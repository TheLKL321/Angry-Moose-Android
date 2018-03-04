package com.example.thelkl321.angrymooseandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayFragment extends Fragment {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = getView().findViewById(R.id.buttonPager);
        mPagerAdapter = new ButtonPagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    // Adapter for buttonPager
    private class ButtonPagerAdapter extends FragmentStatePagerAdapter {

        public ButtonPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PagerButtonsFragment.create(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public int getPageNumber (){
        return mPager.getCurrentItem();
    }
}
