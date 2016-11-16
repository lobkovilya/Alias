package ru.nsu.fit.lobkov.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements GameModel.WordChangedHandler,
                                                               GameModel.PointsChagedHandler {

    private TextView wordView;
    private TextView pointsView;
    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        if (intent.hasExtra(GameModel.GAME_MODEL_TAG)) {
            gameModel = (GameModel)intent.getSerializableExtra(GameModel.GAME_MODEL_TAG);
            gameModel.setWordChangedHandler(this);
            gameModel.setPointsChagedHandler(this);
        }
        wordView = (TextView)findViewById(R.id.wordTextView);
        pointsView = (TextView)findViewById(R.id.pointsTextView);
        pointsView.setText("Points: " + gameModel.getCurrentTeamPoints());
        gameModel.nextWord();
    }

    @Override
    public void onWordChanged(String newWord) {
        wordView.setText(newWord);
    }

    @Override
    public void onPointsChanged(int newValue) {
        pointsView.setText("Points: " + newValue);
    }

    public void onGuessedBtnClicked(View v) {
        gameModel.onTeamGuessedWord();
    }

    public void onNextWordBtnClicked(View v) {
        gameModel.nextWord();
    }
}
