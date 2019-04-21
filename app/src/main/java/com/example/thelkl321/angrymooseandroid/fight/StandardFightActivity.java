package com.example.thelkl321.angrymooseandroid.fight;

import com.example.thelkl321.angrymooseandroid.R;

public class StandardFightActivity extends FightActivity {

    @Override
    protected void updateTurns(){
        turnCounter++;
        middleText.setText(String.valueOf(turnCounter));
        resetDisabledButtons();
    }

    void assignTurnCounter(){
        turnCounter = 1;
        middleText = findViewById(R.id.middleText);
        middleText.setText(String.valueOf(turnCounter));
    }

    @Override
    void startgame(){
        assignTurnCounter();
        resetDisabledButtons();
    }

    @Override
    void endgame(Outcome outcome) {
        endgameFragment.setValues(outcome, lastEvent, turnCounter - 1);
        showFragment(endgameFragment);
    }
}
