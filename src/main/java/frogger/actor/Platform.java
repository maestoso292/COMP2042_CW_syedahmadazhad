package frogger.actor;

import static frogger.Main.*;

/**
 * The Platform class is the abstract class which all Frogger platforms(nodes that can be stood on by the Frogger)
 * must be a subclass of. All images files of Platforms must face the right direction (East).
 * @see Animal
 */
public abstract class Platform extends InteractiveActor {
    /**
     * Specifies the path to the directory containing image files for all platforms.
     */
    protected static final String PLATFORMS_PATH = "file:src/main/resources/platforms/";

    /**
     * Specifies the speed at which the instance moves. Measured in pixels per frame.
     */
    private double speed;

    /**
     * Creates a Platform at the specified coordinates and displays the image of the Platform depending on the
     * sign of the speed value. The image will face left if speed is negative, right otherwise.
     * @param xpos Specifies the x-coordinate. Measured in pixels.
     * @param ypos Specifies the y-coordinate. Measured in pixels.
     * @param speed Specifies the speed at which the instance moves. Measured in pixels per frame.
     */
    public Platform(double xpos, double ypos, double speed) {
        super(xpos, ypos);
        this.speed = speed;
        setScaleX(this.speed >= 0 ? 1 : -1);
    }

    /**
     * Get the speed of the Platform instance.
     * @return The speed of the instance.
     */
    protected double getSpeed() {
        return speed;
    }

    /**
     * Moves the instance according to it's speed every frame. If the instance is completely out of bounds of the
     * application window (Entire node not visible), the x-coordinate is reset to the one side of the window depending
     * on it's speed. Called every frame.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {
        move(speed , 0);
        if (getX()>X_UPPER_BOUND && speed > 0)
            setX(X_LOWER_BOUND - getWidth());
        if (getX()<X_LOWER_BOUND - getWidth() && speed <0)
            setX(X_UPPER_BOUND);
    }
}
