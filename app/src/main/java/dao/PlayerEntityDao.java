package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import domain.PlayerEntity;

@Dao
public interface PlayerEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPlayers(PlayerEntity... players);
    @Insert
    long insertPlayer(PlayerEntity user);

    @Query("SELECT * FROM players WHERE id = :userId")
    PlayerEntity getPlayerById(int userId);
}
