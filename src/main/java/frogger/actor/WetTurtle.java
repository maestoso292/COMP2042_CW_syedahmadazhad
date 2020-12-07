package frogger.actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The WetTurtle class provides a WetTurtle platform that has a diving animation in the Frogger game.
 */
public class WetTurtle extends SinkingPlatform{
	/**
	 * Value of {@value #WET_TURTLE_SIZE} which specifies the width of the Image to be displayed
	 */
	public static final int WET_TURTLE_SIZE = 130;

	/**
	 * Value of {@value #WET_TURTLE_PADDING} which specifies how much vertical padding to use when instantiating. Measured in pixels.
	 */
	private static final double WET_TURTLE_PADDING = 1.7;

	/**
	 * Specifies the path of the image files to use for the WetTurtle instances.
	 */
	private static final String WET_TURTLE_PATH = PLATFORMS_PATH + "WetTurtleAnimation";

	/**
	 * Value of {@value #NUM_WET_TURTLE_ANIM} which specifies the number of Images in the animation of WetTurtle instances.
	 */
	private static final int NUM_WET_TURTLE_ANIM = 4;

	/**
	 * A List to store references to all Images in the WetTurtle animation.
	 */
	private static ArrayList<Image> turtles;

	/**
	 * Specifies the index, in the List of Images, of initial Image to display.
	 */
	private final int initialImageNumber;

	/**
	 * Creates a WetTurtle at the specified coordinates, sets it's speed, and displays the corresponding Image. If
	 * Images have not been loaded, load the images for the animation into a static List.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 * @param speed Specifies the speed at which the instance should move across the screen. Measured in
	 *              pixels per frame.
	 * @param synchronised Specifies whether the animation for this instance is synchronised with other instances.
	 *                     Other instances must all be synchronised as well.
	 */
	public WetTurtle(double xpos, double ypos, double speed, boolean synchronised) {
		super(xpos, ypos + WET_TURTLE_PADDING, speed);
		if (turtles == null) {
			turtles = new ArrayList<>(NUM_WET_TURTLE_ANIM);
			for (int i = 0; i < NUM_WET_TURTLE_ANIM; i++) {
				turtles.add(new Image(WET_TURTLE_PATH + i + ".png", WET_TURTLE_SIZE, WET_TURTLE_SIZE, true, true));
			}
		}
		initialImageNumber = synchronised ? 0 : ThreadLocalRandom.current().nextInt(0, NUM_WET_TURTLE_ANIM);
		setImage(turtles.get(initialImageNumber));
	}

	/**
	 * Plays the animation for WetTurtle instances by switching the displayed Image with those stored in a List. Sets
	 * the instance's sunk value depending on Image displayed. Called every frame.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
		super.act(now);
		setImage(turtles.get((int) ((initialImageNumber + now / 900000000) % NUM_WET_TURTLE_ANIM)));
		setSunk(getImage() == turtles.get(3));
	}
}
