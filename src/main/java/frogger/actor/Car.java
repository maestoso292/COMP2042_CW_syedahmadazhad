package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Car extends Obstacle {
    public enum CarTypes {
        RED (0),
        WHITE (1);
        private int type;
        CarTypes(int type) {
            this.type = type;
        }
    }

    private static final String CAR_PATH = OBSTACLES_PATH + "car";
    private static final int NUM_CAR_TYPES = 2;
    private static final int CAR_SIZE = 50;

    private static ArrayList<Image> cars;

    public Car(CarTypes type, int xpos, int ypos, double speed) {
        super(xpos, ypos, speed);
        if (cars == null) {
            cars = new ArrayList<>(NUM_CAR_TYPES);
            for (int i = 0; i < NUM_CAR_TYPES; i++) {
                cars.add(new Image(CAR_PATH + i + ".png", CAR_SIZE, CAR_SIZE, true, true));
            }
        }
        setImage(cars.get(type.type));
    }
}
