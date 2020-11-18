package frogger.world.levels;

import frogger.actor.Car;
import frogger.actor.Truck;

/**
 * LevelSix is a class that creates a custom Level for the Frogger game to use.
 * @see Level
 * @see LevelFactory
 */
public class LevelSix extends Level{
    /**
     * Creates a new instance of LevelSix with preset child nodes. Called by LevelFactory.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    protected LevelSix(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        // Ensures that animal is above platforms but below obstacles
        getAnimal().toFront();

        add(new Car(Car.CarTypes.WHITE, 0, Section.FOUR.getY(), -1));
        add(new Car(Car.CarTypes.RED, 130, Section.FOUR.getY(), -1));
        add(new Car(Car.CarTypes.WHITE, 260, Section.FOUR.getY(), -1));
        add(new Car(Car.CarTypes.WHITE, 390, Section.FOUR.getY(), -1));
        add(new Car(Car.CarTypes.WHITE, 520, Section.FOUR.getY(), -1));

        add(new Car(Car.CarTypes.RED, 0, Section.FIVE.getY(), -2.5));
        add(new Car(Car.CarTypes.RED, 300, Section.FIVE.getY(), -2.5));

        add(new Car(Car.CarTypes.RACECAR, 100, Section.SIX.getY(), -9));

        add(new Car(Car.CarTypes.RACECAR, 50, Section.SEVEN.getY(), -5.5));
        add(new Car(Car.CarTypes.RACECAR, 470, Section.SEVEN.getY(), -5.5));

        add(new Truck(Truck.TruckTypes.LONG, 0, Section.EIGHT.getY(), .75));
        add(new Truck(Truck.TruckTypes.LONG, 400, Section.EIGHT.getY(), .75));

        add(new Truck(Truck.TruckTypes.LONG, 0, Section.NINE.getY(), -.75));
        add(new Truck(Truck.TruckTypes.LONG, 400, Section.NINE.getY(), -.75));

        add(new Car(Car.CarTypes.RACECAR, 120, Section.TEN.getY(), 5));

        add(new Car(Car.CarTypes.WHITE, 120, Section.ELEVEN.getY(), 2));
        add(new Car(Car.CarTypes.RED, 340, Section.ELEVEN.getY(), 2));
        add(new Car(Car.CarTypes.WHITE, 550, Section.ELEVEN.getY(), 2));

        add(new Car(Car.CarTypes.RACECAR, 60, Section.TWELVE.getY(), 5));
        add(new Car(Car.CarTypes.RACECAR, 280, Section.TWELVE.getY(), 5));

        add(new Car(Car.CarTypes.RACECAR, 0, Section.THIRTEEN.getY(), 7));
    }
}
