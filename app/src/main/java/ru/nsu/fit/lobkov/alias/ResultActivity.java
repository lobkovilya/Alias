package ru.nsu.fit.lobkov.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        if (intent.hasExtra(GameModel.GAME_MODEL_TAG)) {
            gameModel = (GameModel)intent.getSerializableExtra(GameModel.GAME_MODEL_TAG);
        }

        TextView rounds = (TextView)findViewById(R.id.roundTextView);
        TextView currentTeam = (TextView)findViewById(R.id.currentTeamTextView);
        TextView firstTeamResult = (TextView)findViewById(R.id.firstTeamResultTextView);
        TextView secondTeamResult = (TextView)findViewById(R.id.secondTeamResultTextView);

        rounds.setText("Round " + gameModel.getCurrentRound());
        currentTeam.setText("Next move: " + gameModel.getCurrentTeam());
        firstTeamResult.setText(gameModel.getFirstTeamName() + ": " + gameModel.getFirstTeamPoints());
        secondTeamResult.setText(gameModel.getSecondTeamName() + ": " + gameModel.getSecondTeamPoints());
    }

    public void onStartBtnClicked(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameModel.GAME_MODEL_TAG, gameModel);
        startActivity(intent);
    }
}
