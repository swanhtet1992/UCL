package me.sh.android.ucl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by SH on 05/May/2014.
 */

@DatabaseTable(tableName = "GroupItem")
public class GroupItem {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField public int groupNum;
    @DatabaseField public String date;
    @DatabaseField (foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    public MatchItem match;
    @DatabaseField public String time;
    @DatabaseField public String stadium;

    public GroupItem() {
    }

    public GroupItem(int groupNum, String date, MatchItem match, String time, String stadium) {
        this.groupNum = groupNum;
        this.date = date;
        this.match = match;
        this.time = time;
        this.stadium = stadium;
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getGroupId() {
        return groupNum;
    }

    public void setGroupId(int groupId) {
        this.groupNum = groupId;
    }

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