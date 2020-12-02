package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The Turtle class provides a Turtle platform that has a swimming animation in the Frogger game.
 */
public class Turtle extends Platform{
	/** Value of {@value #TURTLE_SIZE} which specifies the width of the Image to be displayed */
	public static final int TURTLE_SIZE = 130;

	/** Value of {@value #TURTLE_PADDING} which specifies how much vertical padding to use when instantiating. Measured in pixels. */
	private static final double TURTLE_PADDING = 1.7;

	/** Specifies the path of the image files to use for the Turtle instances. */
	private static final String TURTLE_PATH = PLATFORMS_PATH + "TurtleAnimation";

	/** Value of {@value #NUM_TURTLE_ANIM} which specifies the number of Images in the animation of Turtle instances. */
	private static final int NUM_TURTLE_ANIM = 3;

	/** A List to store references to all Images in the Turtle animation. */
	private static ArrayList<Image> turtles;

	/**
	 * Creates a Turtle at the specified coordinates, sets it's speed, and displays the corresponding Image. If
	 * Images have not been loaded, load the images for the animation into a static List.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 * @param speed Specifies the speed at which the instance should move across the screen. Measured in
	 *              pixels per frame.
	 */
	public Turtle(double xpos, double ypos, double speed) {
		super(xpos, ypos + TURTLE_PADDING, speed);
		if (turtles == null) {
			turtles = new ArrayList<>(NUM_TURTLE_ANIM);
			for (int i = 0; i < NUM_TURTLE_ANIM; i++) {
				turtles.add(new Image(TURTLE_PATH + i + ".png", TURTLE_SIZE, TURTLE_SIZE, true, true));
			}
		}
		setImage(turtles.get(1));
	}

	/**
	 * Plays the animation for Turtle instances by switching the displayed Image with those stored in a List.
	 * Called every frame.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
		super.act(now);
		setImage(turtles.get((int) (now / 900000000  % NUM_TURTLE_ANIM)));
	}
}
