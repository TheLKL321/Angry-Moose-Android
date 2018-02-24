package com.example.thelkl321.angrymooseandroid;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thelkl321.angrymooseandroid.SurrenderDialogFragment.SurrenderDialogListener;

import java.util.Objects;

public class FightActivity extends AppCompatActivity implements SurrenderDialogListener{

    public static final String TURN_COUNTER_KEY = "turn";
    public static final String OUTCOME_KEY = "outcome";

    private ProgressBar mooseHpBar, playerHpBar;
    private TextView turnCounterText;
    private static int mooseHp, playerHp, turnCounter;
    private static boolean ifAttack, ifKick, ifThrow, ifDodge, ifLeap = true;

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

        // Create scrollable log
        ((TextView) findViewById(R.id.logText)).setMovementMethod(new ScrollingMovementMethod());
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
        if (mooseHp <= 0 && playerHp <= 0) endgame("tie");
        else if (mooseHp <= 0) endgame("win");
        else if (playerHp <= 0) endgame("loss");
    }

    private void updateTurns (){
        turnCounter++;
        turnCounterText.setText(String.valueOf(turnCounter));
    }
    private void logEvent (String text){
        TextView logBox = findViewById(R.id.logText);
        logBox.append("\n" + text);
    }

    public void throwPressed (View view){
        changeHealth("moose",-3);
    }

    public void dodgePressed (View view){
        logEvent("lolololololol");
    }

    public void leapPressed (View view){
        changeHealth("player", -3);
    }

    public void kickPressed (View view){

    }

    public void attackPressed (View view){

    }

    public void logPressed (View view){
        // TODO: useless button
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
