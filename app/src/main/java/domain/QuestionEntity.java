package domain;

public class QuestionEntity {
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
}
