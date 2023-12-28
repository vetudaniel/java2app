package com.first.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import dao.PlayerEntityDao;
import db.AppDatabase;
import domain.GameEntity;
import domain.PlayerEntity;
import domain.QuestionEntity;
import service.GameService;
import service.PlayerService;
import service.QuestionsService;
import service.interfaces.FetchGameCallback;
import service.interfaces.FetchQuestionCallback;

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
        String playerName = intent.getStringExtra("username");
        String playerId = intent.getStringExtra("playerId");
        gs.startGame(playerId);
        TextView question = findViewById(R.id.question);
        TextView player = findViewById(R.id.playerName);
        TextView round = findViewById(R.id.round);
        TextView currentPrize = findViewById(R.id.currentPrize);
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);
        qs.getQuestionsByDifficulty(1, new FetchQuestionCallback() {
            @Override
            public void onQuestionFetched(QuestionEntity questionEntity) {
                question.setText(questionEntity.getQuestion());
                for(int i = 0; i < questionEntity.getAnswers().size(); ++i){
                    answer1.setText(questionEntity.getAnswers().get(0));
                    answer2.setText(questionEntity.getAnswers().get(1));
                    answer3.setText(questionEntity.getAnswers().get(2));
                    answer4.setText(questionEntity.getAnswers().get(3));
                }
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(questionEntity.getAnswers().get(0));
                        if(questionEntity.getCorrectAnswer() == questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(0))){
                            System.out.println("GZ. Correct Answer. Moving to Round 2");
                        }
                        System.out.println("Wrong answer.Game over");
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(questionEntity.getCorrectAnswer() ==  questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(1))){
                            System.out.println("GZ. Correct Answer. Moving to Round 2");
                        }
                        System.out.println("Wrong answer.Game over");
                    }
                });
                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(questionEntity.getCorrectAnswer() == questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(2))){
                            System.out.println("GZ. Correct Answer. Moving to Round 2");
                        }
                        System.out.println("Wrong answer.Game over");
                    }
                });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(questionEntity.getCorrectAnswer() == questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(3))){
                            System.out.println("GZ. Correct Answer. Moving to Round 2");
                        }
                        System.out.println("Wrong answer.Game over");
                    }
                });
            }
            @Override
            public void onError(Throwable t) {

            }
        });
        player.setText("Username: " + playerName);

        gs.getGameByPlayerId(playerId, new FetchGameCallback() {
            @Override
            public void onGameFetched(GameEntity gameEntity) {
                round.setText("Current Round: " + gameEntity.getRound());
                gameEntity.setCurrentPrize(gameEntity.getRound() * 100000);
                currentPrize.setText("Current Prize: $" + gameEntity.getCurrentPrize());
            }
            @Override
            public void onError(Throwable t) {

            }
        });




    }
}