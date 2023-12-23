package com.first.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.QuestionEntityDao;
import db.AppDatabase;
import domain.QuestionEntity;
import service.APIService;
import service.QuestionsService;

public class MainActivity extends AppCompatActivity {

    RoomDatabase.Callback dbCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button12 = findViewById(R.id.button12);
        APIService apiService = APIService.getInstance();
        QuestionsService qs = QuestionsService.getInstance();
       //getApplicationContext().deleteDatabase("millionaire");
       qs.saveQuestions(getAssets());

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}