package service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.first.myapplication.App;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.QuestionEntityDao;
import db.AppDatabase;
import domain.QuestionEntity;
import service.interfaces.FetchPlayerCallback;

public class QuestionsService {
    private static final Context context = App.getAppContext();
    //Initialize the service only once
    private static final QuestionsService instance = new QuestionsService();

    private static final AppDatabase db = App.getDb();

    private static final QuestionEntityDao questionsDao = db.questionEntityDao();

    public static QuestionsService getInstance(){
        return instance;
    }
    private QuestionsService(){}

    public void saveQuestions(AssetManager as) {
        try{
            InputStream inputStream = as.open("questions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<QuestionEntity>>() {}.getType();
            List<QuestionEntity> questionEntities = gson.fromJson(jsonString, listType);
            for (QuestionEntity q : questionEntities){
                Random random = new Random();
                //   Generate a random integer between 1 and 3
                int randomNumber = random.nextInt(3) + 1;
                q.setDifficulty(randomNumber);
            }
            new Thread(() -> {
               questionsDao.saveAll(questionEntities);
            }).start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


   /* public QuestionEntity jsonToQuestion(JSONObject jsonObject) throws JSONException {
        Random random = new Random();
        // Generate a random integer between 1 and 5
        int randomNumber = random.nextInt(5) + 1;
        String question = jsonObject.getString("question");
        String category = jsonObject.getString("category");
        String answer = jsonObject.getString("answer");

        QuestionEntity q = new QuestionEntity(question, category, answer, randomNumber);
        return q;
    }

    public void save(QuestionEntity question){
        AppDatabase database = App.getDb();
        ExecutorService eS =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                questionsDao.insertQuestions(question);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Saved to DB");
                    }
                });
            }
        });

    }*/

    public void kkk (FetchPlayerCallback callback){
        ListenableFuture<QuestionEntity> futureQuestion = questionsDao.getFirstDifficultyQuestion();
        Futures.addCallback(futureQuestion, new FutureCallback<QuestionEntity>() {
            @Override
            public void onSuccess(@Nullable QuestionEntity user) {
                if (user != null) {
                    callback.onQuestionFetched(user);
                } else {
                    // Handle the case when user is null (e.g., user not found)
                    callback.onError(new Exception("User not found"));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t);
            }
        }, Executors.newSingleThreadExecutor());

    }
}
