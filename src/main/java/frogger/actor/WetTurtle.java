package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

import static frogger.actor.Turtle.TURTLE_SIZE;

public class WetTurtle extends Platform{
	private static final String WET_TURTLE_PATH = PLATFORMS_PATH + "WetTurtleAnimation";
	private static final int NUM_WET_TURTLE_ANIM = 4;

	private static ArrayList<Image> turtles;
	private boolean sunk = false;

	public WetTurtle(int xpos, int ypos, int speed) {
		super(xpos, ypos, speed);
		if (turtles == null) {
			turtles = new ArrayList<>(NUM_WET_TURTLE_ANIM);
			for (int i = 0; i < NUM_WET_TURTLE_ANIM; i++) {
				turtles.add(new Image(WET_TURTLE_PATH + i + ".png", TURTLE_SIZE, TURTLE_SIZE, true, true));
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
