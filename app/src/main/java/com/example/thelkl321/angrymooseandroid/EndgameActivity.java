package com.example.thelkl321.angrymooseandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class EndgameActivity extends AppCompatActivity {

    private static int mooseHp, playerHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        // Extract data
        Intent intent = getIntent();
        String outcome = intent.getStringExtra(FightActivity.OUTCOME_KEY);
        mooseHp = intent.getIntExtra(PlayActivity.MOOSE_KEY, 0);
        playerHp = intent.getIntExtra(PlayActivity.PLAYER_KEY, 0);

        // Set outcome
        TextView text = findViewById(R.id.outcomeText);
        switch (outcome){
            case "tie":
                text.setText("IT'S A\nTIE");
                break;

            case "win":
                text.setText("YOU\nWON");
                break;

            case "loss":
                text.setText("YOU\nLOST");
                break;
        }
    }

    public void backPressed (View view){
        finish();
    }

    public void retryPressed (View view){
        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra(PlayActivity.MOOSE_KEY, mooseHp);
        intent.putExtra(PlayActivity.PLAYER_KEY, playerHp);
        startActivity(intent);
    }

    public void sharePressed (View view){
        //TODO: social media integration
    }

}
