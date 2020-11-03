package frogger.actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

import static frogger.Main.RESOURCES_PATH;

public class Digit extends Actor{
	private static final String DIGITS_PATH = RESOURCES_PATH + "digits/";
	private static final int DIGIT_SIZE = 30;
	private ArrayList<Image> digits;

	public Digit(int n, int x, int y) {
		if (digits == null) {
			digits = new ArrayList<>(10);
			for (int i = 0; i < 10; i++) {
				digits.add(new Image(DIGITS_PATH + i + ".png", DIGIT_SIZE, DIGIT_SIZE, true, true));
			}
		}
		setImage(digits.get(n));
		setX(x);
		setY(y);

	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
}
