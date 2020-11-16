package frogger.highscore;

/**
 * The Highscore class provides a way for the Frogger game to store highscores with a name attached to a score value.
 */
public class Highscore implements Comparable<Highscore> {
    /**
     * Field to store the name.
     */
    private final String name;
    /**
     * Field to store the score value.
     */
    private final int score;

    /**
     * Creates an empty Highscore with empty String as a name and 0 as default score value.
     */
    public Highscore() {
        this("", 0);
    }

    /**
     * Creates a Highscore with the specified name and score value.
     * @param name Specifies the name of the person who achieved the highscore in the game.
     * @param score Specifies the score value.
     */
    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * A method for comparing two Highscores based on score value.
     * @param anotherHighscore The other Highscore instance to compare to.
     * @return <ul>
     *     <li>A positive number if this instance has a greater score value than that in the argument Highscore</li>
     *     <li>0 if this instance has a score value equal to that in the argument Highscore</li>
     *     <li>A negative number if this instance has a lesser score value than that in the argument Highscore</li>
     * </ul>
     */
    @Override
    public int compareTo(Highscore anotherHighscore) {
        return Integer.compare(this.score, anotherHighscore.getScore());
    }

    /**
     * Get the name of the person who achieved this Highscore.
     * @return A String representing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the score value of this Highscore.
     * @return An int representing the score value.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a String object representing this Highscore's name and score value. The score value is converted
     * to signed decimal representation.
     * @return A String representation of this Highscore
     */
    @Override
    public String toString() {
        return name + "," + score;
    }
}
