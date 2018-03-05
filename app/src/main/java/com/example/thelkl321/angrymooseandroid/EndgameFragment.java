package com.example.thelkl321.angrymooseandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EndgameFragment extends Fragment {

    private TextView outcomeText, score, lastEventText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_endgame, container, false);
    }

    //TODO: include the last event
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Assign views
        outcomeText = getView().findViewById(R.id.outcomeText);
        score = getView().findViewById(R.id.turnText);
        lastEventText = getView().findViewById(R.id.lastEventText);
    }

    public void setValues(String outcome, String lastEvent, int turnCounter){

        lastEventText.setText(lastEvent);
        switch (outcome){
            case "tie":
                outcomeText.setText("IT'S A\nTIE");
                if (turnCounter == 1) score.setText("You survived one turn");
                else score.setText("You survived " + turnCounter + " turns");
                break;

            case "win":
                outcomeText.setText("YOU\nWON");
                if (turnCounter == 1) score.setText("You won in one turn!"); //that's not possible
                else score.setText("You won in " + turnCounter + " turns");
                break;

            case "loss":
                outcomeText.setText("YOU\nLOST");
                if (turnCounter == 1) score.setText("You survived one turn");
                else score.setText("You survived " + turnCounter + " turns");
                break;

            case "surrender":
                outcomeText.setText("YOU\nLOST");
                score.setText("You ran away like a baby");
                lastEventText.setText("The moose will laugh about you with his friends");
                break;
        }
    }
}
