package domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class PlayerEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String name;
    private boolean hasWon;
    private boolean hasLost;
    private static int score;

    public PlayerEntity(String name) {
        this.name = name;
        this.hasWon = false;
        this.hasLost = false;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
