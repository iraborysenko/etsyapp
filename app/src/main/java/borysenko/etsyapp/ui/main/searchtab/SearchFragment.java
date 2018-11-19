package borysenko.etsyapp.ui.main.searchtab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import borysenko.etsyapp.R;
import borysenko.etsyapp.dagger.components.DaggerSearchFragmentScreenComponent;
import borysenko.etsyapp.dagger.modules.screen.SearchFragmentScreenModule;
import borysenko.etsyapp.ui.main.searchtab.adapter.PaginationScrollListener;
import borysenko.etsyapp.ui.main.searchtab.adapter.SearchRecyclerAdapter;
import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.ui.info.InfoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 13/11/18
 * Time: 22:27
 */
public class SearchFragment extends Fragment implements SearchFragmentScreen.View {

    @Inject
    SearchPresenter mPresenter;

    @BindView(R.id.autoCompleteCategory) AutoCompleteTextView autoCompleteCategory;
    @BindView(R.id.swipe_container) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.product_query) EditText productEdit;
    @BindView(R.id.search_button) Button searchButton;
    Category[] categoriesList;
    RecyclerView recyclerView;
    Merchandise[] merchs;
    int offsetPoint;
    String productQuery;
    String categoryQuery;

    SearchRecyclerAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSearchFragmentScreenComponent.builder()
                .searchFragmentScreenModule(new SearchFragmentScreenModule(this))
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


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offsetPoint=0;
                mAdapter.clear();
                mPresenter.loadSearchResult(categoryQuery, productQuery, offsetPoint);
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light);
        return view;
    }

    //---------------------------------------------------------------------------------------

    @Override
    public void setSearchCategoryResult(Category[] categories) {

        ArrayList<String> catList = new ArrayList<>();
        for(Category category: categories) {
            catList.add(category.getShortName());
        }

        if (getActivity() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_dropdown_item_1line, catList);
            autoCompleteCategory.setAdapter(adapter);
            autoCompleteCategory.setOnItemClickListener(autoItemSelectedListener);
            categoriesList = categories;
        } else throw new RuntimeException("null returned from getActivity()");

    }

    //verify if category item was selected
    private AdapterView.OnItemClickListener autoItemSelectedListener =
            new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
                {
                    searchButton.setEnabled(true);
                }
            };

    //get data for request
    //decide to choose an AutoCompleteView in case if the list of categories
    // may be too big for spinner. think it's more convenient
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
        hideKeyBoard();

        mAdapter.clear();
        offsetPoint=0;
        mPresenter.loadSearchResult(categoryQuery, productQuery, offsetPoint);
    }

    public void hideKeyBoard() {
        final InputMethodManager imm;
        if (getActivity() != null) {
            imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(searchButton.getWindowToken(), 0);
        } else throw new RuntimeException("null returned from getActivity()");
    }

    //---------------------------------------------------------------------------------------

    public SearchRecyclerAdapter initRecyclerView() {
        final SearchRecyclerAdapter mAdapter = new SearchRecyclerAdapter(new Merchandise[0], getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SearchRecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, Merchandise merchandise) {
                detailInfo(merchandise);
            }
        });

        recyclerView.addOnScrollListener(new PaginationScrollListener(5,0, linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.loadSearchResult(categoryQuery, productQuery, offsetPoint);
                offsetPoint++;
            }
        });
        return mAdapter;
    }

    //---------------------------------------------------------------------------------------

    //get result by request. doesn't include pictures
    @Override
    public void resultWithNoPic(Merchandise[] merchandises) {
        merchs = merchandises;
        for (Merchandise merchandise: merchandises) {
            mPresenter.getImageForTheMerchandise(merchandise, mAdapter);
        }
    }

    private void detailInfo(Merchandise merchandise) {
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra("EXTRA_MERCHANDISE", merchandise);
        intent.putExtra("EXTRA_FRAGMENT", "Search");
        startActivity(intent);
    }
}