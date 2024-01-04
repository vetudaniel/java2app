package service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.first.myapplication.App;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

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

    public void playerWon(String playerId){
        ExecutorService eS =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                PlayerEntity player = playerDao.getPlayerById(playerId);
                player.setHasWon(true);
            }
        });
        eS.shutdown();
    }

    public void playerLost(String playerId){
        ExecutorService eS =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                PlayerEntity player = playerDao.getPlayerById(playerId);
                player.setHasLost(true);
            }
        });
        eS.shutdown();
    }

    public boolean exists(String player) {
        ExecutorService eS = Executors.newSingleThreadExecutor();
        FutureTask<Boolean> futureTask = new FutureTask<>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return playerDao.playerExists(player);
            }
        });
        eS.execute(futureTask);
        eS.shutdown();
        try {
            return futureTask.get(); // This will block until the result is available
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

}
