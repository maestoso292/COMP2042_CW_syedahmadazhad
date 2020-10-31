package p4_group_8_repo;

import javafx.scene.image.Image;

import static p4_group_8_repo.Main.RESOURCES_PATH;
import static p4_group_8_repo.Turtle.TURTLE_SIZE;

public class WetTurtle extends Platform{
	private static final String WET_TURTLE_PATH = PLATFORMS_PATH + "WetTurtleAnimation";
	private Image[] turtle;
	/*
	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;
	*/
	boolean sunk = false;

	public WetTurtle(int xpos, int ypos, int speed) {
		turtle = new Image[4];
		for (int i = 0; i < 4; i++) {
			turtle[i] = new Image(WET_TURTLE_PATH + (i + 1) + ".png", TURTLE_SIZE, TURTLE_SIZE, true, true);
		}
		/*
		turtle1 = new Image(PLATFORMS_PATH + "TurtleAnimation1.png", TURTLE_SIZE, TURTLE_SIZE, true, true);
		turtle2 = new Image(PLATFORMS_PATH + "WetTurtleAnimation2.png", TURTLE_SIZE, TURTLE_SIZE, true, true);
		turtle3 = new Image(PLATFORMS_PATH + "WetTurtleAnimation3.png", TURTLE_SIZE, TURTLE_SIZE, true, true);
		turtle4 = new Image(PLATFORMS_PATH + "WetTurtleAnimation4.png", TURTLE_SIZE, TURTLE_SIZE, true, true);
		*/
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setImage(turtle[1]);
	}

	@Override
	public void act(long now) {
		// Changed if else if to switch. By default sunk = false
		setImage(turtle[(int) (now / 900000000 % 4)]);
		sunk = getImage() == turtle[3];

		/*
		switch ((int) (now/900000000 % 4)) {
			case 0:
				setImage(turtle2);
				break;
			case 1:
				setImage(turtle1);
				break;
			case 2:
				setImage(turtle3);
				break;
			case 3:
				setImage(turtle4);
				sunk = true;
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

	public boolean isSunk() {
		return sunk;
	}
}
