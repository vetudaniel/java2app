package domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import domain.typeconverters.AnswersListConverter;

@Entity(tableName = "questions")
public class QuestionEntity {
    private int questionId;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "question")
    private final String question;
    @TypeConverters(AnswersListConverter.class)
    public List<String> answers;

    private final int correctAnswer;
    private int difficulty;

    public QuestionEntity(String question, List<String> answers,int correctAnswer, int difficulty) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", answers='" + answers + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", difficulty=" + difficulty +
                '}';
    }
}
