package ru.nsu.fit.lobkov.alias;

import java.io.Serializable;

/**
 * Created by Lobkov on 16.11.2016.
 */

public class GameModel implements Serializable{
    private String firstTeamName;
    private String secondTeamName;

    private int firstTeamPoints = 0;
    private int secondTeamPoints = 0;

    private final int ROUNDS = 5;
    private int currentRound = 1;
    private Turn turn = Turn.FIRST_TEAM;

    public GameModel(String firstTeamName, String secondTeamName) {
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public int getFirstTeamPoints() {
        return firstTeamPoints;
    }

    public int getSecondTeamPoints() {
        return secondTeamPoints;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public String getCurrentTeam() {
        if (turn == Turn.FIRST_TEAM) {
            return firstTeamName;
        } else {
            return secondTeamName;
        }
    }
    private enum Turn {
        FIRST_TEAM,
        SECOND_TEAM
    }
}
