package com.example.thelkl321.angrymooseandroid;

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
        logEvent("A huge moose stands in front of you");
        mooseTurn();
    }

    @Override
    void endgame(String outcome) {
        endgameFragment.setValues(outcome, lastEvent, turnCounter - 1);
        showFragment(endgameFragment);
    }
}
