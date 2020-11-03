package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

import static frogger.Main.MISC_PATH;

public class End extends Actor{
	private static final int[] END_SIZES = {60, 60};
	private static ArrayList<Image> ends;

	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}
	
	public End(int x, int y) {
		super();
		setX(x);
		setY(y);
		if (ends == null) {
			ends = new ArrayList<>(END_SIZES.length);
			for (int i = 0; i < END_SIZES.length; i++) {
				ends.add(new Image(MISC_PATH + "end" + i + ".png", END_SIZES[i], END_SIZES[i], true, true));
			}
		}
	}
	
	public void setEnd() {
		setImage(ends.get(1));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void reset() {
		setImage(ends.get(0));
		activated = false;
	}

}
