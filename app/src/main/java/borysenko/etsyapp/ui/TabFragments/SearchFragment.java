package borysenko.etsyapp.ui.TabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import borysenko.etsyapp.R;
import borysenko.etsyapp.dagger.DaggerMainScreenComponent;
import borysenko.etsyapp.dagger.MainScreenModule;
import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.ui.MainPresenter;
import borysenko.etsyapp.ui.MainScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 13/11/18
 * Time: 22:27
 */
public class SearchFragment extends Fragment implements MainScreen.View {

    @Inject
    MainPresenter mPresenter;


    @BindView(R.id.autoCompleteCategory) AutoCompleteTextView autoCompleteCategory;
    @BindView(R.id.product_query) EditText productEdit;
    @BindView(R.id.search_button) Button searchButton;
    Category[] categoriesList;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainScreenComponent.builder()
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);
        mPresenter.loadCategories();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setSearchCategoryResult(Category[] categories) {
        ArrayList<String> catList = new ArrayList<>();
        for(Category category: categories) {
            catList.add(category.getShortName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getActivity(), android.R.layout.simple_dropdown_item_1line, catList);
                autoCompleteCategory.setAdapter(adapter);
                autoCompleteCategory.setOnItemClickListener(autoItemSelectedListener);
                categoriesList = categories;
    }

    @Override
    public void resultWithNoPic(Merchandise[] merchandises) {
        for (Merchandise merchandise: merchandises) {
            mPresenter.getImageForTheMerchandise(merchandise.getListingId());
        }

    }

    private AdapterView.OnItemClickListener autoItemSelectedListener =
            new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
                {
                    searchButton.setEnabled(true);
                }
            };


    @OnClick(R.id.search_button)
    void getRequestData() {
        String productQuery = productEdit.getText().toString();
        String categoryQuery = autoCompleteCategory.getText().toString();

        for (Category category: categoriesList) {
            if (category.getShortName().equals(categoryQuery)) {
                categoryQuery = category.getCategoryName();
                break;
            }
        }

//        Log.e("query", categoryQuery);

        mPresenter.loadSearchResult(categoryQuery, productQuery);

    }

}
