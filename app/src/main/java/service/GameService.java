package service;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.first.myapplication.App;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.GameEntityDao;
import dao.JokerEntityDao;
import dao.PlayerEntityDao;
import db.AppDatabase;
import domain.GameEntity;
import domain.JokerEntity;
import domain.PlayerEntity;
import service.interfaces.FetchPlayerCallback;

public class GameService {

    private static final Context context = App.getAppContext();
    private static final GameService instance = new GameService();

    private static final AppDatabase db = App.getDb();

    private static final GameEntityDao gameDao = db.gameEntityDao();

    private static final PlayerEntityDao playerDao = db.playerEntityDao();
    private static final JokerEntityDao jokerDao = db.jokerEntityDao();

    public static GameService getInstance() {
        return instance;
    }

    private GameService() {
    }


    public void startGame(String playerId) {
        ExecutorService eS = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        eS.execute(new Runnable() {
            @Override
            public void run() {
                GameEntity game = new GameEntity(0);
                JokerEntity joker = new JokerEntity(false);
                game.setPlayerId(playerDao.getPlayerById(playerId).getPlayerId());
                long gameid = gameDao.insertGame(game);
                joker.setGameId((int)gameid);
                jokerDao.inserJoker(joker);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Started Game");
                    }
                });
            }
        });

        eS.shutdown();

    }

}
