package ru.nsu.fit.lobkov.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent intent = getIntent();
        String winner = intent.getStringExtra("winner");

        TextView winnerView = (TextView)findViewById(R.id.winnerTextView);
        winnerView.setText(winner);
    }

    public void onNewGameClicked(View v) {
        GameModel gameModel = GameModel.getInstance();
//        gameModel.prepareToNewGame();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
