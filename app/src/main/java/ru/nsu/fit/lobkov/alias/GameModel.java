package ru.nsu.fit.lobkov.alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lobkov on 16.11.2016.
 */

public class GameModel implements Serializable{
    public final static String GAME_MODEL_TAG = "game_model";
    private String firstTeamName;
    private String secondTeamName;
    private ArrayList<String> wordList = new ArrayList<>(Arrays.asList("hello", "world", "planet", "war", "stone", "android"));
    private int cursor = 0;

    private Map<Team, Integer> pointMap = new HashMap<>();

    private final int ROUNDS = 5;
    private int currentRound = 1;
    private Team currentTeam = Team.FIRST_TEAM;

    private WordChangedHandler wordChangedHandler = null;
    private PointsChagedHandler pointsChagedHandler = null;

    public GameModel(String firstTeamName, String secondTeamName) {
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;

        pointMap.put(Team.FIRST_TEAM, 0);
        pointMap.put(Team.SECOND_TEAM, 0);
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public int getFirstTeamPoints() {
        return pointMap.get(Team.FIRST_TEAM);
    }

    public int getSecondTeamPoints() {
        return pointMap.get(Team.SECOND_TEAM);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public String getCurrentTeam() {
        if (currentTeam == Team.FIRST_TEAM) {
            return firstTeamName;
        } else {
            return secondTeamName;
        }
    }

    public int getCurrentTeamPoints() {
        return pointMap.get(currentTeam);
    }

    public void setWordChangedHandler(WordChangedHandler wordChangedHandler) {
        this.wordChangedHandler = wordChangedHandler;
    }

    public void setPointsChagedHandler(PointsChagedHandler pointsChagedHandler) {
        this.pointsChagedHandler = pointsChagedHandler;
    }

    public void onTeamGuessedWord() {
        Integer currentPoints = pointMap.get(currentTeam);
        pointMap.put(currentTeam, currentPoints + 1);
        if (pointsChagedHandler != null) {
            pointsChagedHandler.onPointsChanged(currentPoints + 1);
        }
        nextWord();
    }

    public void nextWord() {
        ++cursor;
        if (cursor == wordList.size()) {
            cursor = 0;
        }
        if (wordChangedHandler != null) {
            wordChangedHandler.onWordChanged(wordList.get(cursor));
        }
    }

    private enum Team {
        FIRST_TEAM,
        SECOND_TEAM
    }

    public interface WordChangedHandler {
        void onWordChanged(String newWord);
    }

    public interface PointsChagedHandler {
        void onPointsChanged(int newValue);
    }
}
