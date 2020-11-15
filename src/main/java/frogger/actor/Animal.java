package frogger.actor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import frogger.world.Level;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static frogger.Main.*;


public class Animal extends Actor {
	private enum DeathType{
		NONE ("none", 0),
		WATER ("waterdeath", 4),
		CAR ("cardeath", 3);

		public final int numAnims;
		public final String label;
		DeathType(String label, int numAnims) {
			this.numAnims = numAnims;
			this.label = label;
		}
	}

	private static final String FROGGER_PATH = RESOURCES_PATH + "frogger/";
	private static final String DEATH_PATH = FROGGER_PATH + "deathAnim/";

	private static final int FROGGER_SIZE = 40;
	private static final double FROGGER_PADDING = 6.67;
	private static final double MOVEMENT_Y = 13.3333333*2;
	private static final double MOVEMENT_X = 10.666666*2;
	private static final double INIT_X_POS = 300;
	private static final double INIT_Y_POS = Level.Section.FOURTEEN.getY() + FROGGER_PADDING;

	private static ArrayList<Image> froggerAnim;
	private static HashMap<DeathType, ArrayList<Image>> deathAnimHashMap;

	private final double waterBoundary;
	private final PropertyChangeSupport propertyChangeSupport;

	private int froggerAnimCounter;
	private int deathAnimCounter;
	private DeathType deathType;
	private boolean noMove;
	private double furthestY;
	private int points;
	private int endsFilled;

	public Animal(double waterBoundary) {
		this.waterBoundary = waterBoundary;
		this.propertyChangeSupport = new PropertyChangeSupport(this);

		if (froggerAnim == null) {
			froggerAnim = new ArrayList<>(2);
			froggerAnim.add(new Image(FROGGER_PATH + "froggerStill.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
			froggerAnim.add(new Image(FROGGER_PATH + "froggerJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		// Store all death animations in a HashMap to avoid duplicate code for each animation
		if (deathAnimHashMap == null) {
			deathAnimHashMap = new HashMap<>(2);
			for (DeathType deathType : DeathType.values())  {
				if (deathType == DeathType.NONE) {
					continue;
				}
				ArrayList<Image> temp = new ArrayList<>(deathType.numAnims);
				for (int i = 0; i < deathType.numAnims; i++) {
					temp.add(new Image(DEATH_PATH + deathType.label + i + ".png", FROGGER_SIZE, FROGGER_SIZE, true, true));
				}
				deathAnimHashMap.put(deathType, temp);
			}
		}

		setOnKeyPressed(this::handleMovement);
		setOnKeyReleased(this::handleMovement);
	}
	
	@Override
	public void act(long now) {
		outOfBoundsCheck();
		collisionCheck();
		// Death animation, if any
		if (deathType != DeathType.NONE) {
			noMove = true;
			setRotate(0);
			ArrayList<Image> deathAnim = deathAnimHashMap.get(deathType);
			if ((now) % 11 == 0) {
				deathAnimCounter++;
			}

			if (deathAnimCounter < deathAnim.size()) {
				setImage(deathAnim.get(deathAnimCounter));
			}
			else {
				setPoints(points - 50);
				reset();
			}
		}
	}

	private void handleMovement(KeyEvent event) {
		if (!noMove && Arrays.asList(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D).contains(event.getCode())) {
			froggerAnimCounter = event.getEventType() == KeyEvent.KEY_RELEASED ? 0 : (++froggerAnimCounter) % froggerAnim.size();
			setImage(froggerAnim.get(froggerAnimCounter));
			switch (event.getCode()) {
				case W:
					if (getY() <= furthestY - FROGGER_SIZE) {
						setPoints(points + 10);
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
			froggerAnimCounter %= froggerAnim.size();
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
			if (currentPlatform.getClass() == WetTurtle.class && ((WetTurtle) currentPlatform).isSunk()) {
				deathType = DeathType.WATER;
			}
		}
		else if (getIntersectingObjects(End.class).size() >= 1) {
			End currentEnd = getIntersectingObjects(End.class).get(0);
			reset();
			if (currentEnd.isActivated()) {
				setPoints(points - 50);
				setEndsFilled(endsFilled - 1);
				currentEnd.setEnd(false);
			}
			else {
				setPoints(points + 50);
				setEndsFilled(endsFilled + 1);
				furthestY = Y_UPPER_BOUND;
				currentEnd.setEnd(true);
			}
		}
		else if (getY() + FROGGER_SIZE < waterBoundary){
			deathType = DeathType.WATER;
		}
	}

	public void initialise() {
		toFront();
		setPoints(0);
		endsFilled = 0;
		furthestY = Y_UPPER_BOUND;
		reset();
	}

	public void reset() {
		froggerAnimCounter = 0;
		deathAnimCounter = 0;
		deathType = DeathType.NONE;
		noMove = false;
		setX(INIT_X_POS);
		setY(INIT_Y_POS);
		setImage(froggerAnim.get(froggerAnimCounter));
		setRotate(0);
	}

	public int getEndsFilled() {
		return endsFilled;
	}

	public void setEndsFilled(int endsFilled) {
		int oldEndsFilled = this.endsFilled;
		propertyChangeSupport.firePropertyChange("endsFilled", oldEndsFilled, endsFilled);
		this.endsFilled = endsFilled;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		int oldPoints = this.points;
		this.points = (Math.max(points, 0));
		propertyChangeSupport.firePropertyChange("points", oldPoints, this.points);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
