package ru.nsu.fit.lobkov.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TeamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
    }

    public void onNextBtnClicked(View v) {
        EditText firstTeamEditText = (EditText)findViewById(R.id.firstTeamEditText);
        EditText secondTeamEditText = (EditText)findViewById(R.id.secondTeamEditText);


        GameModel model = GameModel.getInstance();
        model.setFirstTeamName(firstTeamEditText.getText().toString());
        model.setSecondTeamName(secondTeamEditText.getText().toString());

        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra(GameModel.GAME_MODEL_TAG, model);
        startActivity(intent);
    }
}
