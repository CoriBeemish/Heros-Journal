package todo.herosjournal.herosjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    TextView health, exp, mana, level;
    Integer healthNum,expNum,manaNum, maxHealth, maxMana, maxEXP, levelNum;

    private LinearLayout parentLinearLayout;
    //ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLinearLayout = findViewById(R.id.parent_linear_layout);
      //listView = findViewById(R.id.list);
        health = (TextView)findViewById(R.id.textView11);
        exp= (TextView)findViewById(R.id.textView17);
        mana= (TextView)findViewById(R.id.textView12);
        level=(TextView)findViewById(R.id.textView18);

        healthNum = 750; expNum = 25; manaNum = 350; maxHealth = 1000; maxMana = 600;maxEXP = 300; levelNum=1;


    }


    public void onAddTask(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        healthNum -= 75;
        manaNum -= 50;
        health.setText(healthNum.toString()+ "/" + maxHealth);
        mana.setText(manaNum.toString()+ "/" + maxMana);
        Toast.makeText(this, " You failed to completed this habit.....", Toast.LENGTH_SHORT).show();
        CheckStats();
    }
    public void onComplete (View v)
    {
        Toast.makeText(this, " You completed a habit!", Toast.LENGTH_SHORT).show();
        parentLinearLayout.removeView((View) v.getParent());
        healthNum += 50;
        manaNum += 35;
        expNum += 115;

        if(healthNum < maxHealth)
        {
            health.setText(healthNum.toString() + "/" + maxHealth);
        }

        if(manaNum < maxMana) {
            mana.setText(manaNum.toString() + "/" + maxMana);

        }

        if(expNum < maxEXP) {
            exp.setText(expNum.toString() + "/" + maxEXP);
        }
        CheckStats();
    }

    public void CheckStats()
    {
        if(healthNum > maxHealth){
            healthNum = maxHealth;
            health.setText(healthNum.toString() + "/" + maxHealth);
        }

        if(manaNum > maxMana){
            manaNum = maxMana;
            mana.setText(manaNum.toString() + "/" + maxMana);
        }

        if(expNum > maxEXP)
        {
            expNum = expNum-maxEXP;
            maxEXP = (maxEXP *2) + 15;
            maxMana +=50;
            maxHealth += 100;
            levelNum++;
            exp.setText(expNum.toString() + "/" + maxEXP);
            health.setText(healthNum.toString() + "/" + maxHealth);
            mana.setText(manaNum.toString() + "/" + maxMana);
            level.setText("LEVEL: " + levelNum.toString());
            Toast.makeText(this, "LEVEL UP! You are now level" + levelNum, Toast.LENGTH_LONG).show();
        }
        if(healthNum <= 0){
            healthNum = 0;
            health.setText(healthNum.toString() + "/" + maxHealth);
        }

        if(manaNum <= 0){
            manaNum = 0;
            mana.setText(manaNum.toString() + "/" + maxMana);
        }

    }


}

