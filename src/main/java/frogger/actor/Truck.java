package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The Truck class provides a Truck obstacle of varying sizes in the Frogger game.
 */
public class Truck extends Obstacle {
    /**
     * An enumeration of different types of Trucks and their corresponding sizes.
     */
    public enum TruckTypes{
        /**
         * Specifies a TruckType with label 0 and size(width) 120.
         */
        SHORT (0, 120),
        /**
         * Specifies a TruckType with label 1 and size(width) 200.
         */
        LONG (1, 200);
        /**
         * Specifies the label of the TruckType. Used in path to the image files.
         */
        public final int label;
        /**
         * Specifies the size(width) of the TruckType. Measured in pixels.
         */
        public final int size;

        /**
         * Creates a TruckType with the specified label and size.
         * @param label Specifies the label to use in the path to the image file.
         * @param size Specifies the size(width) of the TruckType. Measured in pixels.
         */
        TruckTypes(int label, int size) {
            this.label = label;
            this.size = size;
        }
    }

    /**
     * Specifies how much vertical padding to use when instantiating. Measured in pixels.
     */
    private static final double TRUCK_PADDING = 7;

    /**
     * Specifies the path of the image files to use for the Truck instances.
     */
    private static final String TRUCK_PATH = OBSTACLES_PATH + "truck";

    /**
     * A List to store references to all possible Images for the Truck instances.
     */
    private static ArrayList<Image> trucks;

    /**
     * Creates a Truck at the specified coordinates, sets it's speed, and displays the corresponding Image. If
     * Images have not been loaded, load the images of all Truck types into a static List.
     * @param type Specifies the type of Truck to instance.
     * @param xpos Specifies the x-coordinate. Measured in pixels.
     * @param ypos Specifies the y-coordinate. Measured in pixels.
     * @param speed Specifies the speed at which the instance should move across the screen. Measured in
     *              pixels per frame.
     */
    public Truck(TruckTypes type, double xpos, double ypos, double speed) {
        super(xpos, ypos + TRUCK_PADDING, speed);
        if (trucks == null) {
            trucks = new ArrayList<>(TruckTypes.values().length);
            for (TruckTypes truckType : TruckTypes.values()) {
                trucks.add(new Image(TRUCK_PATH + truckType.label + ".png", truckType.size, truckType.size, true, true));
            }
        }
        setImage(trucks.get(type.label));
    }
}
