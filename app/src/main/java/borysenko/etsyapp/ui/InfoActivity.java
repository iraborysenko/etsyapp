package borysenko.etsyapp.ui;

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
import borysenko.etsyapp.dagger.DaggerInfoScreenComponent;
import borysenko.etsyapp.dagger.InfoScreenModule;
import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:30
 */
public class InfoActivity extends AppCompatActivity implements InfoScreen.View {

    private TextView mTitle;
    private TextView mPrice;
    private ImageView mImage;
    private TextView mDescription;

    @Inject
    InfoPresenter infoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mTitle = findViewById(R.id.title);
        mImage = findViewById(R.id.merch_image);
        mPrice = findViewById(R.id.price);
        mDescription = findViewById(R.id.description);
        mTitle.setMovementMethod(new ScrollingMovementMethod());

        DaggerInfoScreenComponent.builder()
                .infoScreenModule(new InfoScreenModule(this))
                .build().inject(this);

        Merchandise merchandise = (Merchandise) getIntent().getSerializableExtra("EXTRA_MERCHANDISE");
        setMovieInfo(merchandise);

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
}
