package com.first.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    static GameEntity newGame = new GameEntity(1);
    static int jokerCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String playerName = intent.getStringExtra("username");
        String playerId = intent.getStringExtra("playerId");
        TextView question = findViewById(R.id.question);
        TextView questionDifficultyText = findViewById(R.id.questionDifficulty);
        TextView player = findViewById(R.id.playerName);
        TextView round = findViewById(R.id.round);
        TextView currentPrize = findViewById(R.id.currentPrize);
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);
        Button joker = findViewById(R.id.joker);
        joker.setText("Use 50/50 : " + jokerCounter + "/2");
        player.setText("Username: " + playerName);
        round.setText("Current Round: " + newGame.getRound());
        newGame.setCurrentPrize(newGame.getRound() * 100000);
        currentPrize.setText("Current Prize: $" + newGame.getCurrentPrize());
        Random random = new Random();
        int questionDifficulty = random.nextInt(3) + 1;
        questionDifficultyText.setText("Question Difficulty: " + questionDifficulty);

        if(newGame.getRound() > 10){
            Intent gameWonIntent = new Intent(MainActivity2.this, GamWonActivity.class);
            overridePendingTransition(0, 0);
            startActivity(gameWonIntent);
            overridePendingTransition(0, 0);
            ps.playerWon(playerId);
        }
            qs.getQuestionsByDifficulty(questionDifficulty, new FetchQuestionCallback() {
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
                            if(questionEntity.getCorrectAnswer() != questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(0))){
                                Intent intent = new Intent(MainActivity2.this, GameOverActivity.class);
                                gs.saveGame(playerId, newGame);
                                newGame = new GameEntity(1);
                                jokerCounter = 0;
                                ps.playerLost(playerId);
                                startActivity(intent);
                            }else {
                                        newGame.setRound(newGame.getRound() + 1);
                                        newGame.setCurrentPrize(newGame.getRound() * 100000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                round.setText("Current Round: " + newGame.getRound());
                                                currentPrize.setText("Current Prize: $" + newGame.getCurrentPrize());
                                                finish();
                                                overridePendingTransition(0, 0);
                                                startActivity(getIntent());
                                                overridePendingTransition(0, 0);
                                            }
                                        });
                                    }


                            }

                    });
                    answer2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(questionEntity.getCorrectAnswer() != questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(1))){
                                gs.saveGame(playerId, newGame);
                                Intent intent = new Intent(MainActivity2.this, GameOverActivity.class);
                                jokerCounter = 0;
                                newGame = new GameEntity(1);
                                ps.playerLost(playerId);
                               startActivity(intent);
                            }else {
                                newGame.setRound(newGame.getRound() + 1);
                                newGame.setCurrentPrize(newGame.getRound() * 100000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        round.setText("Current Round: " + newGame.getRound());
                                        currentPrize.setText("Current Prize: $" + newGame.getCurrentPrize());
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                            }


                        }

                    });
                    answer3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(questionEntity.getCorrectAnswer() != questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(2))){
                                System.out.println(newGame.getRound());
                                gs.saveGame(playerId, newGame);
                                Intent intent = new Intent(MainActivity2.this, GameOverActivity.class);
                                newGame = new GameEntity(1);
                                ps.playerLost(playerId);
                                jokerCounter = 0;
                             startActivity(intent);
                            }else {
                                newGame.setRound(newGame.getRound() + 1);
                                newGame.setCurrentPrize(newGame.getRound() * 100000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        round.setText("Current Round: " + newGame.getRound());
                                        currentPrize.setText("Current Prize: $" + newGame.getCurrentPrize());
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                            }

                        }

                    });
                    answer4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(questionEntity.getCorrectAnswer() != questionEntity.getAnswers().indexOf(questionEntity.getAnswers().get(3))){
                                gs.saveGame(playerId, newGame);
                                Intent intent = new Intent(MainActivity2.this, GameOverActivity.class);
                                newGame = new GameEntity(1);
                                ps.playerLost(playerId);
                                jokerCounter = 0;
                          startActivity(intent);
                            }else {
                                newGame.setRound(newGame.getRound() + 1);
                                newGame.setCurrentPrize(newGame.getRound() * 100000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        round.setText("Current Round: " + newGame.getRound());
                                        currentPrize.setText("Current Prize: $" + newGame.getCurrentPrize());
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                            }


                        }

                    });

                    if(jokerCounter < 2){
                        joker.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                int correctAnswer = questionEntity.getCorrectAnswer();
                                List<Button> answerButtons = new ArrayList<>();
                                answerButtons.add(answer1);
                                answerButtons.add(answer2);
                                answerButtons.add(answer3);
                                answerButtons.add(answer4);
                                int removedCount = 0;
                                for(Button b : answerButtons){
                                    if( answerButtons.indexOf(b) != correctAnswer && removedCount < 2){
                                        b.setVisibility(View.GONE);
                                        removedCount++;
                                    }
                                }
                                jokerCounter++;
                                joker.setVisibility(View.GONE);
                            }
                        });
                    }
                    if(jokerCounter > 1){
                        joker.setVisibility(View.GONE);
                    }


                }
                @Override
                public void onError(Throwable t) {

                }
            });
        }

        }

