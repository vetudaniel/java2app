package domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private static PlayerEntity player;
    private static List<QuestionEntity> questions;
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

    public GameEntity startGame(PlayerEntity player, List<QuestionEntity> questions){
        this.player = player;
        this.questions = questions;
        return this;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "round=" + round +
                '}';
    }
}
