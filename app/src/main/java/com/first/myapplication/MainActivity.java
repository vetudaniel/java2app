package com.first.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.GameEntity;
import domain.PlayerEntity;
import domain.QuestionEntity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "VolleyExample";
    private static final String API_URL = "https://api.api-ninjas.com/v1/quotes";

    public void disable(View v) {
        v.setEnabled(false);
        GameEntity game = new GameEntity(0);
        PlayerEntity player = new PlayerEntity("John");
        List<QuestionEntity> questions = new ArrayList<QuestionEntity>();
        questions.add(new QuestionEntity("qwe?",  "asd",  "qwe", 2));

        game.startGame(player, questions);

        System.out.println(game);
    }

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
        getData();
    }
}