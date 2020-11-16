package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The WetTurtle class provides a WetTurtle platform that has a diving animation in the Frogger game.
 */
public class WetTurtle extends Platform{
	/**
	 * Specifies the width of the Image to be displayed
	 */
	public static final int WET_TURTLE_SIZE = 130;

	/**
	 * Specifies how much vertical padding to use when instantiating. Measured in pixels.
	 */
	private static final double WET_TURTLE_PADDING = 1.7;

	/**
	 * Specifies the path of the image files to use for the WetTurtle instances.
	 */
	private static final String WET_TURTLE_PATH = PLATFORMS_PATH + "WetTurtleAnimation";

	/**
	 * Specifies the number of Images in the animation of WetTurtle instances.
	 */
	private static final int NUM_WET_TURTLE_ANIM = 4;

	/**
	 * A List to store references to all Images in the WetTurtle animation.
	 */
	private static ArrayList<Image> turtles;

	/**
	 * Instance variable specifying the whether the instance has 'sunk' below the water.
	 */
	private boolean sunk = false;

	/**
	 * Creates a WetTurtle at the specified coordinates, sets it's speed, and displays the corresponding Image.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 * @param speed Specifies the speed at which the instance should move across the screen. Measured in
	 *              pixels per frame.
	 */
	public WetTurtle(double xpos, double ypos, double speed) {
		super(xpos, ypos + WET_TURTLE_PADDING, speed);
		if (turtles == null) {
			turtles = new ArrayList<>(NUM_WET_TURTLE_ANIM);
			for (int i = 0; i < NUM_WET_TURTLE_ANIM; i++) {
				turtles.add(new Image(WET_TURTLE_PATH + i + ".png", WET_TURTLE_SIZE, WET_TURTLE_SIZE, true, true));
			}
		}
		setImage(turtles.get(1));
	}

	/**
	 * Plays the animation for WetTurtle instances by switching the displayed Image with those stored in a List. Sets
	 * the instance's sunk value depending on Image displayed.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
		super.act(now);
		setImage(turtles.get((int) (now / 900000000 % 4)));
		sunk = getImage() == turtles.get(3);
	}

	/**
	 * Checks whether the WetTurtle is sunk.
	 * @return The sunk state of the WetTurtle
	 */
	public boolean isSunk() {
		return sunk;
	}
}
