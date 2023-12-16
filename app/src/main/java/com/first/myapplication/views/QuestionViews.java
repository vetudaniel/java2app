package com.first.myapplication.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.first.myapplication.DBSingleton;

import db.AppDatabase;

//TODO
public class QuestionViews extends AndroidViewModel {

    private AppDatabase myRoomDatabase;

    public QuestionViews(Application app) {
        super(app);
        myRoomDatabase = ((DBSingleton) app).getDb();
    }
}