package frogger.world.levels;

import frogger.actor.Fireball;
import frogger.actor.Log;
import frogger.actor.Turtle;
import frogger.actor.WetTurtle;

/**
 * LevelNine is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelNine extends Level{
    /**
     * Creates a new instance of LevelNine with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelNine(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogTypes.LONG, 0, Level.Section.THREE.getY(), 1.5));
        add(new Log(Log.LogTypes.LONG, 440, Level.Section.THREE.getY(), 1.5));

        add(new Log(Log.LogTypes.SHORT, 200, Level.Section.FOUR.getY(), -4));
        add(new Log(Log.LogTypes.SHORT, 550, Level.Section.FOUR.getY(), -4));

        add(new Turtle(50, Level.Section.FIVE.getY(), -1.5));
        add(new WetTurtle(200, Level.Section.FIVE.getY(), -1.5, false));
        add(new Turtle(450, Level.Section.FIVE.getY(), -1.5));

        add(new WetTurtle(0, Section.SIX.getY(), 1, true));
        add(new WetTurtle(130, Section.SIX.getY(), 1, true));
        add(new WetTurtle(360, Section.SIX.getY(), 1, true));
        add(new WetTurtle(490, Section.SIX.getY(), 1, true));

        add(new Log(Log.LogTypes.MEDIUM, 200, Level.Section.SEVEN.getY(), -2.5));
        add(new Log(Log.LogTypes.MEDIUM, 550, Level.Section.SEVEN.getY(), -2.5));

        add(new Turtle(0, Section.EIGHT.getY(), -2));
        add(new WetTurtle(130, Section.EIGHT.getY(), -2, true));
        add(new Turtle(360, Section.EIGHT.getY(), -2));
        add(new WetTurtle(490, Section.EIGHT.getY(), -2, true));

        add(new Log(Log.LogTypes.SHORT, 200, Level.Section.NINE.getY(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 550, Level.Section.NINE.getY(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 550, Level.Section.NINE.getY(), 0.75));

        add(new Log(Log.LogTypes.LONG, 100, Level.Section.TEN.getY(), -0.75));
        add(new Log(Log.LogTypes.LONG, 550, Level.Section.TEN.getY(), -0.75));

        add(new WetTurtle(0, Section.ELEVEN.getY(), 2.5, false));
        add(new WetTurtle(250, Section.ELEVEN.getY(), 2.5, false));
        add(new WetTurtle(500, Section.ELEVEN.getY(), 2.5, false));


        add(new Turtle(0, Section.TWELVE.getY(), -1.5));
        add(new WetTurtle(300, Section.TWELVE.getY(), -1.5, false));

        add(new Log(Log.LogTypes.LONG, 100, Section.THIRTEEN.getY(), 1));
        add(new Log(Log.LogTypes.LONG, 550, Section.THIRTEEN.getY(), 1));


        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();

        add(new Fireball(50, Level.Section.THREE.getY(), -3));
        add(new Fireball(420, Level.Section.THREE.getY(), -3));

        add(new Fireball(0, Level.Section.SIX.getY(), -7));

        add(new Fireball(100, Level.Section.EIGHT.getY(), 2));
        add(new Fireball(400, Level.Section.EIGHT.getY(), 2));

        add(new Fireball(350, Level.Section.TEN.getY(), 5));

        add(new Fireball(200, Level.Section.THIRTEEN.getY(), -1.5));
        add(new Fireball(580, Level.Section.THIRTEEN.getY(), -1.5));
    }
}
