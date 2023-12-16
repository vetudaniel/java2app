package com.first.myapplication;

import android.app.Application;
import androidx.room.Room;
import db.AppDatabase;

public class DBSingleton extends Application {
    private AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "millionaire")
                .build();
    }

    public AppDatabase getDb() {
        return db;
    }
}
