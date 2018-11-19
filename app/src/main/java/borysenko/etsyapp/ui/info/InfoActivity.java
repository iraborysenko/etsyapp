package borysenko.etsyapp.ui.info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import borysenko.etsyapp.App;
import borysenko.etsyapp.R;
import borysenko.etsyapp.dagger.components.DaggerInfoScreenComponent;
import borysenko.etsyapp.dagger.components.InfoScreenComponent;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.database.DataBaseManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:30
 */
public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.price) TextView mPrice;
    @BindView(R.id.merch_image) ImageView mImage;
    @BindView(R.id.description) TextView mDescription;
    @BindView(R.id.changing_button) Button mButton;
    Merchandise selectedMerchandise;
    String trigger;

    @Inject
    DataBaseManager mDataManager;

    private InfoScreenComponent infoScreenComponent;

    public InfoScreenComponent getActivityComponent() {
        if (infoScreenComponent == null) {
            infoScreenComponent = DaggerInfoScreenComponent.builder()
                    .appComponent(App.get(this).getApplicationComponent())
                    .build();
        }
        return infoScreenComponent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        mTitle.setMovementMethod(new ScrollingMovementMethod());

        //get object of the selected item
        Merchandise merchandise = (Merchandise) getIntent().getSerializableExtra("EXTRA_MERCHANDISE");
        setMovieInfo(merchandise);

        //find out which action do we need
        trigger = getIntent().getStringExtra("EXTRA_FRAGMENT");
        if(trigger.equals("Search")) mButton.setText(R.string.search_case);
        else if (trigger.equals("Selected")) mButton.setText(R.string.selected_case);

        selectedMerchandise = merchandise;
    }

    //display details of the selected item
    public void setMovieInfo(Merchandise curMerchandise) {

        mTitle.setText(curMerchandise.getTitle());
        mPrice.setText(String.format("%s %s", curMerchandise.getPrice(),
                curMerchandise.getCurrencyCode()));
        mDescription.setText(curMerchandise.getDescription());

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.product_placeholder)
                .error(R.mipmap.no_image)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .asBitmap()
                .load(curMerchandise.getImageUrl())
                .apply(options)
                .into(mImage);
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 30);
        toast.show();
    }

    //save of remove data
    @OnClick(R.id.changing_button)
    public void saveButtonClicked() {
        if(trigger.equals("Search")) {
            mDataManager.saveMerchandise(selectedMerchandise);
            showToast("Item Successfully Saved");

        } else if (trigger.equals("Selected")) {
            mDataManager.removeMerchandise(selectedMerchandise);
            showToast("Idem Deleted");
        }

        mButton.setEnabled(false);
    }
}
