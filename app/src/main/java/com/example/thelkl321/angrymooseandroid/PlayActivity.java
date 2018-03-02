package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private PopupWindow popupWindow;
    private Point realSize = new Point(), size = new Point();

    public static final String MOOSE_KEY = "moose";
    public static final String PLAYER_KEY = "player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.buttonPager);
        mPagerAdapter = new ButtonPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        // Get display size
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        display.getRealSize(realSize);

        // Inflate credits popup layout
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.popup_text, null);

        // Set size of the credits popup
        popupWindow = new PopupWindow(popup);
        popupWindow.setWidth(size.x - 100);
        popupWindow.setHeight(size.y - 100);

        // Set a listener for close popup button
        Button btn = popup.findViewById(R.id.closePopupButton);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void helpPressed (View view){

        popupWindow.showAtLocation(findViewById(R.id.playLayout), Gravity.CENTER,0,(size.y - realSize.y)/2);
        TextView popupText = popupWindow.getContentView().findViewById(R.id.popupText);
        popupText.setText("Press a button to make your move.\n" +
                "Your goal is to decrease the moose's\n" +
                "health to 0 without dying yourself.\n" +
                "\n" +
                "Moves:\n" +
                "Attack - you attempt to throw a punch\n" +
                "Kick - you try to kick the moose\n" +
                "Leap - you throw yourself on the floor to evade an attack\n" +
                "Dodge - you step to the side, doing a light dodge\n" +
                "Throw dirt - you grab a handful of earth to chuck it at the beast's eyes\n" +
                "\n" +
                "In the middle of the screen you'll find an event log. Use it to find out about mooses actions and their consequences\n" +
                "Watch out! The beast can feast on wild berries to heal himself\n" +
                "\n" +
                "Good luck!");
    }

    public void timePressed (View view){
        // TODO: new gamemode
    }

    public void difficultyPressed (View view){

        int mooseHp, playerHp;
        switch (mPager.getCurrentItem()) {
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

    // Close the popup on back press
    @Override
    public void onBackPressed (){
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        else finish();
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
