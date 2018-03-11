package com.example.thelkl321.angrymooseandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EndgameFragment extends Fragment {

    private TextView outcomeText, scoreText, lastEventText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_endgame, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Assign views
        outcomeText = getView().findViewById(R.id.outcomeText);
        scoreText = getView().findViewById(R.id.turnText);
        lastEventText = getView().findViewById(R.id.lastEventText);
    }

    public void setValues(String outcome, String lastEvent, int score){

        String lossOneScoreText, lossMoreScoreText, winOneScoreText, winMoreScoreText;
        if (MainActivity.gamemode.equals(MainActivity.TIME_GAMEMODE)) {
            lossOneScoreText = "You had one second left";
            lossMoreScoreText = "You had " + " seconds left";
            winOneScoreText = "You had one second left!";
            winMoreScoreText = lossMoreScoreText;
        } else {
            lossOneScoreText = "You survived one turn";
            lossMoreScoreText = "You survived " + score + " turns";
            winOneScoreText = "You won in one turn!";
            winMoreScoreText = "You won in " + score + " turns";
        }


        lastEventText.setText(lastEvent);
        switch (outcome){
            case "tie":
                outcomeText.setText("IT'S A\nTIE");
                if (score == 1) this.scoreText.setText(lossOneScoreText);
                else this.scoreText.setText(lossMoreScoreText);
                break;

            case "win":
                outcomeText.setText("YOU\nWON");
                if (score == 1) this.scoreText.setText(winOneScoreText); //that's not possible
                else this.scoreText.setText(winMoreScoreText);
                break;

            case "loss":
                outcomeText.setText("YOU\nLOST");
                if (score == 1) this.scoreText.setText(lossOneScoreText);
                else this.scoreText.setText(lossMoreScoreText);
                break;

            case "surrender":
                outcomeText.setText("YOU\nLOST");
                this.scoreText.setText("You ran away like a baby");
                lastEventText.setText("The moose will laugh about you with his friends");
                break;
        }
    }
}
