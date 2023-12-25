package com.first.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String receivedMessage = intent.getStringExtra("username");

        TextView textView = findViewById(R.id.qwe);
        textView.setText("Welcome " + receivedMessage + " to who wants to be a millionaire. Here is your first question for 100.000$");

    }
}