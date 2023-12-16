package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import domain.PlayerEntity;
import domain.QuestionEntity;

@Dao
public interface QuestionEntityDao {
    @Query("SELECT * FROM questions")
    List<QuestionEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertQuestions(QuestionEntity... questions);
}
