package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

import static frogger.Main.RESOURCES_PATH;

/**
 * The Digit class provides a displayable image of a digit.
 */
public class Digit extends Actor{
	/**
	 * Specifies the path to the directory containing the images of digits.
	 */
	private static final String DIGITS_PATH = RESOURCES_PATH + "digits/";

	/**
	 * Specifies the width of the image. Measured in pixels.
	 */
	private static final int DIGIT_SIZE = 30;

	/**
	 * A List for storing references to all Images of all the digits.
	 */
	private static ArrayList<Image> digits;

	/**
	 * Creates a Digit and displays the corresponding Image at the specified coordinates.
	 * @param number Specifies the digit to display.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 */
	public Digit(int number, int xpos, int ypos) {
		if (digits == null) {
			digits = new ArrayList<>(10);
			for (int i = 0; i < 10; i++) {
				digits.add(new Image(DIGITS_PATH + i + ".png", DIGIT_SIZE, DIGIT_SIZE, true, true));
			}
		}
		setImage(digits.get(number));
		setX(xpos);
		setY(ypos);

	}

	/**
	 * Empty act method.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
	}
}
