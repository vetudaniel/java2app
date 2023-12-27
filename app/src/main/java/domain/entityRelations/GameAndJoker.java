package domain.entityRelations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import domain.GameEntity;
import domain.JokerEntity;
import domain.QuestionEntity;

public class GameAndJoker {
    @Embedded
    public GameEntity game;
    @Relation(
            parentColumn = "gameId",
            entityColumn = "gameId"
    )
    public JokerEntity joker;
}
