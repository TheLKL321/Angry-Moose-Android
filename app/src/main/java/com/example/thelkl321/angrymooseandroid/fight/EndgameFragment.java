package com.example.thelkl321.angrymooseandroid.fight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thelkl321.angrymooseandroid.MainActivity;
import com.example.thelkl321.angrymooseandroid.R;

import java.util.Objects;

public class EndgameFragment extends Fragment {

    private TextView outcomeText, scoreText, lastEventText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_endgame, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Assign views
        outcomeText = Objects.requireNonNull(getView()).findViewById(R.id.outcomeText);
        scoreText = getView().findViewById(R.id.turnText);
        lastEventText = getView().findViewById(R.id.lastEventText);
    }

    void setValues(Outcome outcome, String lastEvent, int score) {
        String scoreTextLoss, scoreTextVictory;
        switch (MainActivity.gamemode) {
            case TIMEATTACK_GAMEMODE:
                if (score == 0) {
                    scoreTextLoss = getString(R.string.timeattack_score_out_of_time);
                    lastEvent = "";
                } else {
                    scoreTextLoss = getResources().getQuantityString(R.plurals.timeattack_score, score, score);
                }
                scoreTextVictory = scoreTextLoss;
                break;

            default:    // STANDARD_GAMEMODE
                scoreTextLoss = getResources().getQuantityString(R.plurals.standard_score_loss, score, score);
                scoreTextVictory = getResources().getQuantityString(R.plurals.standard_score_victory, score, score);
                break;
        }


        lastEventText.setText(lastEvent);
        switch (outcome) {
            case TIE:
                outcomeText.setText(getString(R.string.outcome_tie));
                this.scoreText.setText(scoreTextLoss);
                break;

            case VICTORY:
                outcomeText.setText(getString(R.string.outcome_victory));
                this.scoreText.setText(scoreTextVictory);
                break;

            case LOSS:
                outcomeText.setText(getString(R.string.outcome_loss));
                this.scoreText.setText(scoreTextLoss);
                break;

            case SURRENDER:
                outcomeText.setText(getString(R.string.outcome_loss));
                this.scoreText.setText(getString(R.string.surrender_score));
                lastEventText.setText(getString(R.string.surrender_score_last_event));
                break;
        }
    }
}
