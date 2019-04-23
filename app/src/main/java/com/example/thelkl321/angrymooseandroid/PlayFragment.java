package com.example.thelkl321.angrymooseandroid;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Objects;

public class PlayFragment extends Fragment {

    private ViewPager mPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = Objects.requireNonNull(getView()).findViewById(R.id.buttonPager);
        PagerAdapter mPagerAdapter = new ButtonPagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    // Adapter for buttonPager
    private class ButtonPagerAdapter extends FragmentStatePagerAdapter {

        ButtonPagerAdapter(FragmentManager fm) {
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

    int getPageNumber() {
        return mPager.getCurrentItem();
    }
}
