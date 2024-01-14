package com.first.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import androidx.room.Room;
import db.AppDatabase;

public class App extends Application {
    private AppDatabase db;

    private static  AppDatabase dbInstance;

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "millionaire"
            ).build();
        }
    }

    public static AppDatabase getDb() {
        return dbInstance;
    }

    public static Context getAppContext() {
        return App.context;
    }

}
