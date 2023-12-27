package domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity(tableName = "jokers", foreignKeys = @ForeignKey(entity = GameEntity.class,
        parentColumns = "gameId",
        childColumns = "gameId",
        onDelete = ForeignKey.CASCADE))
public class JokerEntity {
    @PrimaryKey(autoGenerate = true)
    private int jokerId;
    @ColumnInfo(name = "isUsed")
    private boolean isUsed;

    private int gameId;

    public JokerEntity(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public int getJokerId() {
        return jokerId;
    }

    public void setJokerId(int jokerId) {
        this.jokerId = jokerId;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
