package borysenko.etsyapp.ui.main.selectedtab;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import borysenko.etsyapp.App;
import borysenko.etsyapp.R;
import borysenko.etsyapp.dagger.components.DaggerSelectedFragmentScreenComponent;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.database.DataBaseManager;
import borysenko.etsyapp.ui.info.InfoActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 13:11
 */
public class SelectedFragment extends Fragment {

    @Inject
    DataBaseManager mDataManager;

    RecyclerView recyclerView;
    SelectedRecyclerAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

    public SelectedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSelectedFragmentScreenComponent.builder()
                .appComponent(App.get(getActivity()).getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_fragment, container, false);
        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.selected_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = initRecyclerView();

        mAdapter.addAll(mDataManager.loadAllData());

        return view;
    }

    private SelectedRecyclerAdapter initRecyclerView() {
        List<Merchandise> merchs = new ArrayList<>();
        final SelectedRecyclerAdapter mAdapter =
                new SelectedRecyclerAdapter(merchs, getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SelectedRecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, Merchandise merchandise) {
                detailInfo(merchandise);
            }
        });

        return mAdapter;
    }

    @OnClick(R.id.reload_button)
    void reloadSavedList() {
        mAdapter.clear();
        mAdapter.addAll(mDataManager.loadAllData());
    }


    private void detailInfo(Merchandise merchandise) {
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra("EXTRA_MERCHANDISE", merchandise);
        intent.putExtra("EXTRA_FRAGMENT", "Selected");
        startActivity(intent);
    }
}
