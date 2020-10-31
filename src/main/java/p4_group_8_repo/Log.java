package p4_group_8_repo;

import javafx.scene.image.Image;

import java.util.Arrays;

import static p4_group_8_repo.Main.RESOURCES_PATH;

public class Log extends Platform {
	private static final String LOG_PATH = PLATFORMS_PATH + "log";
	private static final int LOG_1_SIZE = 300;
	private static final int LOG_2_SIZE = 209;
	private static final int LOG_3_SIZE = 150;

	private double speed;
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
	}

	public Log(int type, int xpos, int ypos, double speed) {
		switch(type) {
			case 1:
				setImage(new Image(LOG_PATH +"1.png", LOG_1_SIZE, LOG_1_SIZE, true, true));
				break;
			case 2:
				setImage(new Image(LOG_PATH +"2.png", LOG_2_SIZE, LOG_2_SIZE, true, true));
				break;
			default:
				setImage(new Image(LOG_PATH + "3.png", LOG_3_SIZE, LOG_3_SIZE, true, true));
		}

		setX(xpos);
		setY(ypos);
		this.speed = speed;
	}

	public boolean getLeft() {
		return speed < 0;
	}
}
