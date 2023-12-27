package domain;

import static com.google.gson.internal.$Gson$Types.arrayOf;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "games",foreignKeys = @ForeignKey(entity = PlayerEntity.class,
        parentColumns = "playerId",
        childColumns = "playerId",
        onDelete = ForeignKey.CASCADE))
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    public int gameId;

    public String playerId;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "round=" + round +
                '}';
    }
}
