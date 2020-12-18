package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * The End class provides a displayable image of the end goal of the Frogger game. The instance can be toggled to
 * display an empty or filled in end goal.
 */
public class End extends InteractiveActor{
	/** Specifies the path to the directory containing the images of end **/
	private static final String END_PATH = "file:src/main/resources/misc/";

	/** Specifies different sizes for the images of the end goal */
	private static final int[] END_SIZES = {60, 70};

	/** A List to store references to the Images of the empty and filled in end goals. */
	private static ArrayList<Image> ends;

	/** Specifies the activation state of the instance.*/
	private boolean activated = false;

	/**
	 * Creates an End at the specified coordinates with a default activation state of false. This means the displayed
	 * image is of the empty end goal. If Images have not been loaded, load the images of all End types
	 * into a static List.
	 * @param xpos Specifies the x-coordinate. Measured in pixels.
	 * @param ypos Specifies the y-coordinate. Measured in pixels.
	 */
	public End(int xpos, int ypos) {
		super(xpos, ypos);
		if (ends == null) {
			ends = new ArrayList<>(END_SIZES.length);
			for (int i = 0; i < END_SIZES.length; i++) {
				ends.add(new Image(END_PATH + "end" + i + ".png", END_SIZES[i], END_SIZES[i], true, true));
			}
		}
	}

	/**
	 * Sets the activation state of the instance and switch the display to the corresponding Image.
	 * @param activated Specifies the new value for the activation state.
	 */
	public void setEnd(boolean activated) {
		setImage(ends.get(activated ? 1 : 0));
		this.activated = activated;
	}

	/**
	 * Check whether the instance is activated.
	 * @return The activation state.
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * Empty act method.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
	}

}
