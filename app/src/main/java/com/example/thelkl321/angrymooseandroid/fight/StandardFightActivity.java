package com.example.thelkl321.angrymooseandroid.fight;

import com.example.thelkl321.angrymooseandroid.FragmentHelper;
import com.example.thelkl321.angrymooseandroid.R;

public class StandardFightActivity extends FightActivity {

    private void assignTurnCounter() {
        turnCounter = 1;
        middleText = findViewById(R.id.middleText);
        middleText.setText(String.valueOf(turnCounter));
    }

    @Override
    protected void updateTurns() {
        turnCounter++;
        middleText.setText(String.valueOf(turnCounter));
        resetDisabledButtons();
    }

    @Override
    protected void startgame() {
        assignTurnCounter();
        resetDisabledButtons();
    }

    @Override
    protected void endgame(Outcome outcome) {
        endgameFragment.setValues(outcome, lastEvent, turnCounter - 1);
        FragmentHelper.showFragment(endgameFragment, fm);
    }
}
