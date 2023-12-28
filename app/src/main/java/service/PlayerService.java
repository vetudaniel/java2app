package service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.first.myapplication.App;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.PlayerEntityDao;
import db.AppDatabase;
import domain.PlayerEntity;

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
