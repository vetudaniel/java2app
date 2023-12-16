package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import domain.PlayerEntity;

@Dao
public interface PlayerEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPlayers(PlayerEntity... players);
}
