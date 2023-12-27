package dao;

import androidx.room.Dao;
import androidx.room.Insert;

import domain.JokerEntity;

@Dao
public interface JokerEntityDao {

    @Insert
    void inserJoker(JokerEntity joker);
}
