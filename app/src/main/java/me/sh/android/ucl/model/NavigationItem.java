package me.sh.android.ucl.model;

/**
 * Created by SH on 05/May/2014.
 */
public class NavigationItem {
    private String group_badge;
    private String group_name;
    private int group_badge_color;

    public void setGroupBadge(String s) {
        this.group_badge = s;
    }

    public String getGroupBadge() {
        return group_badge;
    }

    public void setGroupName(String s) {
        this.group_name = s;
    }

    public String getGroupName() {
        return group_name;
    }

    public void setGroupBadgeColor(int s) {
        this.group_badge_color = s;
    }

    public int getGroupBadgeColor() {
        return group_badge_color;
    }
}
