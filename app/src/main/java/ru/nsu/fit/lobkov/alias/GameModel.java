package ru.nsu.fit.lobkov.alias;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lobkov on 16.11.2016.
 */

public class GameModel implements Serializable{
    private String firstTeamName;
    private String secondTeamName;

    private Map<Team, Integer> pointMap = new HashMap<>();

    private final int ROUNDS = 2;
    private int currentRound = 0;
    private Team currentTeam = Team.SECOND_TEAM;

    private WordChangedHandler wordChangedHandler = null;
    private PointsChangedHandler pointsChangedHandler = null;
    private GameEndsHandler gameEndsHandler = null;

    private DataGetter dataGetter;

    public static class GameModelHolder {
        public static final GameModel HOLDER_INSTANCE = new GameModel();
    }

    public static GameModel getInstance() {
        return GameModelHolder.HOLDER_INSTANCE;
    }

    public GameModel() {
        pointMap.put(Team.FIRST_TEAM, 0);
        pointMap.put(Team.SECOND_TEAM, 0);
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
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

    public void setPointsChangedHandler(PointsChangedHandler pointsChangedHandler) {
        this.pointsChangedHandler = pointsChangedHandler;
    }

    public void setGameEndsHandler(GameEndsHandler gameEndsHandler) {
        this.gameEndsHandler = gameEndsHandler;
    }

    public void onTeamGuessedWord() {
        Integer currentPoints = pointMap.get(currentTeam);
        pointMap.put(currentTeam, currentPoints + 1);
        if (pointsChangedHandler != null) {
            pointsChangedHandler.onPointsChanged(currentPoints + 1);
        }
        nextWord();
    }

    public void nextWord() {
        if (wordChangedHandler != null) {
            wordChangedHandler.onWordChanged(dataGetter.getNextWord());
        }

    }

    public void changeTurn() {
        if (currentTeam == Team.FIRST_TEAM) {
            currentTeam = Team.SECOND_TEAM;
        } else {
            currentTeam = Team.FIRST_TEAM;
            ++currentRound;
            Log.v("TAG", "Current round " + currentRound);
            if (currentRound > ROUNDS) {
                Log.v("TAG", "Boom");
                String winner;
                if (pointMap.get(Team.FIRST_TEAM) > pointMap.get(Team.SECOND_TEAM)) {
                    winner = firstTeamName;
                } else {
                    winner = secondTeamName;
                }
                if (gameEndsHandler != null) {
                    gameEndsHandler.onGameEnds(winner);
                }
            }
        }
    }

    public void prepareToNewGame(Context ctx) {
        dataGetter = new SQLDataGetter(ctx);
        currentRound = 0;
        currentTeam = Team.SECOND_TEAM;
        pointMap.put(Team.FIRST_TEAM, 0);
        pointMap.put(Team.SECOND_TEAM, 0);
    }

    private enum Team {
        FIRST_TEAM,
        SECOND_TEAM
    }

    public interface WordChangedHandler {
        void onWordChanged(String newWord);
    }

    public interface PointsChangedHandler {
        void onPointsChanged(int newValue);
    }

    public interface GameEndsHandler {
        void onGameEnds(String winner);
    }

}
