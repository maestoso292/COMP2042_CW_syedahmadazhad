package frogger.actor;

import javafx.scene.image.Image;

import static frogger.Main.*;

/**
 * BackgroundImage is a class that provides an ImageView that displays an Image with dimensions to fit the
 * application window. The image is always in the background and is static, in that it does not move.
 */
public class BackgroundImage extends Actor{
	/**
	 * A String to store the path to the directory containing the background images.
	 */
	private static final String BACKGROUNDS_PATH = RESOURCES_PATH + "backgrounds/";

	/**
	 * Creates a new BackgroundImage and displays the image specified by the label. The image's aspect ratio is not
	 * maintained and is made to fit the application window.
	 * @param label Specifies the name of the image in the directory. File extension must be included.
	 */
	public BackgroundImage(String label) {
		setImage(new Image(BACKGROUNDS_PATH + label, X_UPPER_BOUND, Y_UPPER_BOUND, false, true));
		setX(0);
		setY(0);
	}

	/**
	 * A method called every frame. The method does nothing as a BackgroundImage should be static.
	 * @param now Specifies time in nanoseconds.
	 */
	@Override
	public void act(long now) {
	}
}
