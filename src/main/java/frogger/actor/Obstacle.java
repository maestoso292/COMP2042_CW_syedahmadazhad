package frogger.actor;

import static frogger.Main.*;

/**
 * The Obstacle class is the abstract class which all Frogger obstacles(nodes that cannot be stood on by the Frogger)
 * must be a subclass of. All Obstacles that intersect with the Animal instance will cause the Frogger to 'die'.
 * All images files of Obstacles must face the right direction (East).
 * @see Animal
 */
public abstract class Obstacle extends Actor {
	/**
	 * Specifies the path to the directory containing images for all obstacles.
	 */
	protected static final String OBSTACLES_PATH =  "file:src/main/resources/obstacles/";

	/**
	 * Specifies the speed at which the instance moves. Measured in pixels per frame.
	 */
	private double speed;

	/**
	 * Creates an Obstacle at the specified coordinates and displays the image of the Obstacle depending on the
	 * sign of the speed value. The image will face left if speed is negative, right otherwise.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 * @param speed Specifies the speed at which the instance moves. Measured in pixels per frame.
	 */
	public Obstacle(double xpos, double ypos, double speed) {
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setScaleX(this.speed >= 0 ? 1 : -1);
	}

	/**
	 * Moves the instance according to it's speed every frame. If the instance is completely out of bounds of the
	 * application window (Entire node not visible), the x-coordinate is reset to the one side of the window depending
	 * on it's speed. Called every frame.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
		move(speed, 0);
		if (getX() > X_UPPER_BOUND && speed > 0)
			setX(X_LOWER_BOUND - getWidth());
		if (getX() < X_LOWER_BOUND - getWidth() && speed <0)
			setX(X_UPPER_BOUND);
	}
}
