package p4_group_8_repo;

import static p4_group_8_repo.Main.RESOURCES_PATH;

public abstract class Platform extends Actor {
    protected static final String PLATFORMS_PATH = RESOURCES_PATH + "platforms/";
    protected int speed;

    public int getSpeed() {
        return speed;
    }
}
