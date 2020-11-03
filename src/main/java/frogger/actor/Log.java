package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

import static frogger.Main.X_LOWER_BOUND;
import static frogger.Main.X_UPPER_BOUND;

public class Log extends Platform {
	public enum LogTypes {
		LONG (0),
		MEDIUM (1),
		SHORT (2);
		private int type;
		LogTypes(int type) {
			this.type = type;
		}
	}
	private static final String LOG_PATH = PLATFORMS_PATH + "log";
	private static final int[] LOG_SIZES = {300, 209, 150};
	// Storing images into static arrays uses less memory as different instances use the same Image(s)
	private static ArrayList<Image> logs;

	public Log(LogTypes type, int xpos, int ypos, double speed) {
		super(xpos, ypos, speed);
		if (logs == null) {
			logs = new ArrayList<>(LogTypes.values().length);
			for (int i = 0; i < LogTypes.values().length; i++) {
				logs.add(new Image(LOG_PATH + i + ".png", LOG_SIZES[i], LOG_SIZES[i], true, true));
			}
		}
		setImage(logs.get(type.type));
	}

	@Override
	public void act(long now) {
		move(super.getSpeed() , 0);
		// TODO This can be moved to a superclass
		// TODO Perhaps add randomness to this?
		if (getX()>X_UPPER_BOUND && getSpeed()>0)
			setX(X_LOWER_BOUND - getWidth());
		//setX(-180);
		if (getX()<X_LOWER_BOUND - getWidth() && getSpeed()<0)
			setX(X_UPPER_BOUND);
		//setX(700);
	}
}