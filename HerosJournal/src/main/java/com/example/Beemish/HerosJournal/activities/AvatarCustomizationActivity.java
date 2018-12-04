package com.example.Beemish.HerosJournal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.Beemish.HerosJournal.R;
import com.example.Beemish.HerosJournal.helpers.SettingsHelper;
import com.example.Beemish.HerosJournal.helpers.UserDBHelper;
import com.example.Beemish.HerosJournal.models.UserModel;

public class AvatarCustomizationActivity extends AppCompatActivity {

    private ImageView avatar, avatarWeapon, avatarShirt, avatarHelmet, avatarBackground;
    UserDBHelper userDBHelper;

    //column is skin color, row is eye color
    private int drawableArray[][]= {
            {R.drawable.avatar_white_plaine_eyes, R.drawable.avatar_white_big_eyes, R.drawable.avatar_white_sparkle_eyes,R.drawable.avatar_white_sleepy_eyes,},
            {R.drawable.avatar_skincolor_plain_eyes, R.drawable.avatar_skincolor_big_eyes, R.drawable.avatar_skincolor_sparkle_eyes,R.drawable.avatar_skincolor_sleepy_eyes,},
            {R.drawable.avatar_dark_plain_eyes, R.drawable.avatar_dark_big_eyes, R.drawable.avatar_dark_sparkle_eyes,R.drawable.avatar_dark_sleepy_eyes,},
            {R.drawable.avatar_green_plain_eyes, R.drawable.avatar_green_big_eyes, R.drawable.avatar_green_sparkle_eyes,R.drawable.avatar_green_sleepy_eyes,}

    };
    private int rowIndex = 0;
    private int columnIndex = 0;

    private int weaponArray[] = {R.drawable.gear_sword_gold, R.drawable.gear_sword_iron, R.drawable.gear_sword_wooden};
    private int helmetArray[] = {R.drawable.gear_helment_bucket,R.drawable.gear_helment_nicehat,R.drawable.gear_helment_witchhatt };
    private int shirtArray[] = {R.drawable.gear_shirt_blue, R.drawable.gear_shirt_green, R.drawable.gear_shirt_orange};
    private int backgroundArray[] = {R.drawable.avatar_bg, R.drawable.background_sunset, R.drawable.background_forest};

