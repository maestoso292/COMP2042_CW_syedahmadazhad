package frogger.actor;

import static frogger.Main.*;

public abstract class Obstacle extends Actor {
	protected static final String OBSTACLES_PATH = RESOURCES_PATH + "obstacles/";
	private double speed;

	public Obstacle(double xpos, double ypos, double speed) {
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setScaleX(this.speed >= 0 ? 1 : -1);
	}

	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>X_UPPER_BOUND && speed > 0)
			setX(X_LOWER_BOUND - getWidth());
		if (getX()<X_LOWER_BOUND - getWidth() && speed <0)
			setX(X_UPPER_BOUND);
	}
}
