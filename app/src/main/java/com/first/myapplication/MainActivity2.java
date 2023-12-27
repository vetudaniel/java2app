package com.first.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import dao.PlayerEntityDao;
import db.AppDatabase;
import domain.GameEntity;
import domain.PlayerEntity;
import domain.QuestionEntity;
import service.GameService;
import service.PlayerService;
import service.QuestionsService;
import service.interfaces.FetchPlayerCallback;

public class MainActivity2 extends AppCompatActivity {
    private static final AppDatabase db = App.getDb();
    private static final GameService gs = GameService.getInstance();
    private static final PlayerService ps = PlayerService.getInstance();
    private static final PlayerEntityDao playerDao = db.playerEntityDao();
    private static final QuestionsService qs = QuestionsService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String receivedMessage = intent.getStringExtra("username");
        String playerId = intent.getStringExtra("playerId");
        gs.startGame(playerId);
        TextView textView = findViewById(R.id.qwe);
        qs.kkk(new FetchPlayerCallback() {
            @Override
            public void onQuestionFetched(QuestionEntity questionEntity) {
                textView.setText(questionEntity.getQuestion() + "Welcome " + receivedMessage + " to who wants to be a millionaire. Here is your first question for 100.000$" + "your id is:" + playerId);
            }

            @Override
            public void onError(Throwable t) {

            }
        });


    }
}