package com.example.thelkl321.angrymooseandroid.fight;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thelkl321.angrymooseandroid.MainActivity;
import com.example.thelkl321.angrymooseandroid.R;
import com.example.thelkl321.angrymooseandroid.fight.SurrenderDialogFragment.SurrenderDialogListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FightActivity extends AppCompatActivity implements SurrenderDialogListener{

    private ProgressBar mooseHpBar, playerHpBar;
    TextView middleText, eventLog;
    private static int mooseHp, startingMooseHp, playerHp, startingPlayerHp;
    static int turnCounter, time;
    private static HashMap<String, Button> moveButtons = new HashMap<>();
    private static HashSet<Button> disabledButtons = new HashSet<>();
    private static String mooseMove;
    static String lastEvent;

    private FragmentManager fm;
    EndgameFragment endgameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // Get difficulty and gamemode info
        Intent intent = getIntent();
        startingMooseHp = intent.getIntExtra(MainActivity.MOOSE_KEY, 0);
        startingPlayerHp = intent.getIntExtra(MainActivity.PLAYER_KEY, 0);
        time = intent.getIntExtra(MainActivity.TIME_KEY, 0);

        // Assign views
        mooseHpBar = findViewById(R.id.mooseHealthBar);
        mooseHpBar.setMax(startingMooseHp);
        playerHpBar = findViewById(R.id.playerHealthBar);
        playerHpBar.setMax(startingPlayerHp);
        middleText = findViewById(R.id.middleText);

        // Assign move buttons
        moveButtons.put("throw", (Button) findViewById(R.id.throwButton));
        moveButtons.put("dodge", (Button) findViewById(R.id.dodgeButton));
        moveButtons.put("leap", (Button) findViewById(R.id.leapButton));
        moveButtons.put("kick", (Button) findViewById(R.id.kickButton));
        moveButtons.put("attack", (Button) findViewById(R.id.attackButton));

        // Create scrollable log
        eventLog = findViewById(R.id.logText);
        eventLog.setMovementMethod(new ScrollingMovementMethod());

        // Assign the fragment and hide it
        fm = getSupportFragmentManager();
        endgameFragment = (EndgameFragment) fm.findFragmentById(R.id.endgameFragment);
        hideFragment(endgameFragment);

        startgame();
    }

    abstract void startgame();

    void assignTurnCounter(){
        turnCounter = 1;
        middleText = findViewById(R.id.middleText);
        middleText.setText(String.valueOf(turnCounter));
    }

    void applyDifficulty(){
        mooseHp = startingMooseHp;
        playerHp = startingPlayerHp;
        mooseHpBar.setProgress(mooseHp);
        playerHpBar.setProgress(playerHp);
    }

    abstract void endgame(String outcome);

    private void changeHealth (String target, int amount){
        if (Objects.equals(target, "moose")) {
            if (mooseHp + amount <= mooseHpBar.getMax()) {
                mooseHp += amount;
                mooseHpBar.setProgress(mooseHp);
            }
        } else {
            if (playerHp + amount <= playerHpBar.getMax()) {
                playerHp += amount;
                playerHpBar.setProgress(playerHp);
            }
        }
    }

    private static void disable(String move){
        Button button = moveButtons.get(move);
        assert button != null;
        String text = String.valueOf(button.getText());
        button.setText(text.concat(" X"));
        button.setClickable(false);
        disabledButtons.add(button);
    }

    private static void enable (Button button){
        String text = String.valueOf(button.getText());
        text = text.substring(0, text.length() - 2);
        button.setText(text);
        button.setClickable(true);
    }

    private void checkEndgame (){
        if (mooseHp <= 0 || playerHp <= 0) {
            if (mooseHp > 0) endgame("loss");
            else if (playerHp > 0) endgame("win");
            else endgame("tie");
        }
    }

    static void resetDisabledButtons(){
        for (Button button : disabledButtons)
            enable(button);
        disabledButtons.clear();
    }

    private static int random() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }

    void mooseTurn(){
        int number = random();
        if (number <= 17) {
            mooseMove = "lower head";
            logEvent(getString(R.string.moose_lower_head));
        } else if (number <= 34) {
            mooseMove = "roar";
            logEvent(getString(R.string.moose_roar));
        } else if (number <= 51) {
            mooseMove = "step back";
            logEvent(getString(R.string.moose_step_back));
        } else if (number <= 68) {
            mooseMove = "eat";
            logEvent(getString(R.string.moose_eat));
        } else if (number <= 85){
            mooseMove = "pick up"; // lol "picks up his leg"
            logEvent(getString(R.string.moose_pick_up));
        } else {
            mooseMove = "turn around";
            logEvent(getString(R.string.moose_turn_around));
        }
    }

    private void finishTurn (String playerMove){
        updateTurns();
        int number = random();

        switch (mooseMove){
            case "lower head":
                switch (playerMove){
                    case "throw":
                        if (number <=  20){
                            logEvent(getString(R.string.lower_head_throw_1));
                        } else if (number <= 50){
                            changeHealth("moose", -2);
                            logEvent(getString(R.string.lower_head_throw_2));
                        } else {
                            changeHealth("player", -2);
                            logEvent(getString(R.string.lower_head_throw_3));
                        }
                        break;

                    case "dodge":
                        if (number <= 50){
                            changeHealth("player", -2);
                            logEvent(getString(R.string.lower_head_dodge_1));
                        } else if (number <= 80){
                            logEvent(getString(R.string.lower_head_dodge_2));
                        } else {
                            changeHealth("player", -1);
                            logEvent(getString(R.string.lower_head_dodge_3));
                        }
                        break;

                    case "leap":
                        if (number <= 5){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.lower_head_leap_1));
                        } else if (number <= 20){
                            disable("kick");
                            logEvent(getString(R.string.lower_head_leap_2));
                        } else if (number <= 35){
                            disable("attack");
                            logEvent(getString(R.string.lower_head_leap_3));
                        } else if (number <= 90){
                            logEvent(getString(R.string.lower_head_leap_4));
                        } else {
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.lower_head_leap_5));
                        }
                        break;

                    case "kick":
                        if (number <= 5){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.lower_head_kick_1));
                        } else {
                            changeHealth("player", -2);
                            logEvent(getString(R.string.lower_head_kick_2));
                        }
                        break;

                    case "attack":
                        if (number <= 5) {
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.lower_head_attack_1));
                        } else {
                            changeHealth("player", -2);
                            logEvent(getString(R.string.lower_head_attack_2));
                        }
                        break;
                }
                break;

            case "roar":
                switch (playerMove){
                    case "throw":
                        if (number <= 60){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.roar_throw_1));
                        } else {
                            logEvent(getString(R.string.roar_throw_2));
                        }
                        break;

                    case "dodge":
                        if (number <= 70){
                            logEvent(getString(R.string.roar_dodge_1));
                        } else if (number <= 75){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.roar_dodge_2));
                        } else {
                            disable("leap");
                            logEvent(getString(R.string.roar_dodge_3));
                        }
                        break;

                    case "leap":
                        if (number <= 60) {
                            changeHealth("player", -3);
                            disable("attack");
                            disable("kick");
                            disable("throw");
                            logEvent(getString(R.string.roar_leap_1));
                        } else if (number <= 90){
                            changeHealth("player", -2);
                            disable("attack");
                            logEvent(getString(R.string.roar_leap_2));
                        } else {
                            changeHealth("player", -1);
                            logEvent(getString(R.string.roar_leap_3));
                        }
                        break;

                    case "kick":
                        if (number <= 5) {
                            changeHealth("player", -1);
                            logEvent(getString(R.string.roar_kick_1));
                        } else {
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.roar_kick_2));
                        }
                        break;

                    case "attack":
                        if (number <= 50){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.roar_attack_1));
                        } else if (number <= 80){
                            logEvent(getString(R.string.roar_attack_2));
                        } else {
                            changeHealth("player", -1);
                            logEvent(getString(R.string.roar_attack_3));
                        }
                        break;
                }
                break;

            case "step back":
                switch (playerMove){
                    case "throw":
                        if (number <= 80){
                            logEvent(getString(R.string.step_back_throw_1));
                        } else if (number <= 90) {
                            changeHealth("player", -2);
                            logEvent(getString(R.string.step_back_throw_2));
                        } else {
                            changeHealth("moose", -2);
                            logEvent(getString(R.string.step_back_throw_3));
                        }
                        break;

                    case "dodge":
                        logEvent(getString(R.string.step_back_dodge_1));
                        break;

                    case "leap":
                        if (number <= 15){
                            disable("kick");
                            logEvent(getString(R.string.step_back_leap_1));
                        } else if (number <= 30){
                            disable("attack");
                            logEvent(getString(R.string.step_back_leap_2));
                        } else {
                            logEvent(getString(R.string.step_back_leap_3));
                        }
                        break;

                    case "kick":
                        if (number <= 50){
                            changeHealth("moose", -2);
                            logEvent(getString(R.string.step_back_kick_1));
                        } else {
                            changeHealth("player", -2);
                            logEvent(getString(R.string.step_back_kick_2));
                        }
                        break;

                    case "attack":
                        if (number <= 50){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.step_back_attack_1));
                        } else {
                            changeHealth("player", -1);
                            logEvent(getString(R.string.step_back_attack_2));
                        }
                        break;
                }
                break;

            case "eat":
                switch (playerMove){
                    case "throw":
                        if (number <= 30){
                            changeHealth("moose", 1);
                            logEvent(getString(R.string.eat_throw_1));
                        } else {
                            logEvent(getString(R.string.eat_throw_2));
                        }
                        break;

                    case "dodge":
                        if (number <= 90){
                            changeHealth("moose", 1);
                            logEvent(getString(R.string.eat_dodge_1));
                        } else {
                            changeHealth("moose", 2);
                            logEvent(getString(R.string.eat_dodge_2));
                        }
                        break;

                    case "leap":
                        if (number <= 50) {
                            changeHealth("moose", 1);
                            logEvent(getString(R.string.eat_leap_1));
                        } else if (number <= 65){
                            changeHealth("moose", 1);
                            disable("kick");
                            logEvent(getString(R.string.eat_leap_2));
                        } else if (number <= 80){
                            changeHealth("moose", 1);
                            disable("attack");
                            logEvent(getString(R.string.eat_leap_3));
                        } else {
                            changeHealth("moose", 2);
                            logEvent(getString(R.string.eat_leap_4));
                        }
                        break;

                    case "kick":
                        if (number <= 5){
                            changeHealth("player", 1);
                            logEvent(getString(R.string.eat_kick_1));
                        } else if (number <= 60){
                            changeHealth("moose", -2);
                            logEvent(getString(R.string.eat_kick_2));
                        } else if (number <= 70){
                            changeHealth("moose", 1);
                            logEvent(getString(R.string.eat_kick_3));
                        } else if (number <= 95){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.eat_kick_4));
                        } else {
                            changeHealth("player", -1);
                            disable("attack");
                            disable("kick");
                            logEvent(getString(R.string.eat_kick_5));
                        }
                        break;

                    case "attack":
                        if (number <= 30){
                            changeHealth("player", 1);
                            logEvent(getString(R.string.eat_attack_1));
                        } else if (number <= 40){
                            changeHealth("moose", 1);
                            logEvent(getString(R.string.eat_attack_2));
                        } else if (number <= 95){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.eat_attack_3));
                        } else {
                            changeHealth("player", -1);
                            disable("attack");
                            disable("kick");
                            logEvent(getString(R.string.eat_attack_4));
                        }
                        break;
                }
                break;

            case "pick up":
                switch (playerMove){
                    case "throw":
                        if (number <= 30){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.pick_up_throw_1));
                        } else if (number <= 60){
                            disable("attack");
                            disable("throw");
                            logEvent(getString(R.string.pick_up_throw_2));
                        } else {
                            logEvent(getString(R.string.pick_up_throw_3));
                        }
                        break;

                    case "dodge":
                        if (number <= 50){
                            logEvent(getString(R.string.pick_up_dodge_1));
                        } else if (number <= 80){
                            disable("dodge");
                            disable("kick");
                            logEvent(getString(R.string.pick_up_dodge_2));
                        } else {
                            disable("attack");
                            disable("throw");
                            logEvent(getString(R.string.pick_up_dodge_3));
                        }
                        break;

                    case "leap":
                        if (number <= 15){
                            disable("kick");
                            logEvent(getString(R.string.pick_up_leap_1));
                        } else if (number <= 30){
                            disable("attack");
                            logEvent(getString(R.string.pick_up_leap_2));
                        } else {
                            logEvent(getString(R.string.pick_up_leap_3));
                        }
                        break;

                    case "kick":
                        if (number <= 70){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.pick_up_kick_1));
                        } else {
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.pick_up_kick_2));
                        }
                        break;

                    case "attack":
                        if (number <= 90){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.pick_up_attack_1));
                        } else {
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.pick_up_attack_2));
                        }
                        break;
                }
                break;

            case "turn around":
                switch (playerMove){
                    case "throw":
                        if (number <= 50){
                            changeHealth("player", -3);
                            disable("throw");
                            logEvent(getString(R.string.turn_around_throw_1));
                        } else if (number <= 75){
                            changeHealth("player", -2);
                            disable("leap");
                            disable("dodge");
                            logEvent(getString(R.string.turn_around_throw_2));
                        } else {
                            changeHealth("player", 1);
                            disable("attack");
                            logEvent(getString(R.string.turn_around_throw_3));
                        }
                        break;

                    case "dodge":
                        if (number <= 40){
                            changeHealth("player", -3);
                            logEvent(getString(R.string.turn_around_dodge_1));
                        } else if (number <= 70){
                            disable("dodge");
                            disable("leap");
                            logEvent(getString(R.string.turn_around_dodge_2));
                        } else {
                            logEvent(getString(R.string.turn_around_dodge_3));
                        }
                        break;

                    case "leap":
                        if (number <= 60){
                            logEvent(getString(R.string.turn_around_leap_1));
                        } else if (number <= 65){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.turn_around_leap_2));
                        } else if (number <= 70){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.turn_around_leap_3));
                        } else if (number <= 85){
                            disable("kick");
                            logEvent(getString(R.string.turn_around_leap_4));
                        } else {
                            disable("attack");
                            logEvent(getString(R.string.turn_around_leap_5));
                        }
                        break;

                    case "kick":
                        if (number <= 5){
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.turn_around_kick_1));
                        } else if (number <= 35){
                            changeHealth("player", -3);
                            logEvent(getString(R.string.turn_around_kick_2));
                        } else if (number <= 65){
                            changeHealth("player", -1);
                            logEvent(getString(R.string.turn_around_kick_3));
                        } else {
                            changeHealth("player", -1);
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.turn_around_kick_4));
                        }
                        break;

                    case "attack":
                        if (number <= 70){
                            changeHealth("player", -3);
                            disable("attack");
                            disable("throw");
                            logEvent(getString(R.string.turn_around_attack_1));
                        } else {
                            changeHealth("moose", -1);
                            logEvent(getString(R.string.turn_around_attack_2));
                        }
                        break;
                }
                break;

            default:
                throw new NullPointerException();
        }

        checkEndgame();
        mooseTurn();
    }

    void logEvent(String text){
        TextView logBox = findViewById(R.id.logText);
        logBox.append("\n" + text);
        lastEvent = text;
    }

    public void throwPressed (View view){
        finishTurn("throw");
    }

    public void dodgePressed (View view){
        finishTurn("dodge");
    }

    public void leapPressed (View view){
        finishTurn("leap");
    }

    public void kickPressed (View view){
        finishTurn("kick");
    }

    public void attackPressed (View view){
        finishTurn("attack");
    }

    public void backPressed (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void retryPressed (View view){
        startgame();
        hideFragment(endgameFragment);
    }

    public void sharePressed (View view){
        //TODO: social media integration
    }

    public void hideFragment (Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    public void showFragment (Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed (){
        if (endgameFragment.isVisible())
            finish();
        else {
            DialogFragment dialog = new SurrenderDialogFragment();
            dialog.show(getSupportFragmentManager(), "surrender");
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        endgame("surrender");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) { /* Do nothing */ }

    protected abstract void updateTurns();
}
