package frogger.scene;

import frogger.actor.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static frogger.Main.X_UPPER_BOUND;
import static frogger.actor.Car.CAR_SIZE;

public abstract class LevelFactory {
    private enum ActorType {
        LOG (8),
        TURTLE (1.7),
        WET_TURTLE (1.7),
        CAR (9.5),
        TRUCK (7);
        // How padding is calculated: Padding = (Section height - Ingame image height) / 2
        private final double padding;
        ActorType(double padding) {
            this.padding = padding;
        }
    }

    private static final double sectionHeight = 800 / 15.0;
    private static final int[] WATER_SECTIONS = {3, 4, 5, 6, 7};
    private static final int[] ROAD_SECTIONS = {9, 10, 11, 12, 13};
    private static final int MIN_SPACE = 40;
    private static final int MAX_SPACE = 80;

    private static Level level;

    public static Level generateRandomLevel() {
        level = new Level();
        ActorType[] types = ActorType.values();

        // TODO Make this all more efficient and neater
        for (int i : WATER_SECTIONS) {
            double speed = 0;
            while (speed > -0.75 && speed < 0.75) {
                speed = ThreadLocalRandom.current().nextInt(-10, 10) / 4.0;
            }

            ActorType type;
            do {
                type = types[ThreadLocalRandom.current().nextInt(0, types.length)];
            }
            while (type.equals(ActorType.CAR) || type.equals(ActorType.TRUCK));

            double ypos = (800.0 * i / 15) + type.padding;

            switch (type) {
                case LOG:
                    generateLogsInSection(ypos, speed);
                    break;
                case TURTLE:
                    generateTurtlesInSection(ypos, speed);
                    break;
                case WET_TURTLE:
                    generateWetTurtlesInSection(ypos, speed);
                    break;
                default:
            }
        }

        for (int i : ROAD_SECTIONS) {
            double speed = 0;
            while (speed > -0.75 && speed < 0.75) {
                speed = ThreadLocalRandom.current().nextInt(-15, 15) / 4.0;
            }

            ActorType type;
            do {
                type = types[ThreadLocalRandom.current().nextInt(0, types.length)];
            }
            while (!type.equals(ActorType.CAR) && !type.equals(ActorType.TRUCK));

            double ypos = (800.0 * i / 15) + type.padding;

            switch (type) {
                case CAR:
                    generateCarsInSection(ypos, speed);
                    break;
                case TRUCK:
                    generateTrucksInSection(ypos, speed);
                    break;
                default:
            }
        }
        return level;
    }

    private static void generateLogsInSection(double ypos, double speed) {
        Log.LogTypes[] logTypes = Log.LogTypes.values();
        Log.LogTypes logType = logTypes[ThreadLocalRandom.current().nextInt(0, logTypes.length)];

        int count = ThreadLocalRandom.current().nextInt(logType.equals(Log.LogTypes.SHORT) ? 2 : 1, 2 + logType.getType());
        double xpos = 0;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(logType.getSize() + 80, logType.getSize() + 160);
            level.add(new Log(logType, xpos, ypos, speed));
        }
    }

    private static void generateTurtlesInSection(double ypos, double speed) {
        int count = ThreadLocalRandom.current().nextInt(2, 4);
        double xpos = 0;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(Turtle.TURTLE_SIZE + MIN_SPACE,Turtle.TURTLE_SIZE + MAX_SPACE);
            level.add(new Turtle(xpos, ypos, speed));
        }
    }

    private static void generateWetTurtlesInSection(double ypos, double speed) {
        int count = ThreadLocalRandom.current().nextInt(2, 4);
        double xpos = 0;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(Turtle.TURTLE_SIZE + MIN_SPACE,Turtle.TURTLE_SIZE + MAX_SPACE);
            level.add(new WetTurtle(xpos, ypos, speed));
        }
    }

    private static void generateTrucksInSection(double ypos, double speed) {
        Truck.TruckTypes[] truckTypes = Truck.TruckTypes.values();
        Truck.TruckTypes truckType = truckTypes[ThreadLocalRandom.current().nextInt(0, truckTypes.length)];

        int count = ThreadLocalRandom.current().nextInt(1, 3 - truckType.getType());
        double xpos = 0;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(truckType.getSize() + MIN_SPACE, truckType.getSize() + MAX_SPACE );
            level.add(new Truck(truckType, xpos, ypos, speed));
        }
    }

    private static void generateCarsInSection(double ypos, double speed) {
        Car.CarTypes[] carTypes = Car.CarTypes.values();
        Car.CarTypes carType = carTypes[ThreadLocalRandom.current().nextInt(0, carTypes.length)];

        int count = ThreadLocalRandom.current().nextInt(speed > 1.5 ? 1 : 3, speed > 1.5 ? 3 : 5);
        double xpos = 0;
        for (int i = 0; i < count; i++) {
            xpos += ThreadLocalRandom.current().nextDouble(CAR_SIZE + MIN_SPACE, CAR_SIZE + MAX_SPACE);
            level.add(new Car(carType, xpos, ypos, speed));
        }
    }
}
