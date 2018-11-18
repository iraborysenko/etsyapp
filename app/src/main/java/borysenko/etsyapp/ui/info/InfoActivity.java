package borysenko.etsyapp.ui.info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import borysenko.etsyapp.R;
import borysenko.etsyapp.dagger.components.DaggerInfoScreenComponent;
import borysenko.etsyapp.dagger.modules.screen.InfoScreenModule;
import borysenko.etsyapp.model.Merchandise;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:30
 */
public class InfoActivity extends AppCompatActivity implements InfoScreen.View {

    @BindView (R.id.title) TextView mTitle;
    @BindView(R.id.price) TextView mPrice;
    @BindView(R.id.merch_image) ImageView mImage;
    @BindView(R.id.description) TextView mDescription;
    Merchandise merchandiseToSave;

    @Inject
    InfoPresenter infoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        mTitle.setMovementMethod(new ScrollingMovementMethod());

        DaggerInfoScreenComponent.builder()
                .infoScreenModule(new InfoScreenModule(this))
                .build().inject(this);

        Merchandise merchandise = (Merchandise) getIntent().getSerializableExtra("EXTRA_MERCHANDISE");
        setMovieInfo(merchandise);

        merchandiseToSave = merchandise;
//        infoPresenter.loadSelectedMerch(merch);
    }

    public void setMovieInfo(Merchandise curMerchandise) {

        mTitle.setText(curMerchandise.getTitle());
        mPrice.setText(String.format("%s %s", curMerchandise.getPrice(),
                curMerchandise.getCurrencyCode()));
        mDescription.setText(curMerchandise.getDescription());

        //get image
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this)
                .asBitmap()
                .load(curMerchandise.getImageUrl())
                .apply(options)
                .into(mImage);
    }

    @OnClick(R.id.save_button)
    public void saveButtonClicked() {
        infoPresenter.saveButtonClicked(merchandiseToSave, getApplicationContext());
    }
}
