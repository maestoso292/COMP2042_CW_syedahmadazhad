package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

import static frogger.Main.X_LOWER_BOUND;
import static frogger.Main.X_UPPER_BOUND;

public class Log extends Platform {
	public enum LogTypes {
		LONG (0, 318),
		MEDIUM (1, 209),
		SHORT (2, 150);

		public final int type;
		public final int size;
		LogTypes(int type, int size) {
			this.type = type;
			this.size = size;
		}
	}
	private static final double LOG_PADDING = 8;
	private static final String LOG_PATH = PLATFORMS_PATH + "log";
	// Storing images into static arrays uses less memory as different instances use the same Image(s)
	private static ArrayList<Image> logs;

	public Log(LogTypes type, double xpos, double ypos, double speed) {
		super(xpos, ypos + LOG_PADDING, speed);
		if (logs == null) {
			logs = new ArrayList<>(LogTypes.values().length);
			for (LogTypes logType : LogTypes.values()) {
				logs.add(new Image(LOG_PATH + logType.type + ".png", logType.size, logType.size, true, true));
			}
		}
		setImage(logs.get(type.type));
	}
}
