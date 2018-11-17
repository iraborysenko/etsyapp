package borysenko.etsyapp.ui.TabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import borysenko.etsyapp.adapter.MainRecyclerAdapter;
import borysenko.etsyapp.adapter.PaginationAdapterCallBack;
import borysenko.etsyapp.adapter.PaginationScrollListener;
import borysenko.etsyapp.dagger.DaggerMainScreenComponent;
import borysenko.etsyapp.dagger.MainScreenModule;
import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.ui.InfoActivity;
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
public class SearchFragment extends Fragment implements MainScreen.View, PaginationAdapterCallBack {

    @Inject
    MainPresenter mPresenter;

    @BindView(R.id.autoCompleteCategory) AutoCompleteTextView autoCompleteCategory;
    @BindView(R.id.product_query) EditText productEdit;
    @BindView(R.id.search_button) Button searchButton;
    Category[] categoriesList;
    RecyclerView recyclerView;
    Merchandise[] merchs;
    int offsetPoint;
    String productQuery;
    String categoryQuery;

    MainRecyclerAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

    private boolean isLoading = false;

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

        recyclerView = view.findViewById(R.id.main_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = initRecyclerView();

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
        merchs = merchandises;
        for (Merchandise merchandise: merchandises) {
            mPresenter.getImageForTheMerchandise(merchandise, mAdapter);
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
        productQuery = productEdit.getText().toString();
        categoryQuery = autoCompleteCategory.getText().toString();

        for (Category category: categoriesList) {
            if (category.getShortName().equals(categoryQuery)) {
                categoryQuery = category.getCategoryName();
                break;
            }
        }

        mAdapter.clear();
        offsetPoint=0;
        loadNextSearchResult(categoryQuery, productQuery);
    }


    void loadNextSearchResult(String categoryQuery, String productQuery) {
        mPresenter.loadSearchResult(categoryQuery, productQuery, offsetPoint);
        offsetPoint++;
    }


    public MainRecyclerAdapter initRecyclerView() {
        MainRecyclerAdapter mAdapter = new MainRecyclerAdapter(new Merchandise[0], getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MainRecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, Merchandise merchandise) {
                detailInfo(merchandise);
            }
        });


        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                try {
                    isLoading=true;
                    Thread.sleep(10000);
                    loadNextSearchResult(categoryQuery, productQuery);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        return mAdapter;
    }

    @Override
    public void retryPageLoad() {
        offsetPoint = 0;
        loadNextSearchResult(categoryQuery, productQuery);
    }

    private void detailInfo(Merchandise merchandise) {
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra("EXTRA_MERCHANDISE", merchandise);
        startActivity(intent);
    }
}
