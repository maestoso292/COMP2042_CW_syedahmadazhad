package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Truck extends Obstacle {
    public enum TruckTypes{
        SHORT (0, 120),
        LONG (1, 200);
        public final int label;
        public final int size;
        TruckTypes(int label, int size) {
            this.label = label;
            this.size = size;
        }
    }

    private static final double TRUCK_PADDING = 7;
    private static final String TRUCK_PATH = OBSTACLES_PATH + "truck";

    private static ArrayList<Image> trucks;

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
