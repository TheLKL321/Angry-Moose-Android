package com.example.thelkl321.angrymooseandroid.fight;

import com.example.thelkl321.angrymooseandroid.R;

public class StandardFightActivity extends FightActivity {

    @Override
    protected void updateTurns(){
        turnCounter++;
        middleText.setText(String.valueOf(turnCounter));
        resetDisabledButtons();
    }

    @Override
    void startgame(){
        applyDifficulty();
        assignTurnCounter();
        resetDisabledButtons();
        eventLog.setText("");

        // First turn
        logEvent(getString(R.string.initial_log));
        mooseTurn();
    }

    @Override
    void endgame(String outcome) {
        endgameFragment.setValues(outcome, lastEvent, turnCounter - 1);
        showFragment(endgameFragment);
    }
}
