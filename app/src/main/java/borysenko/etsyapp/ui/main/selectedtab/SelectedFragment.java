package borysenko.etsyapp.ui.main.selectedtab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import borysenko.etsyapp.R;
import borysenko.etsyapp.dagger.components.DaggerSelectedFragmentScreenComponent;
import borysenko.etsyapp.dagger.modules.screen.SelectedFragmentScreenModule;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 13:11
 */
public class SelectedFragment extends Fragment implements SelectedFragmentScreen.View {

    @Inject
    SelectedPresenter mPresenter;

    public SelectedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSelectedFragmentScreenComponent.builder()
                .selectedFragmentScreenModule(new SelectedFragmentScreenModule(this))
                .build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.selected_fragment, container, false);
    }
}
