package domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class PlayerEntity {

    @PrimaryKey
    @NonNull
    private String playerId;
    private final String name;
    private boolean hasWon;
    private boolean hasLost;
    private static int score;

    public PlayerEntity(String playerId,String name) {
        this.playerId = playerId;
        this.name = name;
        this.hasWon = false;
        this.hasLost = false;
    }

    public String getName() {
        return name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public boolean isHasLost() {
        return hasLost;
    }

    public static int getScore() {
        return score;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public static void setScore(int score) {
        PlayerEntity.score = score;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "playerId='" + playerId + '\'' +
                ", name='" + name + '\'' +
                ", hasWon=" + hasWon +
                ", hasLost=" + hasLost +
                '}';
    }
}
