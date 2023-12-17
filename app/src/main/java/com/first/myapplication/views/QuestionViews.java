package com.first.myapplication.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.first.myapplication.App;

import db.AppDatabase;

//TODO
public class QuestionViews extends AndroidViewModel {

    private AppDatabase myRoomDatabase;

    public QuestionViews(Application app) {
        super(app);
        myRoomDatabase = ((App) app).getDb();
    }
}