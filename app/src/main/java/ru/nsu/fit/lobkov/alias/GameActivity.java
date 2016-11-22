package ru.nsu.fit.lobkov.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements
        GameModel.WordChangedHandler,
        GameModel.PointsChangedHandler,
        GameModel.GameEndsHandler {

    private TextView wordView;
    private TextView pointsView;
    private TextView timerView;
    private GameModel gameModel;

    private Timer timer = new Timer();
    private SecondsHolder secondsHolder = new SecondsHolder();
    private class SecondsHolder {
        Integer seconds = 10;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();

        gameModel = GameModel.getInstance();
        gameModel.setWordChangedHandler(this);
        gameModel.setPointsChangedHandler(this);
//        gameModel.setGameEndsHandler(this);

        wordView = (TextView)findViewById(R.id.wordTextView);
        pointsView = (TextView)findViewById(R.id.pointsTextView);
        timerView = (TextView)findViewById(R.id.timerTextView);
        pointsView.setText("Points: " + gameModel.getCurrentTeamPoints());
        wordView.setText(gameModel.getCurrentWord());
        timer.schedule(new Task(), 0, 1000);
    }

    @Override
    public void onWordChanged(String newWord) {
        wordView.setText(newWord);
    }

    @Override
    public void onPointsChanged(int newValue) {
        pointsView.setText("Points: " + newValue);
    }

    public void onTimerTick(int secondsRemaining) {
        timerView.setText(String.valueOf(secondsRemaining));
        if (secondsHolder.seconds == 0) {
//            gameModel.changeTurn();
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }

    public void onGuessedBtnClicked(View v) {
        gameModel.onTeamGuessedWord();
    }

    public void onNextWordBtnClicked(View v) {
        gameModel.nextWord();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", secondsHolder.seconds);
        timer.cancel();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        secondsHolder.seconds = savedInstanceState.getInt("seconds");
    }

    @Override
    public void onGameEnds(String winner) {
//        Log.v("TAG", "onGameEnds!!!");
//        Intent intent = new Intent(this, WinnerActivity.class);
//        intent.putExtra("winner", winner);
//        startActivity(intent);
    }

    private class Task extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GameActivity.this.onTimerTick(secondsHolder.seconds--);
                }
            });
        }
    }
}
