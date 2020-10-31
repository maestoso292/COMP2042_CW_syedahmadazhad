package p4_group_8_repo;

import javafx.scene.image.Image;

public class Car extends Obstacle {
    private static final int CAR_SIZE = 50;

    public Car(int type, String direction, int xpos, int ypos, int speed) {
        super();

        String imgLink = OBSTACLES_PATH + "car";
        // Checking that type and direction are valid
        // If not, type = 1 and direction = Right by default
        if (type != 1) {
            type = 1;
        }
        if (!direction.equals("Right") && !direction.equals("Left")) {
            direction = "Right";
        }

        imgLink = imgLink + type + direction + ".png";

        setImage(new Image(imgLink, CAR_SIZE, CAR_SIZE, true, true));
        setX(xpos);
        setY(ypos);
        this.speed = speed;
    }
}
