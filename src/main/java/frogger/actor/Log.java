package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The Log class provides a Log platform of varying sizes in the Frogger game.
 */
public class Log extends Platform {
	/**
	 * An enumeration of different types of Logs and their corresponding labels and sizes.
	 */
	public enum LogTypes {
		/**
		 * Specifies a long log type with label 0 and size(width) 318
		 */
		LONG (0, 318),
		/**
		 * Specifies a medium sized log type with label 1 and size(width) 209
		 */
		MEDIUM (1, 209),
		/**
		 * Specifies a short log type with label 2 and size(width) 150
		 */
		SHORT (2, 150);

		/**
		 * Specifies the label of the LogType.
		 */
		public final int label;
		/**
		 * Specifies the size(width) of the LogType. Measured in pixels.
		 */
		public final int size;

		/**
		 * Creates a LogType with the specified label and size.
		 * @param label The label of the LogType used in the name of its image file.
		 * @param size The size(width) of images of this type. Measured in pixels.
		 */
		LogTypes(int label, int size) {
			this.label = label;
			this.size = size;
		}
	}

	/**
	 * Specifies how much vertical padding to use when instantiating. Measured in pixels.
	 */
	private static final double LOG_PADDING = 8;

	/**
	 * Specifies the path of the image files to use for the Log instances.
	 */
	private static final String LOG_PATH = PLATFORMS_PATH + "log";

	/**
	 * A List to store references to all possible Images for the Log instances.
	 */
	private static ArrayList<Image> logs;

	/**
	 * Creates a Log at the specified coordinates, sets it's speed, and displays the corresponding Image.
	 * @param type Specifies the type of Log to instance.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 * @param speed Specifies the speed at which the instance should move across the screen. Measured in
	 *              pixels per frame.
	 */
	public Log(LogTypes type, double xpos, double ypos, double speed) {
		super(xpos, ypos + LOG_PADDING, speed);
		if (logs == null) {
			logs = new ArrayList<>(LogTypes.values().length);
			for (LogTypes logType : LogTypes.values()) {
				logs.add(new Image(LOG_PATH + logType.label + ".png", logType.size, logType.size, true, true));
			}
		}
		setImage(logs.get(type.label));
	}
}
