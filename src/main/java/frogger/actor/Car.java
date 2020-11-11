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

        public int getType() {
            return type;
        }
    }

    private static final String CAR_PATH = OBSTACLES_PATH + "car";
    public static final int CAR_SIZE = 50;

    private static ArrayList<Image> cars;

    public Car(CarTypes type, double xpos, double ypos, double speed) {
        super(xpos, ypos, speed);
        if (cars == null) {
            cars = new ArrayList<>(CarTypes.values().length);
            for (CarTypes carType : CarTypes.values()) {
                cars.add(new Image(CAR_PATH + carType.getType() + ".png", CAR_SIZE, CAR_SIZE, true, true));
            }
        }
        setImage(cars.get(type.type));
    }
}
