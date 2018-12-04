package com.example.Beemish.HerosJournal.activities;

import android.app.Application;

import com.example.Beemish.HerosJournal.adapters.PendingTodoAdapter;

public class GlobalStats extends Application
{

    private int playerHealth =0;

    public int GetPlayerHealth()
    {
        return playerHealth;
    }

    public void SetPlayerHealth(int playerhealth)
    {
        this.playerHealth =playerhealth;
    }

}