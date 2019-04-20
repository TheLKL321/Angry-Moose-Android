package com.example.thelkl321.angrymooseandroid.fight;

import android.os.CountDownTimer;

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
        applyDifficulty();
        resetDisabledButtons();
        eventLog.setText("");
        timeGamemode(time);

        // First turn
        logEvent("A huge moose stands in front of you");
        mooseTurn();
    }

    // TODO: different text for running out of time
    @Override
    void endgame(String outcome){
        countdown.cancel();
        endgameFragment.setValues(outcome, lastEvent, (int) currentTime);
        showFragment(endgameFragment);
    }
}
