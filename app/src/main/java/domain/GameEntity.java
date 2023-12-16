package domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "games")
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "round")
    private int round;

    public GameEntity(int round) {
        this.round = round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "round=" + round +
                '}';
    }
}
