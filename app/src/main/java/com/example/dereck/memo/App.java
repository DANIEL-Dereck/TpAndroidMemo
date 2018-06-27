package com.example.dereck.memo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.dereck.memo.Helper.DatabaseHelper;
import com.example.dereck.memo.Manager.DatabaseManager;

public class App extends Application {
    private static final String LOGTAG = "Application";
    private static Context context;
    private static DatabaseHelper dbHelper;

    @Override
    public void onCreate() {
        Log.d(LOGTAG, "onCreate()");
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DatabaseHelper(context);
        DatabaseManager.initInstance(dbHelper);
    }

    public static Context getContext()
    {
        Log.d(LOGTAG, "getContext");
        return context;
    }
}
