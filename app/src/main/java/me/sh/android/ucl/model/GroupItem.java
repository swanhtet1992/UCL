package me.sh.android.ucl.model;

/**
 * Created by SH on 05/May/2014.
 */
public class GroupItem {
    public String date;
    public MatchItem match;
    public String time;
    public String stadium;

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getStadium() {
        return this.stadium;
    }

    public MatchItem getMatch() {
        return this.match;
    }
}