package service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import domain.QuestionEntity;

public class APIService {
    private static final String API_URL = "https://api.api-ninjas.com/v1/trivia?limit=30";
    private static final String tag = "Apicall";
    private static final Context context = App.getAppContext();
    private static final QuestionsService qs = QuestionsService.getInstance();
//Initialize the service only once
    private static final APIService instance = new APIService();

    public String getApiKey(){
        try{
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metadata = appInfo.metaData;
            String apiKey = metadata.getString("API_KEY");
            return apiKey;
        } catch(PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "No Api Key Found";
    }

    public void  saveQuestions(){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the JSON response
                        Log.d(tag, response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                QuestionEntity question = qs.jsonToQuestion(obj);
                                qs.save(question);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e(tag, "Error: " + error.toString());
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
//private constructor so others cannot use it
    private APIService(){

    }
//returning only the created instance to the outside
    public static APIService getInstance(){
        return instance;
    }


}
