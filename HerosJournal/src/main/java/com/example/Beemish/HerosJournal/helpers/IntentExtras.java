package com.example.Beemish.HerosJournal.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentExtras {
    public static final String GITHUB_URL="https://github.com/CoriBeemish/Heros-Journal";

    //find on github
    public static void findOnGithub(Context context){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URL));
        context.startActivity(intent);
    }
}
