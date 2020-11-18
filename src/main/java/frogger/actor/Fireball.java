package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The Fireball class provides a Fireball obstacle that has an animation in the Frogger game.
 */
public class Fireball extends Obstacle {
    /** Specifies path to directory containing image files for Fireball animation*/
    private static final String FIREBALL_PATH = OBSTACLES_PATH + "fireball/fireball";

    /**
     * Specifies how much vertical padding to use when instantiating. Measured in pixels.
     */
    private static final double FIREBALL_PADDING = 7;

    /**
     * Specifies the size(width) of all Fireball instances. Measured in pixels.
     */
    private static final int FIREBALL_SIZE = 80;

    /** Specifies the number of Images in the animation of Fireball instances. */
    private static final int NUM_FIREBALL_ANIM = 5;

    /** A List to store references to all Images in the Fireball animation. */
    private static ArrayList<Image> fireballs;

    /**
     * Creates a Fireball at the specified coordinates, sets it's speed, and displays the corresponding Image. If
     * Images have not been loaded, load the images for the animation into a static List.
     * @param xpos Specifies the x-coordinate. Measured in pixels.
     * @param ypos Specifies the y-coordinate. Measured in pixels.
     * @param speed Specifies the speed at which the instance should move across the screen. Measured in
     *              pixels per frame.
     */
    public Fireball(double xpos, double ypos, double speed) {
        super(xpos, ypos + FIREBALL_PADDING, speed);
        if (fireballs == null) {
            fireballs = new ArrayList<>(NUM_FIREBALL_ANIM);
            for (int i = 0; i < NUM_FIREBALL_ANIM; i++) {
                fireballs.add(new Image(FIREBALL_PATH + i + ".png", FIREBALL_SIZE, FIREBALL_SIZE, true, true));
            }
        }
        setImage(fireballs.get(0));
    }

    /**
     * Plays the animation for Fireball instances by switching the displayed Image with those stored in a List.
     * Called every frame.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {
        super.act(now);
        setImage(fireballs.get((int) (now / 90000000  % NUM_FIREBALL_ANIM)));
    }
}
