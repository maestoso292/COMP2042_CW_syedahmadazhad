package frogger.world.levels;

import frogger.actor.Turtle;
import frogger.actor.WetTurtle;

/**
 * LevelSeven is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelSeven extends Level{
    /**
     * Creates a new instance of LevelSeven with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelSeven(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Turtle(0, Section.THREE.getY(), 7));

        add(new Turtle(0, Section.FOUR.getY(), -2));
        add(new Turtle(300, Section.FOUR.getY(), -2));

        add(new WetTurtle(-50, Section.FIVE.getY(), 1.5, false));
        add(new WetTurtle(250, Section.FIVE.getY(), 1.5, false));
        add(new Turtle(500, Section.FIVE.getY(), 1.5));

        add(new Turtle(245, Section.SIX.getY(), -2));
        add(new Turtle(475, Section.SIX.getY(), -2));

        add(new WetTurtle(0, Section.SEVEN.getY(), 1, true));
        add(new WetTurtle(130, Section.SEVEN.getY(), 1, true));
        add(new WetTurtle(360, Section.SEVEN.getY(), 1, true));
        add(new WetTurtle(490, Section.SEVEN.getY(), 1, true));

        add(new WetTurtle(250, Section.EIGHT.getY(), 2, false));
        add(new WetTurtle(0, Section.EIGHT.getY(), 2, false));

        add(new Turtle(60, Section.NINE.getY(), -1));
        add(new Turtle(450, Section.NINE.getY(), -1));

        add(new Turtle(0, Section.TEN.getY(), 1));
        add(new Turtle(200, Section.TEN.getY(), 1));
        add(new Turtle(500, Section.TEN.getY(), 1));

        add(new WetTurtle(500, Section.ELEVEN.getY(), -1.25, false));
        add(new Turtle(300, Section.ELEVEN.getY(), -1.25));
        add(new WetTurtle(100, Section.ELEVEN.getY(), -1.25, false));

        add(new Turtle(40, Section.TWELVE.getY(), 3));
        add(new Turtle(300, Section.TWELVE.getY(), 3));

        add(new WetTurtle(150, Section.THIRTEEN.getY(), 0.75, false));
        add(new WetTurtle(350, Section.THIRTEEN.getY(), 0.75, false));
        add(new WetTurtle(550, Section.THIRTEEN.getY(), 0.75, false));

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();
    }
}
