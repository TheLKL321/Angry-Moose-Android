package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FightActivity extends AppCompatActivity{

    private ProgressBar mooseHpBar;
    private ProgressBar playerHpBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // Get difficulty info
        Intent intent = getIntent();
        int mooseHp = intent.getIntExtra(PlayActivity.MOOSE_KEY, 0);
        int playerHp = intent.getIntExtra(PlayActivity.PLAYER_KEY, 0);

        // Apply difficulty info
        mooseHpBar = findViewById(R.id.mooseHealthBar);
        playerHpBar = findViewById(R.id.playerHealthBar);
        mooseHpBar.setMax(mooseHp);
        playerHpBar.setMax(playerHp);

        // Create scrollable log
        ((TextView) findViewById(R.id.logText)).setMovementMethod(new ScrollingMovementMethod());
    }

    private void logEvent(String text){
        TextView logBox = findViewById(R.id.logText);
        logBox.append("\n" + text);
    }

    public void throwPressed (View view){
        mooseHpBar.setProgress(mooseHpBar.getProgress() - 3);
    }

    public void dodgePressed (View view){
        logEvent("lolololololol");
    }

    public void leapPressed (View view){

    }

    public void kickPressed (View view){

    }

    public void attackPressed (View view){

    }

    public void logPressed (View view){
        // TODO: useless button
    }
}
