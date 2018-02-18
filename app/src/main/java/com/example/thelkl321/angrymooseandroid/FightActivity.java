package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

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
    }

    public void throwPressed (View view){
        mooseHpBar.setProgress(mooseHpBar.getProgress() - 3);
    }

    public void dodgePressed (View view){

    }

    public void leapPressed (View view){

    }

    public void kickPressed (View view){

    }

    public void attackPressed (View view){

    }
}
