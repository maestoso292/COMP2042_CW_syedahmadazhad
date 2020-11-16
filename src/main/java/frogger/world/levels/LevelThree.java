package frogger.world.levels;

import frogger.actor.*;

public class LevelThree extends Level {
    protected LevelThree(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogTypes.SHORT, 0, Section.THREE.getY(), 1));
        add(new Log(Log.LogTypes.SHORT, 350, Section.THREE.getY(), 1));

        add(new WetTurtle(500, Section.FOUR.getY(), -1.25));
        add(new Turtle(300, Section.FOUR.getY(), -1.25));
        add(new WetTurtle(100, Section.FOUR.getY(), -1.25));

        add(new Turtle(150, Section.FIVE.getY(), 0.75));
        add(new WetTurtle(350, Section.FIVE.getY(), 0.75));
        add(new WetTurtle(550, Section.FIVE.getY(), 0.75));

        add(new Car(Car.CarTypes.RED, 0, Section.SEVEN.getY(), -5));

        add(new Truck(Truck.TruckTypes.LONG, 0, Section.EIGHT.getY(), 1.5));
        add(new Truck(Truck.TruckTypes.LONG, 400, Section.EIGHT.getY(), 1.5));

        add(new Truck(Truck.TruckTypes.SHORT, 0, Section.NINE.getY(), -1.5));
        add(new Truck(Truck.TruckTypes.SHORT, 200, Section.NINE.getY(), -1.5));
        add(new Truck(Truck.TruckTypes.SHORT, 450, Section.NINE.getY(), -1.5));

        add(new Car(Car.CarTypes.RED, 0, Section.TEN.getY(), 1));
        add(new Car(Car.CarTypes.WHITE, 120, Section.TEN.getY(), 1));
        add(new Car(Car.CarTypes.RED, 300, Section.TEN.getY(), 1));
        add(new Car(Car.CarTypes.WHITE, 450, Section.TEN.getY(), 1));

        add(new Truck(Truck.TruckTypes.LONG, 200, Section.ELEVEN.getY(), 4));

        add(new Car(Car.CarTypes.WHITE, 100, Section.TWELVE.getY(), -3));
        add(new Car(Car.CarTypes.RED, 400, Section.TWELVE.getY(), -3));

        add(new Truck(Truck.TruckTypes.SHORT, 0, Section.THIRTEEN.getY(), 2));
        add(new Truck(Truck.TruckTypes.SHORT, 300, Section.THIRTEEN.getY(), 2));
    }
}
