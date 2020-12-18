package frogger.actor;

import javafx.scene.image.Image;


/**
 * BackgroundImage is a class that provides an ImageView that displays an Image with dimensions to fit the
 * application window. The image is always in the background and is static, in that it does not move.
 */
public class BackgroundImage extends Actor{
	/**
	 * A String to store the path to the directory containing the background images.
	 */
	private static final String BACKGROUNDS_PATH = "file:src/main/resources/backgrounds/";

	/**
	 * Creates a new BackgroundImage and displays the image specified by the label. The image's aspect ratio is not
	 * maintained and is made to fit the application window.
	 * @param label Specifies the name of the image in the directory. File extension must be included.
	 * @param width Specifies the desired width of the background image. Measured in pixels.
	 * @param height Specifies the desired height of the background image. Measured in pixels.
	 */
	public BackgroundImage(String label, double width, double height) {
		setImage(new Image(BACKGROUNDS_PATH + label, width, height, false, true));
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
