package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import domain.GameEntity;
import domain.QuestionEntity;
import service.GameService;

@Dao
public interface GameEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGames(GameEntity... games);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertGame(GameEntity game);

    @Query("SELECT * FROM games WHERE playerId = :playerId ")
    ListenableFuture<GameEntity> getGameByPlayerId(String playerId);

}
