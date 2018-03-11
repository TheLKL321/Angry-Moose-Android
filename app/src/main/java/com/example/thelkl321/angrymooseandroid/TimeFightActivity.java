package com.example.thelkl321.angrymooseandroid;

import android.os.CountDownTimer;

public class TimeFightActivity extends FightActivity {

    CountDownTimer countdown;
    int currentTime;

    private void timeGamemode (int time){
        currentTime = time;
        countdown = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                middleText.setText(String.valueOf(millisUntilFinished / 1000));
                currentTime--;
            }

            @Override
            public void onFinish() {
                endgame("loss");
            }
        };
        countdown.start();
    }

    @Override
    protected void updateTurns(){
        updateCounters();
    }

    @Override
    void gamemodeCreation() {

    }

    @Override
    void startgame(){
        applyDifficulty();
        clearDisabilities();
        restartMoveCounters();
        eventLog.setText("");
        timeGamemode(time);

        // First turn
        logEvent("A huge moose stands in front of you");
        mooseTurn();
    }

    @Override
    void endgame(String outcome){
        countdown.cancel();
        endgameFragment.setValues(outcome, lastEvent, currentTime);
        showFragment(endgameFragment);
    }
}
