package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class WetTurtle extends Platform{
	public static final int WET_TURTLE_SIZE = 130;

	private static final String WET_TURTLE_PATH = PLATFORMS_PATH + "WetTurtleAnimation";
	private static final double WET_TURTLE_PADDING = 1.7;
	private static final int NUM_WET_TURTLE_ANIM = 4;

	private static ArrayList<Image> turtles;

	private boolean sunk = false;

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

	@Override
	public void act(long now) {
		super.act(now);
		setImage(turtles.get((int) (now / 900000000 % 4)));
		sunk = getImage() == turtles.get(3);
	}

	public boolean isSunk() {
		return sunk;
	}
}
