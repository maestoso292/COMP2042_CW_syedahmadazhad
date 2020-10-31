package p4_group_8_repo;

import javafx.scene.image.Image;

import java.util.Arrays;

public class Truck extends Obstacle {
    private static final int TRUCK_1_SIZE = 120;
    private static final int TRUCK_2_SIZE = 200;

    public Truck(int type, String direction, int xpos, int ypos, int speed) {
        super();

        String imgLink = OBSTACLES_PATH + "truck";
        // Checking that type and direction are valid
        // If not, type = 1 and direction = Right by default
        if (!Arrays.asList(1, 2).contains(type)) {
            type = 1;
        }
        if (!Arrays.asList("Right", "Left").contains(direction)) {
            direction = "Right";
        }

        imgLink = imgLink + type + direction + ".png";

        int size = 0;
        switch (type) {
            case 1:
                size = TRUCK_1_SIZE;
                break;
            case 2:
                size = TRUCK_2_SIZE;
                break;
            default:
        }

        setImage(new Image(imgLink, size, size, true, true));
        setX(xpos);
        setY(ypos);
        this.speed = speed;
    }
}
