package domain.entityRelations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import domain.JokerEntity;
import domain.QuestionEntity;

public class JokerAndQuestions {
    @Embedded
    public JokerEntity joker;
    @Relation(
            parentColumn = "jokerId",
            entityColumn = "jokerOwnerId"
    )
    public List<QuestionEntity> questions;
}
