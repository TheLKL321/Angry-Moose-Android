package com.example.thelkl321.angrymooseandroid.fight;

import android.os.CountDownTimer;

import com.example.thelkl321.angrymooseandroid.MainActivity;
import com.example.thelkl321.angrymooseandroid.util.FragmentUtils;

public class TimeFightActivity extends FightActivity {

    CountDownTimer countdown;
    double currentTime;

    private void timeGamemode(double time) {
        currentTime = time;
        countdown = new CountDownTimer((long) (time * 1000), 100) { // time will always cast to long safely
            @Override
            public void onTick(long millisUntilFinished) {
                middleText.setText(String.valueOf((int) Math.ceil(currentTime)));
                currentTime -= 0.1;
            }

            @Override
            public void onFinish() {
                currentTime = 0;    // prevents a bug where currentTime is not 0, even though onFinish was called
                endgame(Outcome.LOSS);
            }
        };
        countdown.start();
    }

    @Override
    protected void updateTurns() {
        resetDisabledButtons();
    }

    @Override
    protected void startgame() {
        int time = intent.getIntExtra(MainActivity.TIME_KEY, 0);
        timeGamemode(time);
        resetDisabledButtons();
    }

    @Override
    protected void endgame(Outcome outcome) {
        countdown.cancel();
        endgameFragment.setValues(outcome, lastEvent, (int) currentTime);
        FragmentUtils.showFragment(endgameFragment, fm);
    }

    @Override
    protected void onPause() {
        super.onPause();
        countdown.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeGamemode(currentTime);
    }
}
