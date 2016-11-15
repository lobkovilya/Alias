package ru.nsu.fit.lobkov.alias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CommandsActivity extends AppCompatActivity {
    private final String LOG_TAG = CommandsActivity.class.getSimpleName();
    private CommandAdapter mCommandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands);
        ListView commandListView = (ListView)findViewById(R.id.commands_listview);
        ArrayList<String> commandNames = new ArrayList<>();
        mCommandAdapter = new CommandAdapter(this, commandNames);
        commandListView.setAdapter(mCommandAdapter);
    }

    public void onNewCommandButtonClicked(View v) {
        mCommandAdapter.addItem();
    }

    public void onNextButtonClicked(View v) {
        String s = "";
        for (int i = 0; i < mCommandAdapter.getCount(); i++) {
            s = s.concat((String)mCommandAdapter.getItem(i));
            s = s.concat(" ");
        }
        Log.v(LOG_TAG, s);
    }
}
