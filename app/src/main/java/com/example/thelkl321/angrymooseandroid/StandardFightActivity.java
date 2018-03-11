package com.example.thelkl321.angrymooseandroid;

public class StandardFightActivity extends FightActivity {

    @Override
    protected void updateTurns(){
        turnCounter++;
        middleText.setText(String.valueOf(turnCounter));
        updateCounters();
    }

    @Override
    void gamemodeCreation() {
        // Do nothing TODO: OR SHOULD I?
    }

    @Override
    void startgame(){
        applyDifficulty();
        assignTurnCounter();
        clearDisabilities();
        restartMoveCounters();
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
