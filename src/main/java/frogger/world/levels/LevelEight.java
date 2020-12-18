package frogger.world.levels;

import frogger.actor.Fireball;
import frogger.actor.Log;
import frogger.actor.Turtle;
import frogger.actor.WetTurtle;

/**
 * LevelEight is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelEight extends Level {
    /**
     * Creates a new instance of LevelEight with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelEight(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new WetTurtle(0, Section.THREE.getY(), -2.5, false));
        add(new WetTurtle(280, Section.THREE.getY(), -2.5, false));
        add(new WetTurtle(530, Section.THREE.getY(), -2.5, false));

        add(new Log(Log.LogType.MEDIUM, 200, Section.FOUR.getY(), 1.5));
        add(new Log(Log.LogType.MEDIUM, 550, Section.FOUR.getY(), 1.5));

        add(new Turtle(50, Section.FIVE.getY(), -2));
        add(new WetTurtle(200, Section.FIVE.getY(), -2, false));
        add(new Turtle(450, Section.FIVE.getY(), -2));

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();

        add(new Fireball(50, Section.FOUR.getY(), -3));

        add(new Fireball(0, Section.SEVEN.getY(), 3));
        add(new Fireball(250, Section.SEVEN.getY(), 3));
        add(new Fireball(450, Section.SEVEN.getY(), 3));

        add(new Fireball(100, Section.EIGHT.getY(), -3));
        add(new Fireball(350, Section.EIGHT.getY(), -3));
        add(new Fireball(550, Section.EIGHT.getY(), -3));

        add(new Fireball(100, Section.NINE.getY(), -5));

        add(new Fireball(350, Section.TEN.getY(), 5));

        add(new Fireball(100, Section.ELEVEN.getY(), -1.5));
        add(new Fireball(360, Section.ELEVEN.getY(), -1.5));
        add(new Fireball(580, Section.ELEVEN.getY(), -1.5));

        add(new Fireball(100, Section.TWELVE.getY(), -1.5));
        add(new Fireball(360, Section.TWELVE.getY(), -1.5));
        add(new Fireball(580, Section.TWELVE.getY(), -1.5));

        add(new Fireball(100, Section.THIRTEEN.getY(), -1.5));
        add(new Fireball(360, Section.THIRTEEN.getY(), -1.5));
        add(new Fireball(580, Section.THIRTEEN.getY(), -1.5));
    }
}