    private int weaponIndex = 0;
    private int helmetIndex = 0;
    private int shirtIndex = 0;
    private int backgroundIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_customization);
        SettingsHelper.applyTheme(this);

        setTitle(getString(R.string.sprite_customization_title));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar)); // Custom tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SettingsHelper.applyThemeToolbar((Toolbar)findViewById(R.id.toolbar), this);

        loadAvatarImage();
    }

    private void loadAvatarImage() {
        userDBHelper = new UserDBHelper(this);
        avatar = (ImageView)findViewById(R.id.avatarCustomization);
        avatarWeapon = findViewById(R.id.avatarCustomizationSword);
        avatarHelmet = findViewById(R.id.avatarCustomizationHelment);
        avatarShirt = findViewById(R.id.avatarCustomizationShirt);
        avatarBackground = findViewById(R.id.avatarCustomizationBackground);

        UserModel userModel = userDBHelper.fetchUser("root");
        avatar.setImageResource(userModel.getUserAvatar());
        avatarWeapon.setImageResource(userModel.getUserWeaponValue());
        avatarHelmet.setImageResource(userModel.getUserHelmetValue());
        avatarShirt.setImageResource(userModel.getUserShirtValue());
        avatarBackground.setImageResource(userModel.getUserBackgroundValue());

        //set indices to correct positions according to
        for (int i = 0; i < weaponArray.length; ++i) {
            if (userModel.getUserWeaponValue() == weaponArray[i]) { weaponIndex = i; };
        }
        for (int i = 0; i < helmetArray.length; ++i) {
            if (userModel.getUserHelmetValue() == helmetArray[i]) { helmetIndex = i; };
        }
        for (int i = 0; i < shirtArray.length; ++i) {
            if (userModel.getUserShirtValue() == shirtArray[i]) { shirtIndex = i; };
        }
        for (int i = 0; i < backgroundArray.length; ++i) {
            if (userModel.getUserBackgroundValue() == backgroundArray[i]) { backgroundIndex = i; };
        }
        for (int i = 0; i < drawableArray.length; ++i) {
            for (int j = 0; j < drawableArray[i].length; ++j) {
                if (userModel.getUserAvatar() == drawableArray[i][j]) {
                    columnIndex = i;
                    rowIndex = j;
                }
            }
        }
    }

    private void setAvatarImage() {
        userDBHelper = new UserDBHelper(this);

        //set logged in user
        UserModel userModel = userDBHelper.fetchUser(userDBHelper.fetchUser("root").getUserPassword());
        userModel.setUserAvatar(drawableArray[columnIndex][rowIndex]);
        userModel.setUserWeaponValue(weaponArray[weaponIndex]);
        userModel.setUserHelmetValue(helmetArray[helmetIndex]);
        userModel.setUserShirtValue(shirtArray[shirtIndex]);
        userModel.setUserBackgroundValue(backgroundArray[backgroundIndex]);
        userDBHelper.saveUser(userModel);

        //Set root user
        userModel = userDBHelper.fetchUser("root");
        userModel.setUserAvatar(drawableArray[columnIndex][rowIndex]);
        userModel.setUserWeaponValue(weaponArray[weaponIndex]);
        userModel.setUserHelmetValue(helmetArray[helmetIndex]);
        userModel.setUserShirtValue(shirtArray[shirtIndex]);
        userModel.setUserBackgroundValue(backgroundArray[backgroundIndex]);
        userDBHelper.saveUser(userModel);

        loadAvatarImage();
    }

    public void onClick(View view) {
        //userDBHelper = new UserDBHelper(this);
        switch (view.getId()) {
            case R.id.skinColorNextButton:
                if (columnIndex == drawableArray.length - 1) {
                    columnIndex = 0;
                } else ++columnIndex;
                setAvatarImage();
                break;
            case R.id.skinColorPreviousButton:
                if (columnIndex == 0) {
                    columnIndex = drawableArray.length - 1;
                } else --columnIndex;
                setAvatarImage();
                break;
            case R.id.eyeStyleNextButton:
                if (rowIndex == drawableArray[columnIndex].length - 1) {
                    rowIndex = 0;
                } else ++rowIndex;
                setAvatarImage();
                break;
            case R.id.eyeStylePreviousButton:
                if (rowIndex == 0) {
                    rowIndex = drawableArray[columnIndex].length - 1;
                } else ++rowIndex;
                setAvatarImage();
                break;
            case R.id.weaponStyleNextButton:
                if (weaponIndex == weaponArray.length - 1) weaponIndex = 0; else ++weaponIndex;
                setAvatarImage();
                break;
            case R.id.weaponStylePreviousButton:
                if (weaponIndex == 0) weaponIndex = weaponArray.length - 1; else --weaponIndex;
                setAvatarImage();
                break;
            case R.id.shirtStyleNextButton:
                if (shirtIndex == shirtArray.length - 1) shirtIndex = 0; else ++shirtIndex;
                setAvatarImage();
                break;
            case R.id.shirtStylePreviousButton:
                if (shirtIndex == 0) shirtIndex = shirtArray.length - 1; else --shirtIndex;
                setAvatarImage();
                break;
            case R.id.helmetStyleNextButton:
                if (helmetIndex == helmetArray.length - 1) helmetIndex = 0; else ++helmetIndex;
                setAvatarImage();
                break;
            case R.id.helmetStylePreviousButton:
                if (helmetIndex == 0) helmetIndex = helmetArray.length - 1; else --helmetIndex;
                setAvatarImage();
                break;
            case R.id.bgStyleNextButton:
                if (backgroundIndex == backgroundArray.length - 1) backgroundIndex = 0; else ++backgroundIndex;
                setAvatarImage();
                break;
            case R.id.bgStylePreviousButton:
                if (backgroundIndex == 0) backgroundIndex = backgroundArray.length - 1; else --backgroundIndex;
                setAvatarImage();
                break;
        }
    }
}
