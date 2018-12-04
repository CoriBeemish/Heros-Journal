package com.example.Beemish.HerosJournal.models;

import com.example.Beemish.HerosJournal.R;

public class UserModel {
    private int userID;
    private String userEmail;
    private String userPassword;
    private int userAvatarValue;
    private int userHelmetValue;
    private int userWeaponValue;
    private int userShirtValue;
    private int userBackgroundValue;

    public UserModel(int userID, String userEmail, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAvatarValue = R.drawable.avatar_white_plaine_eyes;
        this.userWeaponValue = R.drawable.gear_sword_wooden;
        this.userHelmetValue = R.drawable.gear_helment_bucket;
        this.userShirtValue = R.drawable.gear_shirt_blue;
        this.userBackgroundValue = R.drawable.background_sunset;
    }

    public UserModel(int userID, String userEmail, String userPassword, int userAvatarValue, int userWeaponValue,
                     int userHelmetValue, int userShirtValue, int userBackgroundValue) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAvatarValue = userAvatarValue;
        this.userWeaponValue = userWeaponValue;
        this.userHelmetValue = userHelmetValue;
        this.userShirtValue = userShirtValue;
        this.userBackgroundValue = userBackgroundValue;
    }

    public UserModel(String userEmail) {
        this.userEmail = userEmail;
        this.userAvatarValue = R.drawable.avatar_white_plaine_eyes;
        this.userWeaponValue = R.drawable.gear_sword_wooden;
        this.userHelmetValue = R.drawable.gear_helment_bucket;
        this.userShirtValue = R.drawable.gear_shirt_blue;
        this.userBackgroundValue = R.drawable.background_sunset;
    }

    public int getUserID() {return userID;}

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserAvatar() {return userAvatarValue;}

    public void setUserAvatar(int userAvatar) {this.userAvatarValue = userAvatar;}

    public String getUserEmail() {return userEmail;}

    public String getUserPassword() {return userPassword;}

    public boolean passwordIsEqual(String password) {
        if (password.equals(this.userPassword)) {
            return true;
        } return false;
    }

    public int getUserHelmetValue() {return userHelmetValue;}

    public int getUserWeaponValue() {return userWeaponValue;}

    public int getUserShirtValue() {return userShirtValue;}

    public int getUserBackgroundValue() {return userBackgroundValue;}

    public void setUserHelmetValue(int userHelmetValue) {
        this.userHelmetValue = userHelmetValue;
    }

    public void setUserWeaponValue(int userWeaponValue) {
        this.userWeaponValue = userWeaponValue;
    }

    public void setUserShirtValue(int userShirtValue) {
        this.userShirtValue = userShirtValue;
    }

    public void setUserBackgroundValue(int userBackgroundValue) {
        this.userBackgroundValue = userBackgroundValue;
    }
}
