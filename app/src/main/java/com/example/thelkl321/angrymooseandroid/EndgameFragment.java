package com.example.thelkl321.angrymooseandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EndgameFragment extends Fragment {

    private TextView text, score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_endgame, container, false);
    }

    //TODO: include the last event
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Assign views
        text = getView().findViewById(R.id.outcomeText);
        score = getView().findViewById(R.id.turnText);
    }

    public void setValues(String outcome, int turnCounter){
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

            case "surrender":
                text.setText("YOU\nLOST");
                score.setText("You ran away like a baby");
                break;
        }
    }
}
