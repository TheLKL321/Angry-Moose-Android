package com.example.thelkl321.angrymooseandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class EndgameActivity extends AppCompatActivity {

    private static int mooseHp, playerHp;

    //TODO: include the last event
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        // Extract data
        Intent intent = getIntent();
        String outcome = intent.getStringExtra(FightActivity.OUTCOME_KEY);
        mooseHp = intent.getIntExtra(PlayActivity.MOOSE_KEY, 0);
        playerHp = intent.getIntExtra(PlayActivity.PLAYER_KEY, 0);
        int turnCounter = intent.getIntExtra(FightActivity.TURN_COUNTER_KEY, 0);

        // Set outcome
        TextView text = findViewById(R.id.outcomeText);
        TextView score = findViewById(R.id.turnText);
        switch (outcome){
            case "tie":
                text.setText("IT'S A\nTIE");
                if (turnCounter == 1) score.setText("You survived one turn");
                else score.setText("You survived " + turnCounter + " turns");
                break;

            case "win":
                text.setText("YOU\nWON");
                if (turnCounter == 1) score.setText("You won in one turn!"); //that's not possible
                else score.setText("You won in " + turnCounter + " turns");
                break;

            case "loss":
                text.setText("YOU\nLOST");
                if (turnCounter == 1) score.setText("You survived one turn");
                else score.setText("You survived " + turnCounter + " turns");
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
        finish();
    }

    public void sharePressed (View view){
        //TODO: social media integration
    }

}
