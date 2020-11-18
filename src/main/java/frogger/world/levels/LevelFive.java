package frogger.world.levels;

import frogger.actor.Log;
import frogger.actor.Turtle;
import frogger.actor.WetTurtle;

/**
 * LevelFive is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelFive extends Level {
    /**
     * Creates a new instance of LevelFive with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelFive(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogTypes.SHORT, 0, Section.THREE.getY(), 7));

        add(new Log(Log.LogTypes.MEDIUM, 25, Section.FOUR.getY(), -1));
        add(new Log(Log.LogTypes.MEDIUM, 375, Section.FOUR.getY(), -1));

        add(new WetTurtle(-50, Section.FIVE.getY(), 1.5, true));
        add(new WetTurtle(250, Section.FIVE.getY(), 1.5, true));
        add(new Turtle(500, Section.FIVE.getY(), 1.5));

        add(new Turtle(245, Section.SIX.getY(), -2));
        add(new Turtle(475, Section.SIX.getY(), -2));

        add(new Log(Log.LogTypes.LONG, -150, Section.SEVEN.getY(), 1));
        add(new Log(Log.LogTypes.LONG, 300, Section.SEVEN.getY(), 1));

        add(new WetTurtle(250, Section.EIGHT.getY(), 2, true));
        add(new WetTurtle(0, Section.EIGHT.getY(), 2, true));

        add(new Log(Log.LogTypes.SHORT, 0, Section.NINE.getY(), -3));
        add(new Log(Log.LogTypes.SHORT, 400, Section.NINE.getY(), -3));

        add(new Log(Log.LogTypes.LONG, 350, Section.TEN.getY(), 3));

        add(new WetTurtle(500, Section.ELEVEN.getY(), -1.25, true));
        add(new Turtle(300, Section.ELEVEN.getY(), -1.25));
        add(new WetTurtle(100, Section.ELEVEN.getY(), -1.25, true));

        add(new Log(Log.LogTypes.SHORT, 0, Section.TWELVE.getY(), 1.5));
        add(new Log(Log.LogTypes.SHORT, 350, Section.TWELVE.getY(), 1.5));

        add(new WetTurtle(150, Section.THIRTEEN.getY(), 0.75, true));
        add(new WetTurtle(350, Section.THIRTEEN.getY(), 0.75, true));
        add(new WetTurtle(550, Section.THIRTEEN.getY(), 0.75, true));

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();
    }
}
