package p4_group_8_repo;

import static p4_group_8_repo.Main.RESOURCES_PATH;

public class Obstacle extends Actor {
	protected static final String OBSTACLES_PATH = RESOURCES_PATH + "obstacles/";

	protected int speed;

	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}
}
