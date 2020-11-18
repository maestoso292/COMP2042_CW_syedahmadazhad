package frogger.world.levels;

import frogger.actor.*;

/**
 * LevelFour is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelFour extends Level {
    /**
     * Creates a new instance of LevelFour with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelFour(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogTypes.SHORT, 0, Section.THREE.getY(), 7));

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();

        add(new Car(Car.CarTypes.WHITE, 100, Section.FIVE.getY(), -1.25));
        add(new Car(Car.CarTypes.WHITE, 250, Section.FIVE.getY(), -1.25));
        add(new Car(Car.CarTypes.RED, 400, Section.FIVE.getY(), -1.25));
        add(new Car(Car.CarTypes.RED, 550, Section.FIVE.getY(), -1.25));

        add(new Truck(Truck.TruckTypes.SHORT, -200, Section.SIX.getY(), -5));

        add(new Car(Car.CarTypes.WHITE, 0, Section.SEVEN.getY(), 3));
        add(new Car(Car.CarTypes.WHITE, 300, Section.SEVEN.getY(), 3));

        add(new Truck(Truck.TruckTypes.SHORT, -120, Section.EIGHT.getY(), .75));
        add(new Truck(Truck.TruckTypes.SHORT, 80, Section.EIGHT.getY(), .75));
        add(new Truck(Truck.TruckTypes.SHORT, 280, Section.EIGHT.getY(), .75));
        add(new Truck(Truck.TruckTypes.SHORT, 480, Section.EIGHT.getY(), .75));

        add(new Car(Car.CarTypes.RED, 0 + 60, Section.NINE.getY(), -1.5));
        add(new Car(Car.CarTypes.WHITE, 216.67 + 60, Section.NINE.getY(), -1.5));
        add(new Car(Car.CarTypes.RED, 433.33 + 60, Section.NINE.getY(), -1.5));

        add(new Car(Car.CarTypes.WHITE, 0 + 120, Section.TEN.getY(), -1.5));
        add(new Car(Car.CarTypes.RED, 216.67 + 120, Section.TEN.getY(), -1.5));
        add(new Car(Car.CarTypes.WHITE, 433.33 + 120, Section.TEN.getY(), -1.5));

        add(new Car(Car.CarTypes.RED, 0 + 120, Section.ELEVEN.getY(), 1.5));
        add(new Car(Car.CarTypes.WHITE, 216.67 + 120, Section.ELEVEN.getY(), 1.5));
        add(new Car(Car.CarTypes.RED, 433.33 + 120, Section.ELEVEN.getY(), 1.5));

        add(new Car(Car.CarTypes.WHITE, 0 + 60, Section.TWELVE.getY(), 1.5));
        add(new Car(Car.CarTypes.WHITE, 216.67 + 60, Section.TWELVE.getY(), 1.5));
        add(new Car(Car.CarTypes.RED, 433.33 + 60, Section.TWELVE.getY(), 1.5));

        add(new Car(Car.CarTypes.RED, 0, Section.THIRTEEN.getY(), 1.5));
        add(new Car(Car.CarTypes.RED, 216.67, Section.THIRTEEN.getY(), 1.5));
        add(new Car(Car.CarTypes.WHITE, 433.33, Section.THIRTEEN.getY(), 1.5));
    }
}
