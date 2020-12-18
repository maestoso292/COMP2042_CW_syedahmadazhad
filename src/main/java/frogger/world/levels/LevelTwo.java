package frogger.world.levels;

import frogger.actor.*;

/**
 * LevelTwo is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelTwo extends Level {
    /**
     * Creates a new instance of LevelTwo with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelTwo(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogType.LONG, 250, Section.THREE.getY(), -2.5));

        add(new Turtle(0, Section.FOUR.getY(), 1));
        add(new Turtle(300, Section.FOUR.getY(), 1));
        add(new WetTurtle(500, Section.FOUR.getY(), 1, true));

        add(new Turtle(100, Section.FIVE.getY(), -1.75));
        add(new Turtle(400, Section.FIVE.getY(), -1.75));

        add(new WetTurtle(150, Section.SIX.getY(), 2, true));
        add(new WetTurtle(350, Section.SIX.getY(), 2, true));
        add(new WetTurtle(550, Section.SIX.getY(), 2, true));

        add(new Log(Log.LogType.MEDIUM, 0, Section.SEVEN.getY(), -3));
        add(new Log(Log.LogType.MEDIUM, 300, Section.SEVEN.getY(), -3));

        add(new Turtle(-200, Section.EIGHT.getY(), 2));
        add(new Turtle(100, Section.EIGHT.getY(), 2));
        add(new WetTurtle(400, Section.EIGHT.getY(), 2, true));

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();

        add(new Car(Car.CarType.RED, 0, Section.TEN.getY(), 1));
        add(new Car(Car.CarType.RED, 200, Section.TEN.getY(), 1));
        add(new Car(Car.CarType.WHITE, 500, Section.TEN.getY(), 1));

        add(new Car(Car.CarType.WHITE, 100, Section.ELEVEN.getY(), 4));
        add(new Car(Car.CarType.WHITE, 450, Section.ELEVEN.getY(), 4));

        add(new Truck(Truck.TruckType.SHORT, 0, Section.TWELVE.getY(), -2));
        add(new Truck(Truck.TruckType.SHORT, 300, Section.TWELVE.getY(), -2));

        add(new Car(Car.CarType.WHITE, 250, Section.THIRTEEN.getY(), -6));
    }
}
