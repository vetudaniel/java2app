package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import domain.GameEntity;

@Dao
public interface GameEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGames(GameEntity... games);

}
