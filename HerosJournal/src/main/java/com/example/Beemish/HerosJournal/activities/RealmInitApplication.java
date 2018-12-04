package com.example.Beemish.HerosJournal.activities;

//Written by Alex Helfrich

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmInitApplication extends Application {
    private final String TAG = "Todo List App: ";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("todo.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        Log.i(TAG, "Realm Initialized");
    }
}
