package service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.first.myapplication.App;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.PlayerEntityDao;
import dao.QuestionEntityDao;
import db.AppDatabase;
import domain.PlayerEntity;
import domain.QuestionEntity;
import service.interfaces.FetchPlayerCallback;

public class PlayerService {

    private static final Context context = App.getAppContext();
    private static final PlayerService instance = new PlayerService();
    private static final GameService gs = GameService.getInstance();

    private static final AppDatabase db = App.getDb();

    private static final PlayerEntityDao playerDao = db.playerEntityDao();

    public static PlayerService getInstance(){
        return instance;
    }
    private PlayerService(){}



    public void save(PlayerEntity player){
        ExecutorService eS =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                playerDao.insertPlayer(player);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Player Saved to DB");
                    }
                });
            }
        });
        eS.shutdown();
    }


}
