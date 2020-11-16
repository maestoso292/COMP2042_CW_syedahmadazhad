package frogger.world.levels;

import frogger.actor.*;

public class LevelTwo extends Level {
    public LevelTwo(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);

        add(new Log(Log.LogTypes.LONG, 250, Section.THREE.getY(), -2.5));

        add(new Turtle(0, Section.FOUR.getY(), 1));
        add(new Turtle(300, Section.FOUR.getY(), 1));
        add(new WetTurtle(500, Section.FOUR.getY(), 1));

        add(new Turtle(100, Section.FIVE.getY(), -1.75));
        add(new Turtle(400, Section.FIVE.getY(), -1.75));

        add(new WetTurtle(150, Section.SIX.getY(), 2));
        add(new WetTurtle(350, Section.SIX.getY(), 2));
        add(new WetTurtle(550, Section.SIX.getY(), 2));

        add(new Log(Log.LogTypes.MEDIUM, 0, Section.SEVEN.getY(), -3));
        add(new Log(Log.LogTypes.MEDIUM, 300, Section.SEVEN.getY(), -3));

        add(new Turtle(-200, Section.EIGHT.getY(), 2));
        add(new Turtle(100, Section.EIGHT.getY(), 2));
        add(new WetTurtle(400, Section.EIGHT.getY(), 2));

        add(new Car(Car.CarTypes.RED, 0, Section.TEN.getY(), 1));
        add(new Car(Car.CarTypes.RED, 200, Section.TEN.getY(), 1));
        add(new Car(Car.CarTypes.WHITE, 500, Section.TEN.getY(), 1));

        add(new Car(Car.CarTypes.WHITE, 100, Section.ELEVEN.getY(), 4));
        add(new Car(Car.CarTypes.WHITE, 450, Section.ELEVEN.getY(), 4));

        add(new Truck(Truck.TruckTypes.SHORT, 0, Section.TWELVE.getY(), -2));
        add(new Truck(Truck.TruckTypes.SHORT, 300, Section.TWELVE.getY(), -2));

        add(new Car(Car.CarTypes.WHITE, 250, Section.THIRTEEN.getY(), -6));
    }

    @Override
    public void act(long now) {

    }
}
