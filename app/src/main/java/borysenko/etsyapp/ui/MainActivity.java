package borysenko.etsyapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import borysenko.etsyapp.R;
import borysenko.etsyapp.ui.TabFragments.SearchFragment;
import borysenko.etsyapp.ui.TabFragments.SelectedFragment;
import borysenko.etsyapp.ui.TabFragments.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    Fragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewpager);
        searchFragment = setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public Fragment setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment searchFragment = new SearchFragment();
        adapter.addFragment(searchFragment, "Search");
        adapter.addFragment(new SelectedFragment(), "Selected");
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        return searchFragment;
    }
}
