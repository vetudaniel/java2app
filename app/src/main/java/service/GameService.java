package service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.first.myapplication.App;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.GameEntityDao;
import dao.JokerEntityDao;
import dao.PlayerEntityDao;
import db.AppDatabase;
import domain.GameEntity;
import domain.JokerEntity;
import domain.QuestionEntity;
import service.interfaces.FetchGameCallback;
import service.interfaces.FetchQuestionCallback;

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
                GameEntity game = new GameEntity(1);
                JokerEntity joker = new JokerEntity(false);
                game.setPlayerId(playerDao.getPlayerById(playerId).getPlayerId());
                long gameId = gameDao.insertGame(game);
                joker.setGameId((int)gameId);
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

    public void getGameByPlayerId (String playerId, FetchGameCallback callback){
        ListenableFuture<GameEntity> futureGame = gameDao.getGameByPlayerId(playerId);
        Futures.addCallback(futureGame, new FutureCallback<GameEntity>() {
            @Override
            public void onSuccess(@Nullable GameEntity game) {
                if (game != null) {
                    callback.onGameFetched(game);
                } else {
                    callback.onError(new Exception("Game not found"));
                }
            }
            @Override
            public void onFailure(Throwable t) {
                callback.onError(t);
            }
        }, Executors.newSingleThreadExecutor());

    }

}
