package todo.herosjournal.herosjournal;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login Activity: ";
    private EditText loginEditEmail, loginEditPassword;
    private Button loginButton, createButton;

    private Realm realm;
    private MyRealmObject myRealmObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        realm = Realm.getDefaultInstance();

        loginEditEmail = findViewById(R.id.emailEditText);
        loginEditPassword = findViewById(R.id.passwordEditText);
        createButton = findViewById(R.id.createButton);
        loginButton = findViewById(R.id.loginButton);

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
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private boolean checkUser(String email,String password) {
        RealmResults<MyRealmObject> realmObjects = realm.where(MyRealmObject.class).findAll();
        for (MyRealmObject myRealmObject : realmObjects) {
            if (email.equals(myRealmObject.getEmail()) && password.equals(myRealmObject.getPassword())) {
                Log.e(TAG, myRealmObject.getEmail());
                return true;
            }
        }

        Log.e(TAG, String.valueOf(realm.where(MyRealmObject.class).contains("Email", email)));
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
