package frogger.world.levels;

import frogger.actor.*;

import java.beans.PropertyChangeEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static frogger.Main.X_LOWER_BOUND;
import static frogger.actor.Car.CAR_SIZE;

public class LevelRandom extends Level {
    private static final int[] WATER_SECTIONS = {3, 4, 5, 6, 7};
    private static final int[] ROAD_SECTIONS = {9, 10, 11, 12, 13};
    private static final int MIN_SPACE = 40;
    private static final int MAX_SPACE = 80;

    private final List<Actor> generatedActors = new LinkedList<>();

    protected LevelRandom(int levelNumber, double waterBoundary) {
        super(levelNumber, waterBoundary);
    }

    @Override
    public void start() {
        generateLevel();
        super.start();
    }

    private void generateLevel() {
        generatedActors.forEach(this::remove);
        generatedActors.clear();
        for (int i : WATER_SECTIONS) {
            double ypos = i * SECTION_HEIGHT;
            double speed = 0;
            while (speed > -0.75 && speed < 0.75) {
                speed = ThreadLocalRandom.current().nextInt(-10, 10) / 4.0;
            }
            switch (ThreadLocalRandom.current().nextInt(0, 3)) {
                case 0 -> generateLogsInSection(ypos, speed);
                case 1 -> generateTurtlesInSection(ypos, speed);
                case 2 -> generateWetTurtlesInSection(ypos, speed);
            }
        }
        for (int i : ROAD_SECTIONS) {
            double ypos = i * SECTION_HEIGHT;
            double speed = 0;
            while (speed > -0.75 && speed < 0.75) {
                speed = ThreadLocalRandom.current().nextInt(-10, 10) / 4.0;
            }
            switch (ThreadLocalRandom.current().nextInt(0, 2)) {
                case 0 -> generateCarsInSection(ypos, speed);
                case 1 -> generateTrucksInSection(ypos, speed);
            }
        }
        getAnimal().toFront();
    }

    private void generateLogsInSection(double ypos, double speed) {
        Log.LogTypes[] logTypes = Log.LogTypes.values();
        Log.LogTypes logType = logTypes[ThreadLocalRandom.current().nextInt(0, logTypes.length)];

        int count = ThreadLocalRandom.current().nextInt( 1, 2 + logType.type);
        double xpos = 0;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(logType.size + MIN_SPACE, logType.size + MAX_SPACE);

            Log log = new Log(logType, xpos, ypos, speed);
            generatedActors.add(log);
            add(log);
        }
    }

    private void generateTurtlesInSection(double ypos, double speed) {
        int count = ThreadLocalRandom.current().nextInt(2, 4);
        double xpos = speed > 0 ? 0 : X_LOWER_BOUND - Turtle.TURTLE_SIZE;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(Turtle.TURTLE_SIZE + MIN_SPACE,Turtle.TURTLE_SIZE + MAX_SPACE);

            Turtle turtle = new Turtle(xpos, ypos, speed);
            generatedActors.add(turtle);
            add(turtle);
        }
    }

    private void generateWetTurtlesInSection(double ypos, double speed) {
        int count = ThreadLocalRandom.current().nextInt(2, 4);
        double xpos = speed > 0 ? 0 : X_LOWER_BOUND - WetTurtle.WET_TURTLE_SIZE;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(WetTurtle.WET_TURTLE_SIZE + MIN_SPACE,WetTurtle.WET_TURTLE_SIZE + MAX_SPACE);

            WetTurtle wetTurtle = new WetTurtle(xpos, ypos, speed);
            generatedActors.add(wetTurtle);
            add(wetTurtle);
        }
    }

    private void generateTrucksInSection(double ypos, double speed) {
        Truck.TruckTypes[] truckTypes = Truck.TruckTypes.values();
        Truck.TruckTypes truckType = truckTypes[ThreadLocalRandom.current().nextInt(0, truckTypes.length)];

        int count = ThreadLocalRandom.current().nextInt(1, 3 - truckType.label);
        double xpos = speed > 0 ? 0 : X_LOWER_BOUND - truckType.size;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(truckType.size + MIN_SPACE, truckType.size + MAX_SPACE );

            Truck truck = new Truck(truckType, xpos, ypos, speed);
            generatedActors.add(truck);
            add(truck);
        }
    }

    private void generateCarsInSection(double ypos, double speed) {
        Car.CarTypes[] carTypes = Car.CarTypes.values();
        Car.CarTypes carType = carTypes[ThreadLocalRandom.current().nextInt(0, carTypes.length)];

        int count = ThreadLocalRandom.current().nextInt(speed > 1.5 ? 1 : 3, speed > 1.5 ? 3 : 5);
        double xpos = speed > 0 ? 0 : X_LOWER_BOUND - CAR_SIZE;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(CAR_SIZE + MIN_SPACE, CAR_SIZE + MAX_SPACE);

            Car car = new Car(carType, xpos, ypos, speed);
            generatedActors.add(car);
            add(car);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if (evt.getPropertyName().equals("endsFilled")) {
            generateLevel();
        }
    }
}
