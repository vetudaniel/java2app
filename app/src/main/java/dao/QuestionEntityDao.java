package dao;

import android.content.ContentProviderClient;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import domain.PlayerEntity;
import domain.QuestionEntity;

@Dao
public interface QuestionEntityDao {
    @Query("SELECT * FROM questions")
    List<QuestionEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertQuestions(QuestionEntity... questions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void saveAll(List<QuestionEntity> questions);

    @Query("SELECT * FROM questions WHERE difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    ListenableFuture<QuestionEntity> getQuestionsByDifficulty(int difficulty);


}
