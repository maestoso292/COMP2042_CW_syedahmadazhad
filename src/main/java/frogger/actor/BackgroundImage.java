package frogger.actor;

import javafx.scene.image.Image;

import static frogger.Main.*;

public class BackgroundImage extends Actor{
	private static final String BACKGROUNDS_PATH = RESOURCES_PATH + "backgrounds/";

	@Override
	public void act(long now) {
	}
	
	public BackgroundImage(String label) {
		setImage(new Image(BACKGROUNDS_PATH + label, X_UPPER_BOUND, Y_UPPER_BOUND, false, true));
		setX(0);
		setY(0);
	}

}
