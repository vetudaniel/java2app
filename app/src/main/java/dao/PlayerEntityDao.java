package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import domain.PlayerEntity;
import domain.QuestionEntity;
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

    @Query("SELECT EXISTS(SELECT 1 FROM players WHERE name = :name)")
    boolean playerExists(String name);
    @Update
    void updatePlayer(PlayerEntity player);


}
