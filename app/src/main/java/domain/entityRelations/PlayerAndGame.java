package domain.entityRelations;

import androidx.room.Embedded;
import androidx.room.Relation;

import domain.GameEntity;
import domain.PlayerEntity;

public class PlayerAndGame {

    @Embedded
    public PlayerEntity player;
    @Relation(
            parentColumn = "playerId",
            entityColumn = "playerId"
    )
    public GameEntity game;
}
