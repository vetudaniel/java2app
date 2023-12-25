package com.first.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import domain.PlayerEntity;
import service.PlayerService;


public class MainActivity extends AppCompatActivity {
    PlayerService ps = PlayerService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGame = findViewById(R.id.startGame);
        TextView userName = findViewById(R.id.userName);

        userName.setSelectAllOnFocus(true);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                PlayerEntity newPlayer = new PlayerEntity(user);
                ps.save(newPlayer);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });
    }


}