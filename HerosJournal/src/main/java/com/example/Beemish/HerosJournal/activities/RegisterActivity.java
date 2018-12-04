package com.example.Beemish.HerosJournal.activities;

//Written by Alex Helfrich

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Beemish.HerosJournal.R;
import com.example.Beemish.HerosJournal.helpers.TagDBHelper;
import com.example.Beemish.HerosJournal.helpers.UserDBHelper;
import com.example.Beemish.HerosJournal.models.UserModel;

import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class RegisterActivity extends AppCompatActivity {
    private Realm realm;
    private MyRealmObject myRealmObject;
    private EditText registerEmail, registerPassword, confirmPassword;

    private UserDBHelper userDBHelper;

    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        realm = realm.getDefaultInstance();

        registerEmail = findViewById(R.id.registerEmailEditText);
        registerPassword = findViewById(R.id.registerPasswordEditText);
        confirmPassword = findViewById(R.id.confirmEditText);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registerEmail.length() == 0 ) {
                    showSnackBar("Enter Email");
                } else if (registerPassword.length() == 0) {
                    showSnackBar("Enter password");
                } else if (!registerPassword.getText().toString().equals(
                        confirmPassword.getText().toString())) {
                    showSnackBar("Passwords Do Not Match");
                } else {
                    try {
                        /*realm.beginTransaction();

                        myRealmObject = realm.createObject(MyRealmObject.class,
                                registerEmail.getText().toString());
                        //myRealmObject.setEmail(registerEmail.getText().toString());
                        myRealmObject.setPassword(registerPassword.getText().toString());

                        realm.commitTransaction();

                        showSnackBar("Account Created");
*/
                        //SQL version

                        createNewUser();


                        finish();
                    } catch (RealmPrimaryKeyConstraintException e) {
                        e.printStackTrace();
                        showSnackBar("User already exists");
                    }
                }
            }
        });
    }

    private void createNewUser() {
        userDBHelper = new UserDBHelper(this);
        UserModel userModel = new UserModel(userDBHelper.countUsers() + 1, registerEmail.getText().toString(), registerPassword.getText().toString());
        userDBHelper.addNewUser(userModel);
    }

    private void showSnackBar(String msg) {
        try {
            Snackbar.make(findViewById(R.id.registerActivityView), msg, Snackbar.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
