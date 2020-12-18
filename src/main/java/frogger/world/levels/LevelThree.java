package frogger.world.levels;

import frogger.actor.*;

/**
 * LevelThree is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelThree extends Level {
    /**
     * Creates a new instance of LevelThree with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelThree(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogType.SHORT, 0, Section.THREE.getY(), 1));
        add(new Log(Log.LogType.SHORT, 350, Section.THREE.getY(), 1));

        add(new WetTurtle(500, Section.FOUR.getY(), -1.25, true));
        add(new Turtle(300, Section.FOUR.getY(), -1.25));
        add(new WetTurtle(100, Section.FOUR.getY(), -1.25, true));

        add(new Turtle(150, Section.FIVE.getY(), 0.75));
        add(new WetTurtle(350, Section.FIVE.getY(), 0.75, true));
        add(new WetTurtle(550, Section.FIVE.getY(), 0.75, true));

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();

        add(new Car(Car.CarType.RED, 0, Section.SEVEN.getY(), -5));

        add(new Truck(Truck.TruckType.LONG, 0, Section.EIGHT.getY(), 1.5));
        add(new Truck(Truck.TruckType.LONG, 400, Section.EIGHT.getY(), 1.5));

        add(new Truck(Truck.TruckType.SHORT, 0, Section.NINE.getY(), -1.5));
        add(new Truck(Truck.TruckType.SHORT, 200, Section.NINE.getY(), -1.5));
        add(new Truck(Truck.TruckType.SHORT, 450, Section.NINE.getY(), -1.5));

        add(new Car(Car.CarType.RED, 0, Section.TEN.getY(), 1));
        add(new Car(Car.CarType.WHITE, 120, Section.TEN.getY(), 1));
        add(new Car(Car.CarType.RED, 300, Section.TEN.getY(), 1));
        add(new Car(Car.CarType.WHITE, 450, Section.TEN.getY(), 1));

        add(new Truck(Truck.TruckType.LONG, 200, Section.ELEVEN.getY(), 4));

        add(new Car(Car.CarType.WHITE, 100, Section.TWELVE.getY(), -3));
        add(new Car(Car.CarType.RED, 400, Section.TWELVE.getY(), -3));

        add(new Truck(Truck.TruckType.SHORT, 0, Section.THIRTEEN.getY(), 2));
        add(new Truck(Truck.TruckType.SHORT, 300, Section.THIRTEEN.getY(), 2));
    }
}
