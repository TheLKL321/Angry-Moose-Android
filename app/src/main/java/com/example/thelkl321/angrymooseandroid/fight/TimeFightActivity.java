package com.example.thelkl321.angrymooseandroid.fight;

import android.os.CountDownTimer;

import com.example.thelkl321.angrymooseandroid.R;

public class TimeFightActivity extends FightActivity {

    CountDownTimer countdown;
    double currentTime;

    private void timeGamemode (int time){
        currentTime = time;
        countdown = new CountDownTimer(time * 1000, 250) {
            @Override
            public void onTick(long millisUntilFinished) {
                middleText.setText(String.valueOf((int) Math.ceil(currentTime)));
                currentTime -= 0.25;
            }

            @Override
            public void onFinish() {
                currentTime = 0;    // prevents a bug where currentTime is not 0, even though onFinish was called
                endgame("loss");
            }
        };
        countdown.start();
    }

    @Override
    protected void updateTurns(){
        resetDisabledButtons();
    }

    @Override
    void startgame(){
        timeGamemode(time);
        resetDisabledButtons();
    }

    @Override
    void endgame(String outcome){
        countdown.cancel();
        endgameFragment.setValues(outcome, lastEvent, (int) currentTime);
        showFragment(endgameFragment);
    }
}
