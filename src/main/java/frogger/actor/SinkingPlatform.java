package frogger.actor;

/**
 * The SinkingPlatform class is the abstract class which all Frogger
 * platforms(nodes that can be stood on by the Frogger) that 'sink' below the surface must be a subclass of.
 * All images files of Platforms must face the right direction(East).
 * @see Animal
 */
public abstract class SinkingPlatform extends Platform {
    /**
     * Instance variable specifying the whether the instance has 'sunk' below the water.
     */
    private boolean sunk;

    /**
     * Creates a SinkingPlatform at the specified coordinates and displays the image of the SinkingPlatform depending
     * on the sign of the speed value. The image will face left if speed is negative, right otherwise.
     * @param xpos Specifies the x-coordinate. Measured in pixels.
     * @param ypos Specifies the y-coordinate. Measured in pixels.
     * @param speed Specifies the speed at which the instance moves. Measured in pixels per frame.
     */
    public SinkingPlatform(double xpos, double ypos, double speed) {
        super(xpos, ypos, speed);
    }

    /**
     * Sets the sunk state for the instance.
     * @param sunk The new boolean value for the sunk state.
     */
    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    /**
     * Checks whether the instance is sunk.
     * @return The sunk state of the instance.
     */
    public boolean isSunk() {
        return sunk;
    }
}
