package com.example.Beemish.HerosJournal.models;

/**
 * Created by Beemish on 12/28/17.
 */

public class TagsModel {
    private int tagID;
    private String tagTitle;
    private int level; //attribute level calculated by ((exp/100) + 1);
    private int exp; //total progress

    public TagsModel(){}

    public TagsModel(String tagTitle) {
        this.tagTitle = tagTitle;
        this.level = 1;
        this.exp = 0;
    }

    public TagsModel(int tagID, String tagTitle) {
        this.tagID = tagID;
        this.tagTitle = tagTitle;
        this.level = 1;
        this.exp = 0;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public int getTagLevel() {return level;}

    public int getTagExp() {return exp;}

    public void setTagExp(int exp) {
        this.exp = exp;
        //recalculate level
        this.level = this.exp/100 + 1;
    }

    /*public void setTagLevel(int level) {
        this.level = level;
    }*/ //shouldn't be needed, as setting exp should set level
}
