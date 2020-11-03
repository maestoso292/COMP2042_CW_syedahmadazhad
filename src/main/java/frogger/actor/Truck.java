package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Truck extends Obstacle {
    public enum TruckTypes{
        SHORT (0),
        LONG (1);
        private int type;
        TruckTypes(int type) {
            this.type = type;
        }
    }

    private static final String TRUCK_PATH = OBSTACLES_PATH + "truck";
    private static final int NUM_TRUCK_TYPES = 2;
    private static final int[] TRUCK_SIZES = {120, 200};

    private static ArrayList<Image> trucks;

    public Truck(TruckTypes type, int xpos, int ypos, int speed) {
        super(xpos, ypos, speed);
        if (trucks == null) {
            trucks = new ArrayList<>(NUM_TRUCK_TYPES);
            for (int i = 0; i < NUM_TRUCK_TYPES; i++) {
                trucks.add(new Image(TRUCK_PATH + i + ".png", TRUCK_SIZES[i], TRUCK_SIZES[i], true, true));
            }
        }
        setImage(trucks.get(type.type));
    }
}
