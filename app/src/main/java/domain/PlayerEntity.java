package domain;

public class PlayerEntity {
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
