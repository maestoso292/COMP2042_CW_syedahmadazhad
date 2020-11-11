package frogger.world.levels;

import frogger.actor.*;
import frogger.world.Level;

public class LevelTwo extends Level {
    public LevelTwo() {
        super();

        add(new Log(Log.LogTypes.SHORT, 0, Section.THREE.getY() + ActorType.LOG.getPadding(), 0.75));
        add(new Log(Log.LogTypes.SHORT , 220, Section.THREE.getY() + ActorType.LOG.getPadding(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 440, Section.THREE.getY() + ActorType.LOG.getPadding(), 0.75));

        add(new WetTurtle(600, Section.FOUR.getY() + ActorType.TURTLE.getPadding(), -1));
        add(new WetTurtle(400, Section.FOUR.getY() + ActorType.TURTLE.getPadding(), -1));
        add(new WetTurtle(200, Section.FOUR.getY() + ActorType.TURTLE.getPadding(), -1));

        add(new Log(Log.LogTypes.LONG, 0, Section.FIVE.getY() + ActorType.LOG.getPadding(), -2));
        add(new Log(Log.LogTypes.LONG, 400, Section.FIVE.getY() + ActorType.LOG.getPadding(), -2));

        add(new Log(Log.LogTypes.SHORT, 50, Section.SIX.getY() + ActorType.LOG.getPadding(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 270, Section.SIX.getY() + ActorType.LOG.getPadding(), 0.75));
        add(new Log(Log.LogTypes.SHORT, 490, Section.SIX.getY() + ActorType.LOG.getPadding(), 0.75));

        add(new Turtle(500, Section.SEVEN.getY() + ActorType.TURTLE.getPadding(), -1));
        add(new Turtle(300, Section.SEVEN.getY() + ActorType.TURTLE.getPadding(), -1));
        add(new WetTurtle(700, Section.SEVEN.getY() + ActorType.TURTLE.getPadding(), -1));

        add(new Car(Car.CarTypes.WHITE, 500, Section.NINE.getY() + ActorType.CAR.getPadding(), -2));
        add(new Car(Car.CarTypes.WHITE, 300, Section.NINE.getY() + ActorType.CAR.getPadding(), -2));

        add(new Car(Car.CarTypes.WHITE, 0, Section.TEN.getY() + ActorType.CAR.getPadding(), 5));

        add(new Truck(Truck.TruckTypes.LONG, 0, Section.ELEVEN.getY() + ActorType.TRUCK.getPadding(), 1));
        add(new Truck(Truck.TruckTypes.LONG, 500, Section.ELEVEN.getY() + ActorType.TRUCK.getPadding(), 1));

        add(new Car(Car.CarTypes.RED, 100, Section.TWELVE.getY() + ActorType.CAR.getPadding(), -1));
        add(new Car(Car.CarTypes.RED, 250, Section.TWELVE.getY() + ActorType.CAR.getPadding(), -1));
        add(new Car(Car.CarTypes.RED, 400, Section.TWELVE.getY() + ActorType.CAR.getPadding(), -1));
        add(new Car(Car.CarTypes.RED, 550, Section.TWELVE.getY() + ActorType.CAR.getPadding(), -1));

        add(new Truck(Truck.TruckTypes.SHORT, 0, Section.THIRTEEN.getY() + ActorType.TRUCK.getPadding(), 1));
        add(new Truck(Truck.TruckTypes.SHORT, 300, Section.THIRTEEN.getY() + ActorType.TRUCK.getPadding(), 1));
        add(new Truck(Truck.TruckTypes.SHORT, 600, Section.THIRTEEN.getY() + ActorType.TRUCK.getPadding(), 1));

        addAnimal();
    }

    @Override
    public void act(long now) {

    }
}
