package com.example.thelkl321.angrymooseandroid;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thelkl321.angrymooseandroid.SurrenderDialogFragment.SurrenderDialogListener;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class FightActivity extends AppCompatActivity implements SurrenderDialogListener{

    public static final String TURN_COUNTER_KEY = "turn";
    public static final String OUTCOME_KEY = "outcome";

    private ProgressBar mooseHpBar, playerHpBar;
    private TextView turnCounterText;
    private static int mooseHp, playerHp, turnCounter;
    private static HashMap<String, Integer> counters = new HashMap<>();
    private static HashMap<String, Button> moveButtons = new HashMap<>();
    private static String mooseMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // Get difficulty info
        Intent intent = getIntent();
        mooseHp = intent.getIntExtra(PlayActivity.MOOSE_KEY, 0);
        playerHp = intent.getIntExtra(PlayActivity.PLAYER_KEY, 0);

        // Apply difficulty info
        mooseHpBar = findViewById(R.id.mooseHealthBar);
        playerHpBar = findViewById(R.id.playerHealthBar);
        mooseHpBar.setMax(mooseHp);
        playerHpBar.setMax(playerHp);

        // Assign turn counter
        turnCounter = 1;
        turnCounterText = findViewById(R.id.turnCounterText);
        turnCounterText.setText(String.valueOf(turnCounter));

        // Assign move buttons
        moveButtons.put("throw", (Button) findViewById(R.id.throwButton));
        moveButtons.put("dodge", (Button) findViewById(R.id.dodgeButton));
        moveButtons.put("leap", (Button) findViewById(R.id.leapButton));
        moveButtons.put("kick", (Button) findViewById(R.id.kickButton));
        moveButtons.put("attack", (Button) findViewById(R.id.attackButton));

        // Start the move counters
        for (String move :
                new String[] {"throw", "dodge", "leap", "kick", "attack"}) {
            counters.put(move, 0);
        }

        // Create scrollable log
        ((TextView) findViewById(R.id.logText)).setMovementMethod(new ScrollingMovementMethod());

        // First turn
        mooseTurn();
    }

    private void endgame (String outcome){
        Intent intent = new Intent(this, EndgameActivity.class);
        intent.putExtra(OUTCOME_KEY, outcome);
        intent.putExtra(PlayActivity.MOOSE_KEY, mooseHpBar.getMax());
        intent.putExtra(PlayActivity.PLAYER_KEY, playerHpBar.getMax());
        intent.putExtra(TURN_COUNTER_KEY, turnCounter);
        startActivity(intent);
        finish();
    }

    private void changeHealth (String target, int amount){
        if (Objects.equals(target, "moose")) {
            mooseHp += amount;
            mooseHpBar.setProgress(mooseHp);
        }
        else {
            playerHp += amount;
            playerHpBar.setProgress(playerHp);
        }
    }

    // TEMPORARY
    private static void disable (String move, int duration){
        counters.put(move, counters.get(move) + duration);
        Button button = moveButtons.get(move);

        for (int i = 0; i < duration; i++){
            String text = String.valueOf(button.getText());
            button.setText(text + "X");
        }
        button.setClickable(false);
    }

    // TEMPORARY
    private static void enable (String move){
        Button button = moveButtons.get(move);
        String text = String.valueOf(button.getText());
        String ttext = text.substring(0, text.length() - 1);
        button.setText(ttext);
        button.setClickable(true);
    }

    private void checkEndgame (){
        if (mooseHp <= 0 && playerHp <= 0) endgame("tie");
        else if (mooseHp <= 0) endgame("win");
        else if (playerHp <= 0) endgame("loss");
    }

    private static void checkCounters(){
        for (String move : counters.keySet()) {
            if (counters.get(move) == 1){
                counters.put(move, 0);
                enable(move);
            }
        }
    }

    private static int random() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }

    private void updateTurns (){
        turnCounter++;
        turnCounterText.setText(String.valueOf(turnCounter));
        checkCounters();
        for (String move : counters.keySet()) {
            int count = counters.get(move);
            if (count > 1) {
                counters.put(move, count - 1);
                Button button = moveButtons.get(move);
                String text = String.valueOf(button.getText());
                String ttext = text.substring(0, text.length() - 1);
                button.setText(ttext);
            }
        }
    }

    // TODO: commentary
    private void mooseTurn (){
        int number = random();
        if (number < 17) {
            mooseMove = "lower head";
            logEvent("lower head");
        }
        else if (number < 34) {
            mooseMove = "roar";
            logEvent("roar");
        }
        else if (number < 51) {
            mooseMove = "step back";
            logEvent("step back");
        }
        else if (number < 68) {
            mooseMove = "eat";
            logEvent("eat");
        }
        else if (number < 85){
            mooseMove = "pick up";
            logEvent("pick up");
        }
        else mooseMove = "turn around";
    }

    //TODO: commentary
    private void finishTurn (String playerMove){

        updateTurns();

        switch (mooseMove){
            case "lower head":
                switch (playerMove){
                    case "throw":
                        break;

                    case "dodge":
                        break;

                    case "leap":
                        changeHealth("player", -1);
                        logEvent("player -1");
                        break;

                    case "kick":
                        changeHealth("moose", -1);
                        logEvent("moose -1");
                        break;

                    case "attack":
                        disable("throw", 2);
                        logEvent("throw disabled");
                        break;
                }
                break;

            case "roar":
                switch (playerMove){
                    case "throw":
                        break;

                    case "dodge":
                        break;

                    case "leap":
                        changeHealth("player", -1);
                        logEvent("player -1");
                        break;

                    case "kick":
                        changeHealth("moose", -1);
                        logEvent("moose -1");
                        break;

                    case "attack":
                        disable("throw", 2);
                        logEvent("throw disabled");
                        break;
                }
                break;

            case "step back":
                switch (playerMove){
                    case "throw":
                        break;

                    case "dodge":
                        break;

                    case "leap":
                        changeHealth("player", -1);
                        logEvent("player -1");
                        break;

                    case "kick":
                        changeHealth("moose", -1);
                        logEvent("moose -1");
                        break;

                    case "attack":
                        disable("throw", 2);
                        logEvent("throw disabled");
                        break;
                }
                break;

            case "eat":
                switch (playerMove){
                    case "throw":
                        break;

                    case "dodge":
                        break;

                    case "leap":
                        changeHealth("player", -1);
                        logEvent("player -1");
                        break;

                    case "kick":
                        changeHealth("moose", -1);
                        logEvent("moose -1");
                        break;

                    case "attack":
                        disable("throw", 2);
                        logEvent("throw disabled");
                        break;
                }
                break;

            case "pick up":
                switch (playerMove){
                    case "throw":
                        break;

                    case "dodge":
                        break;

                    case "leap":
                        changeHealth("player", -1);
                        logEvent("player -1");
                        break;

                    case "kick":
                        changeHealth("moose", -1);
                        logEvent("moose -1");
                        break;

                    case "attack":
                        disable("throw", 2);
                        logEvent("throw disabled");
                        break;
                }
                break;

            case "turn around":
                switch (playerMove){
                    case "throw":
                        break;

                    case "dodge":
                        break;

                    case "leap":
                        changeHealth("player", -1);
                        logEvent("player -1");
                        break;

                    case "kick":
                        changeHealth("moose", -1);
                        logEvent("moose -1");
                        break;

                    case "attack":
                        disable("throw", 2);
                        logEvent("throw disabled");
                        break;
                }
                break;

            default:
                throw new NullPointerException();
        }

        checkEndgame();
        mooseTurn();
    }

    private void logEvent (String text){
        TextView logBox = findViewById(R.id.logText);
        logBox.append("\n" + text);
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

    @Override
    public void onBackPressed (){
        DialogFragment dialog = new SurrenderDialogFragment();
        dialog.show(getFragmentManager(), "surrender");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        endgame("loss");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Do nothing
    }
}
