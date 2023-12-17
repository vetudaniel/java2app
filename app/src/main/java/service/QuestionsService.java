package service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.first.myapplication.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class QuestionsService {
    private static final Context context = App.getAppContext();
    //Initialize the service only once
    private static final QuestionsService instance = new QuestionsService();

    public static QuestionsService getInstance(){
        return instance;
    }
    private QuestionsService(){}

    public QuestionEntity jsonToQuestion(JSONObject jsonObject) throws JSONException {
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
                QuestionEntityDao questionsDao = database.questionEntityDao();
                questionsDao.insertQuestions(question);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Saved to DB");
                    }
                });
            }
        });

    }


}
