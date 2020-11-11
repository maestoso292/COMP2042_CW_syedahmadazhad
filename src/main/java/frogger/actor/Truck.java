package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Truck extends Obstacle {
    public enum TruckTypes{
        SHORT (0, 120),
        LONG (1, 200);
        private int type;
        private int size;
        TruckTypes(int type, int size) {
            this.type = type;
            this.size = size;
        }

        public int getType() {
            return type;
        }

        public int getSize() {
            return size;
        }
    }

    private static final String TRUCK_PATH = OBSTACLES_PATH + "truck";
    private static ArrayList<Image> trucks;

    public Truck(TruckTypes type, double xpos, double ypos, double speed) {
        super(xpos, ypos, speed);
        if (trucks == null) {
            trucks = new ArrayList<>(TruckTypes.values().length);
            for (TruckTypes truckType : TruckTypes.values()) {
                trucks.add(new Image(TRUCK_PATH + truckType.getType() + ".png", truckType.getSize(), truckType.getSize(), true, true));
            }
        }
        setImage(trucks.get(type.type));
    }
}
