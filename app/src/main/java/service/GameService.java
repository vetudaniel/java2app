package service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.first.myapplication.App;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.GameEntityDao;
import dao.PlayerEntityDao;
import db.AppDatabase;
import domain.GameEntity;
import domain.PlayerEntity;

public class GameService {

    private static final Context context = App.getAppContext();
    private static final GameService instance = new GameService();

    private static final AppDatabase db = App.getDb();

    private static final GameEntityDao gameDao = db.gameEntityDao();

    public static GameService getInstance(){
        return instance;
    }
    private GameService(){}

    public void save(GameEntity game){
        ExecutorService eS =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                gameDao.insertGames(game);
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
