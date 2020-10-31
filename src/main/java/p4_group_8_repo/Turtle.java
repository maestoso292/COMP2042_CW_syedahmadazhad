package p4_group_8_repo;

import javafx.scene.image.Image;

public class Turtle extends Platform{
	public static final int TURTLE_SIZE = 130;
	private static final String TURTLE_PATH = PLATFORMS_PATH + "TurtleAnimation";
	private Image[] turtle;
	/*
	Image turtle1;
	Image turtle2;
	Image turtle3;
	*/

	public Turtle(int xpos, int ypos, int speed) {
		turtle = new Image[3];
		for (int i = 0; i < 3; i++) {
			turtle[i] = new Image(TURTLE_PATH + (i + 1) + ".png", TURTLE_SIZE, TURTLE_SIZE, true, true);
		}
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setImage(turtle[1]);
	}

	@Override
	public void act(long now) {
		setImage(turtle[(int) (now / 900000000  % 3)]);
		// Changed if else if to switch
		/*
		switch ((int) (now/900000000  % 3)) {
			case 0:
				setImage(turtle2);
				break;
			case 1:
				setImage(turtle1);
				break;
			case 2:
				setImage(turtle3);
				break;
			default:
		}
		*/
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}
}
