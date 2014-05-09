package me.sh.android.ucl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by SH on 05/May/2014.
 */

@DatabaseTable(tableName = "MatchItem")
public class MatchItem {
    @DatabaseField(generatedId = true) public int id;
    @DatabaseField public String team_1;
    @DatabaseField public String team_2;
    @DatabaseField public String goal_1;
    @DatabaseField public String goal_2;
    @DatabaseField public String score = "";

    public MatchItem() {
    }

    public MatchItem(String team_1, String team_2, String goal_1, String goal_2, String score) {
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.goal_1 = goal_1;
        this.goal_2 = goal_2;
        this.score = score;
    }

    public String getTeam1() {
        return team_1;
    }

    public void setTeam1(String team_1) {
        this.team_1 = team_1;
    }

    public String getTeam2() {
        return team_2;
    }

    public void setTeam2(String team_2) {
        this.team_2 = team_2;
    }

    public String getGoal1() {
        return goal_1;
    }

    public void setGoal1(String goal_1) {
        this.goal_1 = goal_1;
    }

    public String getGoal2() {
        return goal_2;
    }

    public void setGoal2(String goal_2) {
        this.goal_2 = goal_2;
    }

    public void setScore() {
        score = goal_1 + " - " + goal_2;
    }

    public String getScore() {
        if (score.equalsIgnoreCase("")) {
            return score = "vs";
        } else {
            return score;
        }
    }
}