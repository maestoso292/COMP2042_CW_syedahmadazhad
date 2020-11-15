package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Turtle extends Platform{
	public static final int TURTLE_SIZE = 130;

	private static final double TURTLE_PADDING = 1.7;
	private static final String TURTLE_PATH = PLATFORMS_PATH + "TurtleAnimation";
	private static final int NUM_TURTLE_ANIM = 3;

	private static ArrayList<Image> turtles;

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
	@Override
	public void act(long now) {
		super.act(now);
		setImage(turtles.get((int) (now / 900000000  % 3)));
	}
}
