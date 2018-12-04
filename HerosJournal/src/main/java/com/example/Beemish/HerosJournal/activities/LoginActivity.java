package com.example.Beemish.HerosJournal.activities;

// Written by Alex Helfrich

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Beemish.HerosJournal.R;
import com.example.Beemish.HerosJournal.activities.MyRealmObject;
import com.example.Beemish.HerosJournal.activities.RealmInitApplication;
import com.example.Beemish.HerosJournal.helpers.UserDBHelper;
import com.example.Beemish.HerosJournal.models.UserModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login Activity: ";
    private EditText loginEditEmail, loginEditPassword;
    private Button loginButton, createButton;

    private Realm realm;
    private com.example.Beemish.HerosJournal.activities.MyRealmObject myRealmObject;

    private UserDBHelper userDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //realm = Realm.getDefaultInstance();

        loginEditEmail = findViewById(R.id.emailEditText);
        loginEditPassword = findViewById(R.id.passwordEditText);
        createButton = findViewById(R.id.createButton);
        loginButton = findViewById(R.id.loginButton);

        //Root user contains the current avatar, this is done to avoid having to send
        //extras in intents to every activity in order to load the correct avatar image
        initRootUser();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginEditEmail.length() == 0) {
                    showSnackBar("Enter EMAIL");
                    loginEditEmail.requestFocus();
                } else if (loginEditPassword.length() == 0) {
                    showSnackBar("Enter password");
                    loginEditPassword.requestFocus();
                } else if (checkUser(loginEditEmail.getText().toString(),
                        loginEditPassword.getText().toString())){
                    //String usernameExtra = loginEditPassword.getText().toString();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    //i.putExtra("username", usernameExtra);
                    startActivity(i);
                }
            }
        });
    }

    private void initRootUser() {
        userDBHelper = new UserDBHelper(this);
        if (!userDBHelper.userExists("root")) {
            UserModel userModel = new UserModel(1, "root", "");
            userDBHelper.addNewUser(userModel);
        }
    }

    private boolean checkUser(String email,String password) {
        /*RealmResults<MyRealmObject> realmObjects = realm.where(MyRealmObject.class).findAll();
        for (MyRealmObject myRealmObject : realmObjects) {
            if (email.equals(myRealmObject.getEmail()) && password.equals(myRealmObject.getPassword())) {
                Log.e(TAG, myRealmObject.getEmail());
                return true;
            }
        }*/

        //Log.e(TAG, String.valueOf(realm.where(MyRealmObject.class).contains("Email", email)));

        //SQL version
        userDBHelper = new UserDBHelper(this);
        if (userDBHelper.userExists(email) && userDBHelper.fetchUser(email).getUserPassword().equals(password)) {
            //set root.avatar = user.avatar
            //set root.password = user.password
            UserModel userModel = new UserModel(1, "root", userDBHelper.fetchUser(email).getUserEmail(),
                    userDBHelper.fetchUser(email).getUserAvatar(),
                    userDBHelper.fetchUser(email).getUserWeaponValue(),
                    userDBHelper.fetchUser(email).getUserHelmetValue(),
                    userDBHelper.fetchUser(email).getUserShirtValue(),
                    userDBHelper.fetchUser(email).getUserBackgroundValue());
            userDBHelper.saveUser(userModel);

            return true;
        }

        return false;
    }

    private void showSnackBar(String msg) {
        try {
            Snackbar.make(findViewById(R.id.loginActivityView), msg, Snackbar.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
