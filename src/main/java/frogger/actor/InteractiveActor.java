package frogger.actor;

/**
 * InteractiveActor is the abstract class for Actor objects that an {@link Actor}
 * instance can interact with.
 */
public abstract class InteractiveActor extends Actor{
    /**
     * Create an instance of InteractiveActor at the specified x-coordinate and y-coordinate.
     * @param xpos Specifies initial x-coordinate of the instance. Measured in pixels.
     * @param ypos Specifies initial y-coordinate of the instance. Measured in pixels.
     */
    public InteractiveActor(double xpos, double ypos) {
        setX(xpos);
        setY(ypos);
    }
}
