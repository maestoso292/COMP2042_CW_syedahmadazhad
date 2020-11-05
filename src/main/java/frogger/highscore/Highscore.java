package frogger.highscore;

public class Highscore implements Comparable<Highscore> {
    private final String name;
    private final int score;

    public Highscore() {
        this("", 0);
    }

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Highscore o) {
        return Integer.compare(this.score, o.getScore());
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + "," + score;
    }
}
