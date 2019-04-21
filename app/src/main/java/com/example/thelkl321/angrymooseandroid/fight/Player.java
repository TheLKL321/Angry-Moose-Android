package com.example.thelkl321.angrymooseandroid.fight;

import com.example.thelkl321.angrymooseandroid.R;

import static com.example.thelkl321.angrymooseandroid.fight.PlayerMove.*;

class Player extends Fighter {
    private Moose opponent;

    Player(int maxHealth, FightActivity myWorld, Moose opponent) {
        super(maxHealth, myWorld);
        this.opponent = opponent;
    }

    void tradeBlows(PlayerMove playerMove, MooseMove mooseMove) {
        int number = FightActivity.random();

        switch (mooseMove) {
            case LOWER_HEAD:
                switch (playerMove) {
                    case THROW:
                        if (number <= 20) {
                            myWorld.logEvent(R.string.lower_head_throw_1);
                        } else if (number <= 50) {
                            opponent.takeDamage(2);
                            myWorld.logEvent(R.string.lower_head_throw_2);
                        } else {
                            takeDamage(2);
                            myWorld.logEvent(R.string.lower_head_throw_3);
                        }
                        break;

                    case DODGE:
                        if (number <= 50) {
                            takeDamage(2);
                            myWorld.logEvent(R.string.lower_head_dodge_1);
                        } else if (number <= 80) {
                            myWorld.logEvent(R.string.lower_head_dodge_2);
                        } else {
                            takeDamage(1);
                            myWorld.logEvent(R.string.lower_head_dodge_3);
                        }
                        break;

                    case LEAP:
                        if (number <= 5) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.lower_head_leap_1);
                        } else if (number <= 20) {
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.lower_head_leap_2);
                        } else if (number <= 35) {
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.lower_head_leap_3);
                        } else if (number <= 90) {
                            myWorld.logEvent(R.string.lower_head_leap_4);
                        } else {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.lower_head_leap_5);
                        }
                        break;

                    case KICK:
                        if (number <= 5) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.lower_head_kick_1);
                        } else {
                            takeDamage(2);
                            myWorld.logEvent(R.string.lower_head_kick_2);
                        }
                        break;

                    case ATTACK:
                        if (number <= 5) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.lower_head_attack_1);
                        } else {
                            takeDamage(2);
                            myWorld.logEvent(R.string.lower_head_attack_2);
                        }
                        break;
                }
                break;

            case ROAR:
                switch (playerMove) {
                    case THROW:
                        if (number <= 60) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.roar_throw_1);
                        } else {
                            myWorld.logEvent(R.string.roar_throw_2);
                        }
                        break;

                    case DODGE:
                        if (number <= 70) {
                            myWorld.logEvent(R.string.roar_dodge_1);
                        } else if (number <= 75) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.roar_dodge_2);
                        } else {
                            myWorld.disable(LEAP);
                            myWorld.logEvent(R.string.roar_dodge_3);
                        }
                        break;

                    case LEAP:
                        if (number <= 60) {
                            takeDamage(3);
                            myWorld.disable(ATTACK);
                            myWorld.disable(KICK);
                            myWorld.disable(THROW);
                            myWorld.logEvent(R.string.roar_leap_1);
                        } else if (number <= 90) {
                            takeDamage(2);
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.roar_leap_2);
                        } else {
                            takeDamage(1);
                            myWorld.logEvent(R.string.roar_leap_3);
                        }
                        break;

                    case KICK:
                        if (number <= 5) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.roar_kick_1);
                        } else {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.roar_kick_2);
                        }
                        break;

                    case ATTACK:
                        if (number <= 50) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.roar_attack_1);
                        } else if (number <= 80) {
                            myWorld.logEvent(R.string.roar_attack_2);
                        } else {
                            takeDamage(1);
                            myWorld.logEvent(R.string.roar_attack_3);
                        }
                        break;
                }
                break;

            case STEP_BACK:
                switch (playerMove) {
                    case THROW:
                        if (number <= 80) {
                            myWorld.logEvent(R.string.step_back_throw_1);
                        } else if (number <= 90) {
                            takeDamage(2);
                            myWorld.logEvent(R.string.step_back_throw_2);
                        } else {
                            opponent.takeDamage(2);
                            myWorld.logEvent(R.string.step_back_throw_3);
                        }
                        break;

                    case DODGE:
                        myWorld.logEvent(R.string.step_back_dodge_1);
                        break;

                    case LEAP:
                        if (number <= 15) {
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.step_back_leap_1);
                        } else if (number <= 30) {
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.step_back_leap_2);
                        } else {
                            myWorld.logEvent(R.string.step_back_leap_3);
                        }
                        break;

                    case KICK:
                        if (number <= 50) {
                            opponent.takeDamage(2);
                            myWorld.logEvent(R.string.step_back_kick_1);
                        } else {
                            takeDamage(2);
                            myWorld.logEvent(R.string.step_back_kick_2);
                        }
                        break;

                    case ATTACK:
                        if (number <= 50) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.step_back_attack_1);
                        } else {
                            takeDamage(1);
                            myWorld.logEvent(R.string.step_back_attack_2);
                        }
                        break;
                }
                break;

            case EAT:
                switch (playerMove) {
                    case THROW:
                        if (number <= 30) {
                            opponent.takeDamage(-1);
                            myWorld.logEvent(R.string.eat_throw_1);
                        } else {
                            myWorld.logEvent(R.string.eat_throw_2);
                        }
                        break;

                    case DODGE:
                        if (number <= 90) {
                            opponent.takeDamage(-1);
                            myWorld.logEvent(R.string.eat_dodge_1);
                        } else {
                            opponent.takeDamage(-2);
                            myWorld.logEvent(R.string.eat_dodge_2);
                        }
                        break;

                    case LEAP:
                        if (number <= 50) {
                            opponent.takeDamage(-1);
                            myWorld.logEvent(R.string.eat_leap_1);
                        } else if (number <= 65) {
                            opponent.takeDamage(-1);
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.eat_leap_2);
                        } else if (number <= 80) {
                            opponent.takeDamage(-1);
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.eat_leap_3);
                        } else {
                            opponent.takeDamage(-2);
                            myWorld.logEvent(R.string.eat_leap_4);
                        }
                        break;

                    case KICK:
                        if (number <= 5) {
                            takeDamage(-1);
                            myWorld.logEvent(R.string.eat_kick_1);
                        } else if (number <= 60) {
                            opponent.takeDamage(2);
                            myWorld.logEvent(R.string.eat_kick_2);
                        } else if (number <= 70) {
                            opponent.takeDamage(-1);
                            myWorld.logEvent(R.string.eat_kick_3);
                        } else if (number <= 95) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.eat_kick_4);
                        } else {
                            takeDamage(1);
                            myWorld.disable(ATTACK);
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.eat_kick_5);
                        }
                        break;

                    case ATTACK:
                        if (number <= 30) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.eat_attack_1);
                        } else if (number <= 40) {
                            opponent.takeDamage(-1);
                            myWorld.logEvent(R.string.eat_attack_2);
                        } else if (number <= 95) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.eat_attack_3);
                        } else {
                            takeDamage(1);
                            myWorld.disable(ATTACK);
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.eat_attack_4);
                        }
                        break;
                }
                break;

            case RAISE_LEG:
                switch (playerMove) {
                    case THROW:
                        if (number <= 30) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.pick_up_throw_1);
                        } else if (number <= 60) {
                            myWorld.disable(ATTACK);
                            myWorld.disable(THROW);
                            myWorld.logEvent(R.string.pick_up_throw_2);
                        } else {
                            myWorld.logEvent(R.string.pick_up_throw_3);
                        }
                        break;

                    case DODGE:
                        if (number <= 50) {
                            myWorld.logEvent(R.string.pick_up_dodge_1);
                        } else if (number <= 80) {
                            myWorld.disable(DODGE);
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.pick_up_dodge_2);
                        } else {
                            myWorld.disable(ATTACK);
                            myWorld.disable(THROW);
                            myWorld.logEvent(R.string.pick_up_dodge_3);
                        }
                        break;

                    case LEAP:
                        if (number <= 15) {
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.pick_up_leap_1);
                        } else if (number <= 30) {
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.pick_up_leap_2);
                        } else {
                            myWorld.logEvent(R.string.pick_up_leap_3);
                        }
                        break;

                    case KICK:
                        if (number <= 70) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.pick_up_kick_1);
                        } else {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.pick_up_kick_2);
                        }
                        break;

                    case ATTACK:
                        if (number <= 90) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.pick_up_attack_1);
                        } else {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.pick_up_attack_2);
                        }
                        break;
                }
                break;

            case TURN_AROUND:
                switch (playerMove) {
                    case THROW:
                        if (number <= 50) {
                            takeDamage(3);
                            myWorld.disable(THROW);
                            myWorld.logEvent(R.string.turn_around_throw_1);
                        } else if (number <= 75) {
                            takeDamage(2);
                            myWorld.disable(LEAP);
                            myWorld.disable(DODGE);
                            myWorld.logEvent(R.string.turn_around_throw_2);
                        } else {
                            takeDamage(1);
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.turn_around_throw_3);
                        }
                        break;

                    case DODGE:
                        if (number <= 40) {
                            takeDamage(3);
                            myWorld.logEvent(R.string.turn_around_dodge_1);
                        } else if (number <= 70) {
                            myWorld.disable(DODGE);
                            myWorld.disable(LEAP);
                            myWorld.logEvent(R.string.turn_around_dodge_2);
                        } else {
                            myWorld.logEvent(R.string.turn_around_dodge_3);
                        }
                        break;

                    case LEAP:
                        if (number <= 60) {
                            myWorld.logEvent(R.string.turn_around_leap_1);
                        } else if (number <= 65) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.turn_around_leap_2);
                        } else if (number <= 70) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.turn_around_leap_3);
                        } else if (number <= 85) {
                            myWorld.disable(KICK);
                            myWorld.logEvent(R.string.turn_around_leap_4);
                        } else {
                            myWorld.disable(ATTACK);
                            myWorld.logEvent(R.string.turn_around_leap_5);
                        }
                        break;

                    case KICK:
                        if (number <= 5) {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.turn_around_kick_1);
                        } else if (number <= 35) {
                            takeDamage(3);
                            myWorld.logEvent(R.string.turn_around_kick_2);
                        } else if (number <= 65) {
                            takeDamage(1);
                            myWorld.logEvent(R.string.turn_around_kick_3);
                        } else {
                            takeDamage(1);
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.turn_around_kick_4);
                        }
                        break;

                    case ATTACK:
                        if (number <= 70) {
                            takeDamage(3);
                            myWorld.disable(ATTACK);
                            myWorld.disable(THROW);
                            myWorld.logEvent(R.string.turn_around_attack_1);
                        } else {
                            opponent.takeDamage(1);
                            myWorld.logEvent(R.string.turn_around_attack_2);
                        }
                        break;
                }
                break;

            default:
                throw new NullPointerException();
        }
    }
}
