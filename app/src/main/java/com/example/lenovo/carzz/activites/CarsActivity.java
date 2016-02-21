package com.example.lenovo.carzz.activites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lenovo.carzz.R;
import com.example.lenovo.carzz.fragments.featuresFragment;
import com.example.lenovo.carzz.fragments.galleryFragment;
import com.example.lenovo.carzz.fragments.modelsFragment;
import com.example.lenovo.carzz.fragments.specsFragment;
import com.example.lenovo.carzz.views.SlidingTabLayout;

public class CarsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout mTabs;
    private ViewPager mPager;
    private MyPagerAdapter mAdapter;
    public final int GALLERY = 0;
    public final int FEATURES = 1;
    public final int MODELS = 2;
    public final int SPECS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
        mTabs.setViewPager(mPager);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        String[] tabsText;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabsText = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case GALLERY:
                    fragment = galleryFragment.newInstance("", "");
                    break;
                case FEATURES:
                    fragment = featuresFragment.newInstance("", "");
                    break;
                case MODELS:
                    fragment = modelsFragment.newInstance("", "");
                    break;
                case SPECS:
                    fragment = specsFragment.newInstance("", "");
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabsText[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}
