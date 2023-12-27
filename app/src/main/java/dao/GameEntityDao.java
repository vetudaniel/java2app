package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import domain.GameEntity;
import service.GameService;

@Dao
public interface GameEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertGames(GameEntity... games);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertGame(GameEntity game);

}
