package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PlayActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public static final String MOOSE_KEY = "moose";
    public static final String PLAYER_KEY = "player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.buttonPager);
        mPagerAdapter = new ButtonPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    public void helpPressed (View view){
        // TODO: popup with a tutorial
    }

    public void timePressed (View view){
        // TODO: new gamemode
    }

    public void difficultyPressed (View view){

        int mooseHp, playerHp;
        switch (mPagerAdapter.getCount()) {
            case 0:
                mooseHp = 15;
                playerHp = 15;
                break;

            case 1:
                mooseHp = 20;
                playerHp = 10;
                break;

            case 2:
                mooseHp = 30;
                playerHp = 10;
                break;

            case 3:
                mooseHp = 30;
                playerHp = 2;
                break;

            default:
                throw new NullPointerException();
        }

        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra(MOOSE_KEY, mooseHp);
        intent.putExtra(PLAYER_KEY, playerHp);
        startActivity(intent);
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
}
