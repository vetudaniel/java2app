package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import domain.PlayerEntity;
import domain.entityRelations.PlayerAndGame;

@Dao
public interface PlayerEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPlayers(PlayerEntity... players);
    @Insert
    void insertPlayer(PlayerEntity user);

    @Query("SELECT * FROM players WHERE playerId = :playerId")
    PlayerEntity getPlayerById(String playerId);

    @Query("SELECT * FROM players WHERE playerId = :playerId")
    PlayerAndGame getPlayerAndGame(int playerId);
}
