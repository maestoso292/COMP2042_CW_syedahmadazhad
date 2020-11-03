package frogger.actor;

import static frogger.Main.RESOURCES_PATH;

public class Obstacle extends Actor {
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
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}
}
