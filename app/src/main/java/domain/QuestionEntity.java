package domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class QuestionEntity {
    @PrimaryKey(autoGenerate = true)
    private int questionId;
    private final String question;
    private final String category;
    private final String answer;
    private final int difficulty;

    public QuestionEntity(String question, String category, String answer, int difficulty) {
        this.question = question;
        this.category = category;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public String getAnswer() {
        return answer;
    }

    public int getDifficulty(){
        return difficulty;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id=" + questionId +
                ", question='" + question + '\'' +
                ", category='" + category + '\'' +
                ", answer='" + answer + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
