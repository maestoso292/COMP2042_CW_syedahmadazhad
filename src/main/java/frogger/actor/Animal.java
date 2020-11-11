package frogger.actor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

import frogger.world.Level;
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
	private static final double INIT_Y_POS = Level.Section.THIRTEEN.getY() + Level.ActorType.FROGGER.getPadding();
	private static final double WATER_BOUNDARY = 413;

	private static Animal animal;

	private int froggerAnimCounter;
	private ArrayList<Image> froggerAnim;

	private int deathAnimCounter;
	private DeathType deathType;
	private HashMap<DeathType, ArrayList<Image>> deathAnimHashMap;

	private boolean noMove;
	private double furthestY;

	private int points;
	private int endsFilled;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public static Animal getInstance() {
		if (animal == null) {
			animal = new Animal();
		}
		return animal;
	}

	private Animal() {
		setImage(new Image(FROGGER_PATH + "froggerStill.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		froggerAnim = new ArrayList<>(2);
		froggerAnim.add(new Image(FROGGER_PATH + "froggerStill.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		froggerAnim.add(new Image(FROGGER_PATH + "froggerJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true));

		ArrayList<Image> waterDeathAnim = new ArrayList<>(NUM_WATER_DEATH_ANIM);
		for (int i = 0; i < NUM_WATER_DEATH_ANIM; i++) {
			waterDeathAnim.add(new Image(DEATH_PATH + "waterdeath" + i + ".png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		ArrayList<Image> carDeathAnim = new ArrayList<>(NUM_CAR_DEATH_ANIM);
		for (int i = 0; i < NUM_CAR_DEATH_ANIM; i++) {
			carDeathAnim.add(new Image(DEATH_PATH + "cardeath" + i + ".png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		// Store all death animations in a HashMap to avoid duplicate code for each animation
		deathAnimHashMap = new HashMap<>(2);
		deathAnimHashMap.put(DeathType.WATER, waterDeathAnim);
		deathAnimHashMap.put(DeathType.CAR, carDeathAnim);

		setOnKeyPressed(event -> handleMovement(event));
		setOnKeyReleased(event -> handleMovement(event));
	}
	
	@Override
	public void act(long now) {
		outOfBoundsCheck();
		collisionCheck();
		// Death animation, if any
		if (deathType != DeathType.NONE) {
			noMove = true;
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
		if (!noMove) {
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
			froggerAnimCounter = (++froggerAnimCounter) % froggerAnim.size();
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
			if (currentEnd.isActivated()) {
				setEndsFilled(endsFilled - 1);
				setPoints(points - 50);
			}
			else {
				setEndsFilled(endsFilled + 1);
				setPoints(points + 50);
				furthestY = Y_UPPER_BOUND;
				currentEnd.setEnd();
			}
			reset();
		}
		else if (getY() < WATER_BOUNDARY){
			deathType = DeathType.WATER;
		}
	}

	public void initialise() {
		/*
		for (PropertyChangeListener listener : propertyChangeSupport.getPropertyChangeListeners()) {
			propertyChangeSupport.removePropertyChangeListener(listener);
		}

		 */
		setPoints(0);
		endsFilled = 0;
		furthestY = Y_UPPER_BOUND;
		toFront();
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

	// Getters and setters required for beans property change listeners

	public int getEndsFilled() {
		return endsFilled;
	}

	public void setEndsFilled(int endsFilled) {
		if (endsFilled == 1) {
			int oldEndsFilled = this.endsFilled;
			propertyChangeSupport.firePropertyChange("endsFilled", oldEndsFilled, endsFilled);
		}
		this.endsFilled = endsFilled;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		int oldPoints = this.points;
		this.points = (points > 0 ? points : 0);
		propertyChangeSupport.firePropertyChange("points", oldPoints, this.points);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
