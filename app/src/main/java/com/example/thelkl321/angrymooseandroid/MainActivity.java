package com.example.thelkl321.angrymooseandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.thelkl321.angrymooseandroid.fight.StandardFightActivity;
import com.example.thelkl321.angrymooseandroid.fight.TimeFightActivity;

import static com.example.thelkl321.angrymooseandroid.Gamemode.STANDARD_GAMEMODE;
import static com.example.thelkl321.angrymooseandroid.Gamemode.TIMEATTACK_GAMEMODE;

public class MainActivity extends AppCompatActivity {

    public static final String MOOSE_KEY = "moose";
    public static final String PLAYER_KEY = "player";
    public static final String TIME_KEY = "time";

    public static Gamemode gamemode;
    private PlayFragment playFragment;
    private OptionsFragment optionsFragment;
    private FragmentManager fm;
    private PopupWindow popupWindow;
    private Point realSize = new Point(), size = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamemode = STANDARD_GAMEMODE;

        // Get display size
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        display.getRealSize(realSize);

        // Inflate credits popup layout
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popup = inflater.inflate(R.layout.popup_text, null);

        // Set size of the credits/help popup
        popupWindow = new PopupWindow(popup);
        popupWindow.setWidth(size.x - 100);
        popupWindow.setHeight(size.y - 100);

        // Set a listener for close popup button
        Button btn = popup.findViewById(R.id.closePopupButton);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // Assign the fragments and hide them
        fm = getSupportFragmentManager();
        playFragment = (PlayFragment) fm.findFragmentById(R.id.playFragment);
        optionsFragment = (OptionsFragment) fm.findFragmentById(R.id.optionsFragment);
        FragmentHelper.hideFragment(playFragment, fm);
        FragmentHelper.hideFragment(optionsFragment, fm);
    }

    public void playPressed(View view) {
        FragmentHelper.showFragment(playFragment, fm);
    }

    public void optionsPressed(View view) {
        FragmentHelper.showFragment(optionsFragment, fm);
    }

    public void creditsPressed(View view) {
        showPopup(getString(R.string.credits));
    }

    public void helpPressed(View view) {
        showPopup(getString(R.string.help));
    }

    public void difficultyPressed(View view) {
        int mooseHp, playerHp, time;
        switch (playFragment.getPageNumber()) {
            case 0:
                mooseHp = 15;
                playerHp = 15;
                time = 60;
                break;

            case 1:
                mooseHp = 20;
                playerHp = 10;
                time = 45;
                break;

            case 2:
                mooseHp = 30;
                playerHp = 10;
                time = 30;
                break;

            case 3:
                mooseHp = 30;
                playerHp = 2;
                time = 15;
                break;

            default:
                throw new NullPointerException();
        }

        Intent intent;
        switch (gamemode) {
            case TIMEATTACK_GAMEMODE:
                intent = new Intent(this, TimeFightActivity.class);
                intent.putExtra(TIME_KEY, time);
                break;

            default:    // STANDARD_GAMEMODE
                intent = new Intent(this, StandardFightActivity.class);
                break;
        }
        intent.putExtra(MOOSE_KEY, mooseHp).putExtra(PLAYER_KEY, playerHp);
        startActivity(intent);
    }

    public void timePressed(View view) {
        if (((ToggleButton) view).isChecked())
            gamemode = TIMEATTACK_GAMEMODE;
        else
            gamemode = STANDARD_GAMEMODE;
    }

    @Override
    public void onBackPressed() {
        if (popupWindow.isShowing())
            popupWindow.dismiss();
        else if (playFragment.isVisible())
            FragmentHelper.hideFragment(playFragment, fm);
        else if (optionsFragment.isVisible())
            FragmentHelper.hideFragment(optionsFragment, fm);
        else finish();
    }

    public void showPopup(String text) {
        popupWindow.showAtLocation(findViewById(R.id.mainLayout), Gravity.CENTER, 0, (size.y - realSize.y) / 2);
        TextView popupText = popupWindow.getContentView().findViewById(R.id.popupText);
        popupText.setText(text);
    }

    // Called by X button on popup
    public void closePopup(View view) {
        popupWindow.dismiss();
    }

    public void exitPressed(View view) {
        finish();
        System.exit(0);
    }
}
