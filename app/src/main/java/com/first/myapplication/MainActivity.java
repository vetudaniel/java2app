package com.first.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.QuestionEntityDao;
import db.AppDatabase;
import domain.QuestionEntity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "VolleyExample";
    private static final String API_URL = "https://api.api-ninjas.com/v1/quotes";
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




    public String getApiKey(){
        try{
            PackageManager packageManager = getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle metadata = appInfo.metaData;
            String apiKey = metadata.getString("API_KEY");
            return apiKey;
        } catch(PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "No Api Key Found";
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the JSON response
                        Log.d(TAG, response.toString());
                        // Process the response data here
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e(TAG, "Error: " + error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", getApiKey());
                return headers;
            }
        };

        queue.add(jsonRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button12 = findViewById(R.id.button12);

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });

        getData();
    }

    public void addQuestion(){
        AppDatabase database = ((DBSingleton) getApplication()).getDb();

        ExecutorService eS =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                QuestionEntityDao questionsDao = database.questionEntityDao();
                questionsDao.insertQuestions(new QuestionEntity("asd?", "category", "yes", 4));
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