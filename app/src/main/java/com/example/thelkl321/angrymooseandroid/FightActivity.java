package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class FightActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // Get difficulty info
        Intent intent = getIntent();
        int mooseHp = intent.getIntExtra(PlayActivity.MOOSE_KEY, 0);
        int playerHp = intent.getIntExtra(PlayActivity.PLAYER_KEY, 0);

        // Apply difficulty info
        ProgressBar mooseHpBar = (ProgressBar) findViewById(R.id.mooseHealthBar);
        ProgressBar playerHpBar = (ProgressBar) findViewById(R.id.playerHealthBar);
        mooseHpBar.setMax(mooseHp);
        playerHpBar.setMax(playerHp);
    }
}
