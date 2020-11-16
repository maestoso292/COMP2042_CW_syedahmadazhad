package frogger.world.levels;

import frogger.actor.*;

/**
 * LevelOne is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelOne extends Level {
    /**
     * Creates a new instance of LevelOne with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelOne(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogTypes.SHORT, 0, Section.THREE.getY(), 0.75));
        add(new Log(Log.LogTypes.SHORT , 220, Section.THREE.getY(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 440, Section.THREE.getY(), 0.75));

        add(new WetTurtle(600, Section.FOUR.getY(), -1));
        add(new WetTurtle(400, Section.FOUR.getY(), -1));
        add(new WetTurtle(200, Section.FOUR.getY(), -1));

        add(new Log(Log.LogTypes.LONG, 0, Section.FIVE.getY(), -2));
        add(new Log(Log.LogTypes.LONG, 400, Section.FIVE.getY(), -2));

        add(new Log(Log.LogTypes.SHORT, 50, Section.SIX.getY(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 270, Section.SIX.getY(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 490, Section.SIX.getY(), 0.75));

        add(new Turtle(500, Section.SEVEN.getY(), -1));
        add(new Turtle(300, Section.SEVEN.getY(), -1));
        add(new WetTurtle(700, Section.SEVEN.getY(), -1));

        add(new Car(Car.CarTypes.RED, 0, Section.TEN.getY(), -5));

        add(new Truck(Truck.TruckTypes.LONG, 0, Section.ELEVEN.getY(), 1));
        add(new Truck(Truck.TruckTypes.LONG, 500, Section.ELEVEN.getY(), 1));

        add(new Car(Car.CarTypes.RED, 100, Section.TWELVE.getY(), -1));
        add(new Car(Car.CarTypes.RED, 250, Section.TWELVE.getY(), -1));
        add(new Car(Car.CarTypes.RED, 400, Section.TWELVE.getY(), -1));
        add(new Car(Car.CarTypes.RED, 550, Section.TWELVE.getY(), -1));

        add(new Truck(Truck.TruckTypes.SHORT, 0, Section.THIRTEEN.getY(), 1));
        add(new Truck(Truck.TruckTypes.SHORT, 300, Section.THIRTEEN.getY(), 1));
        add(new Truck(Truck.TruckTypes.SHORT, 600, Section.THIRTEEN.getY(), 1));
    }
}
