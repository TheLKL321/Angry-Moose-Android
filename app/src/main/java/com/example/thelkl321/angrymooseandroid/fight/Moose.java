package com.example.thelkl321.angrymooseandroid.fight;

import com.example.thelkl321.angrymooseandroid.R;

class Moose extends Fighter {

    Moose(int maxHealth, FightActivity myWorld) {
        super(maxHealth, myWorld);
    }

    MooseMove mooseTurn(){
        int number = FightActivity.random();
        if (number <= 17) {
            myWorld.logEvent(R.string.moose_lower_head);
            return MooseMove.LOWER_HEAD;
        } else if (number <= 34) {
            myWorld.logEvent(R.string.moose_roar);
            return MooseMove.ROAR;
        } else if (number <= 51) {
            myWorld.logEvent(R.string.moose_step_back);
            return MooseMove.STEP_BACK;
        } else if (number <= 68) {
            myWorld.logEvent(R.string.moose_eat);
            return MooseMove.EAT;
        } else if (number <= 85){
            myWorld.logEvent(R.string.moose_pick_up);
            return MooseMove.RAISE_LEG;
        } else {
            myWorld.logEvent(R.string.moose_turn_around);
            return MooseMove.TURN_AROUND;
        }
    }
}
