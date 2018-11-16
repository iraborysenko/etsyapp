package borysenko.etsyapp.ui.TabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import borysenko.etsyapp.R;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 13:11
 */
public class SelectedFragment extends Fragment {

    public SelectedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
}
