package qdev.unb.utils;

public enum Difficulty {
    EASY(0, 20, 400, 250),
    NORMAL(1, 10, 200, 500),
    HARD(2, 5, 100, 1000);

    public final int index;
    public final int lives;
    public final int money;
    public final int killsToReach;

    Difficulty(int index, int lives, int money, int killsToReach) {
        this.index = index;
        this.lives = lives;
        this.money = money;
        this.killsToReach = killsToReach;
    }

    public static Difficulty fromIndex(int idx) {
        for (Difficulty d : values()) {
            if (d.index == idx) return d;
        }
        throw new IllegalArgumentException("Difficulté inconnue: " + idx);
    }

    public static Difficulty fromName(String name) {
        if (name == null) throw new IllegalArgumentException("Nom null");
        switch (name.toLowerCase()) {
            case "easy":
                return EASY;
            case "normal":
                return NORMAL;
            case "hard":
                return HARD;
            default:
                throw new IllegalArgumentException("Difficulté inconnue: " + name);
        }
    }
}
