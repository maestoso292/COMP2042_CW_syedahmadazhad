package frogger.actor;

import static frogger.Main.*;

public abstract class Platform extends Actor {
    protected static final String PLATFORMS_PATH = RESOURCES_PATH + "platforms/";
    private double speed;

    public Platform(double xpos, double ypos, double speed) {
        setX(xpos);
        setY(ypos);
        this.speed = speed;
        setScaleX(this.speed >= 0 ? 1 : -1);
    }

    protected double getSpeed() {
        return speed;
    }

    @Override
    public void act(long now) {
        move(speed , 0);
        // TODO Perhaps this can be moved to a superclass?
        if (getX()>X_UPPER_BOUND && speed > 0)
            setX(X_LOWER_BOUND - getWidth());
        if (getX()<X_LOWER_BOUND - getWidth() && speed <0)
            setX(X_UPPER_BOUND);
    }
}
