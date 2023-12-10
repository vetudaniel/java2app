package domain;

import java.util.ArrayList;
import java.util.List;

public class JokerEntity {
    private boolean isUsed;
    private final List<QuestionEntity> questions;

    public JokerEntity(boolean isUsed) {
        this.isUsed = isUsed;
        this.questions = new ArrayList<QuestionEntity>();
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }
}
