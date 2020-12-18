package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The Car class provides a Car obstacle of varying colours in the Frogger game.
 */
public class Car extends Obstacle {
    /**
     * An enumeration of different types of Cars and their corresponding labels.
     */
    public enum CarType {
        /** Specifies a red car with label 0. */
        RED (0),
        /** Specifies a white car with label 1. */
        WHITE (1),
        /** Specifies a racecar with label 2. */
        RACECAR (2);
        /**
         * Specifies the label of the car to use in the name of the corresponding image file.
         */
        public final int label;

        /**
         * Creates a CarType with the specified label.
         * @param label Specifies the label of the CarType to use in the name of the corresponding image file.
         */
        CarType(int label) {
            this.label = label;
        }
    }

    /**
     * Specifies the path of the image files to use for the Car instances.
     */
    private static final String CAR_PATH = OBSTACLES_PATH + "car";

    /**
     * Value of {@value #CAR_SIZE} which specifies the size(width) of all Car instances. Measured in pixels.
     */
    public static final int CAR_SIZE = 50;

    /**
     * Value of {@value #CAR_PADDING} which specifies how much vertical padding to use when instantiating. Measured in pixels.
     */
    private static final double CAR_PADDING = 9.5;

    /**
     * A List to store references to all possible Images for the Car instances.
     */
    private static ArrayList<Image> cars;

    /**
     * Creates a Car at the specified coordinates, sets it's speed, and displays the corresponding Image. If
     * Images have not been loaded, load the images of all Car types into a static List.
     * @param type Specifies the type of Car to instance.
     * @param xpos Specifies the x-coordinate. Measured in pixels.
     * @param ypos Specifies the y-coordinate. Measured in pixels.
     * @param speed Specifies the speed at which the instance should move across the screen. Measured in
     *              pixels per frame.
     */
    public Car(CarType type, double xpos, double ypos, double speed) {
        super(xpos, ypos + CAR_PADDING, speed);
        if (cars == null) {
            cars = new ArrayList<>(CarType.values().length);
            for (CarType carType : CarType.values()) {
                cars.add(new Image(CAR_PATH + carType.label + ".png", CAR_SIZE, CAR_SIZE, true, true));
            }
        }
        setImage(cars.get(type.label));
    }
}
