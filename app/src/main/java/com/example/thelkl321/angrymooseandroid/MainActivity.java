package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MOOSE_KEY = "moose";
    public static final String PLAYER_KEY = "player";

    private PlayFragment playFragment;
    private OptionsFragment optionsFragment;
    private FragmentManager fm;

    private static PopupWindow popupWindow;
    Point realSize = new Point(), size = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Assign the fragments and hide them
        playFragment = (PlayFragment) getSupportFragmentManager().findFragmentById(R.id.playFragment);
        optionsFragment = (OptionsFragment) getSupportFragmentManager().findFragmentById(R.id.optionsFragment);
        fm = getSupportFragmentManager();
        hideFragment(playFragment);
        hideFragment(optionsFragment);
    }

    public void playPressed (View view){
        showFragment(playFragment);
    }

    public void optionsPressed (View view){
        showFragment(optionsFragment);
    }

    //TODO: Actual credits and extract string
    public void creditsPressed (View view){
        showPopup("Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Nullam tempor mi vitae\n" +
                "odio vulputate, nec egestas quam\n" +
                "euismod. Sed sit amet urna justo. In elementum,\n" +
                "nulla eu pharetra lobortis, sem nisi interdum\n" +
                "ante, id luctus ex libero quis justo. Sed\n" +
                "volutpat tincidunt augue ac lobortis. Vestibulum\n" +
                "a lacinia nibh. Mauris porttitor massa ut aliquet\n" +
                "sodales. Integer sit amet turpis scelerisque,\n" +
                "ultrices ipsum et, volutpat mi.");
    }

    // TODO: extract string
    public void helpPressed (View view){
        showPopup("Press a button to make your move.\n" +
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
        switch (playFragment.getPageNumber()) {
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
        } else if (playFragment.isVisible()){
            hideFragment(playFragment);
        } else if (optionsFragment.isVisible()){
            hideFragment(optionsFragment);
        }
        else finish();
    }

    public void hideFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    public void showFragment (Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public void closePopup (View view){
        popupWindow.dismiss();
    }

    public void showPopup (String text){
        popupWindow.showAtLocation(findViewById(R.id.mainLayout), Gravity.CENTER,0, (size.y - realSize.y)/2);
        TextView popupText = popupWindow.getContentView().findViewById(R.id.popupText);
        popupText.setText(text);
    }

    public void exitPressed (View view){
        finish();
        System.exit(0);
    }
}
