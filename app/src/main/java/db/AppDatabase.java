package db;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import dao.GameEntityDao;
import dao.JokerEntityDao;
import dao.PlayerEntityDao;
import dao.QuestionEntityDao;
import domain.GameEntity;
import domain.JokerEntity;
import domain.PlayerEntity;
import domain.QuestionEntity;

@Database(entities = {GameEntity.class, JokerEntity.class, PlayerEntity.class, QuestionEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameEntityDao gameEntityDao();
    public abstract JokerEntityDao jokerEntityDao();
    public abstract PlayerEntityDao playerEntityDao();
    public abstract QuestionEntityDao questionEntityDao();

}