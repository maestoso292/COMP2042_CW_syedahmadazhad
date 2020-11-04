package frogger.actor;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import static frogger.Main.*;


public class Animal extends Actor {
	private enum DeathType{
		NONE, WATER, CAR
	}

	private static final String FROGGER_PATH = RESOURCES_PATH + "frogger/";
	private static final String DEATH_PATH = FROGGER_PATH + "deathAnim/";
	private static final int NUM_WATER_DEATH_ANIM = 4;
	private static final int NUM_CAR_DEATH_ANIM = 3;

	private static final int FROGGER_SIZE = 40;
	private static final double MOVEMENT_Y = 13.3333333*2;
	private static final double MOVEMENT_X = 10.666666*2;
	private static final double INIT_X_POS = 300;
	private static final double INIT_Y_POS = 679.8 + (MOVEMENT_Y * 3);
	private static final double WATER_BOUNDARY = 413;

	private Image imgFroggerStill;
	private Image imgFroggerJump;

	private int deathAnimCounter;
	private DeathType deathType;
	private HashMap<DeathType, ArrayList<Image>> deathAnim;

	private boolean noMove;
	private boolean second;
	private int points;
	private boolean scoreChanged;
	private int endsFilled;
	private double furthestY;

	public Animal() {
		imgFroggerStill = new Image(FROGGER_PATH + "froggerStill.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgFroggerJump = new Image(FROGGER_PATH + "froggerJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true);

		ArrayList<Image> waterDeathAnim = new ArrayList<>(NUM_WATER_DEATH_ANIM);
		for (int i = 0; i < NUM_WATER_DEATH_ANIM; i++) {
			waterDeathAnim.add(new Image(DEATH_PATH + "waterdeath" + i + ".png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		ArrayList<Image> carDeathAnim = new ArrayList<>(NUM_CAR_DEATH_ANIM);
		for (int i = 0; i < NUM_CAR_DEATH_ANIM; i++) {
			carDeathAnim.add(new Image(DEATH_PATH + "cardeath" + i + ".png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		// Store all death animations in a HashMap to avoid duplicate code for each animation
		deathAnim = new HashMap<>(2);
		deathAnim.put(DeathType.WATER, waterDeathAnim);
		deathAnim.put(DeathType.CAR, carDeathAnim);

		setOnKeyPressed(event -> handleMovement(event, second));
		setOnKeyReleased(event -> handleMovement(event, true));
	}
	
	@Override
	public void act(long now) {
		outOfBoundsCheck();
		collisionCheck();
		// Death animation, if any
		if (deathType != DeathType.NONE) {
			noMove = true;
			ArrayList<Image> deathAnim = this.deathAnim.get(deathType);
			if ((now) % 11 == 0) {
				deathAnimCounter++;
			}

			if (deathAnimCounter < deathAnim.size()) {
				setImage(deathAnim.get(deathAnimCounter));
			}
			else {
				decrementPoints();
				reset();
			}
		}
	}

	private void handleMovement(KeyEvent event, boolean second) {
		if (!noMove) {
			setImage(second ? imgFroggerStill : imgFroggerJump);
			switch (event.getCode()) {
				case W:
					if (getY() <= furthestY - FROGGER_SIZE) {
						incrementPoints(10);
						furthestY = getY();
					}
					move(0, -MOVEMENT_Y);
					setRotate(0);
					break;
				case A:
					move(-MOVEMENT_X, 0);
					setRotate(-90);
					break;
				case S:
					move(0, MOVEMENT_Y);
					setRotate(180);
					break;
				case D:
					move(MOVEMENT_X, 0);
					setRotate(90);
					break;
				default:
			}
			this.second = !second;
		}
	}

	private void outOfBoundsCheck() {
		if (getY() < Y_LOWER_BOUND || getY() > Y_UPPER_BOUND - FROGGER_SIZE) {
			setY(INIT_Y_POS);
		}
		if (getX() < X_LOWER_BOUND) {
			setX(X_LOWER_BOUND);
		}
		else if (getX() > X_UPPER_BOUND - FROGGER_SIZE) {
			setX(X_UPPER_BOUND - FROGGER_SIZE);
		}
	}

	private void collisionCheck() {
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			deathType = DeathType.CAR;
		}
		else if (getIntersectingObjects(Platform.class).size() >= 1 && !noMove) {
			Platform currentPlatform = getIntersectingObjects(Platform.class).get(0);
			move(currentPlatform.getSpeed(), 0);
			if (currentPlatform.getClass() == WetTurtle.class) {
				if (((WetTurtle) currentPlatform).isSunk()) {
					deathType = DeathType.WATER;
				}
			}
		}
		else if (getIntersectingObjects(End.class).size() >= 1) {
			End currentEnd = getIntersectingObjects(End.class).get(0);
			if (currentEnd.isActivated()) {
				endsFilled--;
				decrementPoints();
			}
			else {
				endsFilled++;
				incrementPoints(50);
				furthestY = Y_UPPER_BOUND;
				currentEnd.setEnd();
			}
			reset();
		}
		else if (getY() < WATER_BOUNDARY){
			deathType = DeathType.WATER;
		}
	}

	private void decrementPoints() {
		if (points > 50) {
			points -= 50;
			scoreChanged = true;
		}
	}

	private void incrementPoints(int points) {
		this.points += points;
		scoreChanged = true;
	}

	public boolean getStop() {
		return endsFilled == 5;
	}

	public int getPoints() {
		return points;
	}

	public boolean isScoreChanged() {
		if (scoreChanged) {
			scoreChanged = false;
			return true;
		}
		return false;
	}

	public void initialise() {
		points = 0;
		endsFilled = 0;
		furthestY = Y_UPPER_BOUND;
		reset();
	}

	public void reset() {
		deathType = DeathType.NONE;
		deathAnimCounter = 0;
		scoreChanged = false;
		second = false;
		noMove = false;
		setX(INIT_X_POS);
		setY(INIT_Y_POS);
		setImage(imgFroggerStill);
		setRotate(0);
	}
}
