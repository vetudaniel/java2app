package com.first.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import dao.PlayerEntityDao;
import domain.PlayerEntity;
import service.GameService;
import service.PlayerService;
import service.QuestionsService;


public class MainActivity extends AppCompatActivity {
    PlayerService ps = PlayerService.getInstance();
    QuestionsService qs = QuestionsService.getInstance();
    GameService gs = GameService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGame = findViewById(R.id.startGame);
        TextView userName = findViewById(R.id.userName);
        TextView userNameExists = findViewById(R.id.usernameTaken);
  //getApplicationContext().deleteDatabase("millionaire");
        qs.saveQuestions(getAssets());
        userName.setSelectAllOnFocus(true);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                if(ps.exists(name)){
                    userNameExists.setVisibility(View.VISIBLE);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            userNameExists.setVisibility(View.GONE);
                        }
                    }, 3000);
                } else {
                    String playerID = UUID.randomUUID().toString();
                    PlayerEntity newPlayer = new PlayerEntity(playerID, name);
                    ps.save(newPlayer);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("username", name);
                    intent.putExtra("playerId", playerID);
                    startActivity(intent);
                }
            }
        });
    }


}