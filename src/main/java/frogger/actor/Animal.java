package frogger.actor;

import frogger.world.levels.Level;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * The Animal class provides a playable character that can handle key inputs from the user. The Animal instance will
 * interact with other nodes that it's bounds intersect with. The Animal instance also keeps track of level
 * progress(how many end goals reached) and the current score.
 */
public class Animal extends Actor{
	/** An enumeration describing various death types and the number of unique images in the corresponding animation. */
	private enum DeathType{
		/** Specifies a null death type with no animations. */
		NONE ("none", 0),
		/** Specifies a death type of falling into water with 4 images for animations. */
		WATER ("waterdeath", 4),
		/** Specifies a death type due to Obstacle collision with 3 images for animations. */
		CAR ("cardeath", 3);

		/** Specifies the number of images in the animation for the DeathType. */
		public final int numAnims;

		/** Specifies the label of the DeathType. */
		public final String label;

		/**
		 * Creates a DeathType with the specified label and number of images in its animation.
		 * @param label The label of the DeathType.
		 * @param numAnims The number of unique images in the animation.
		 */
		DeathType(String label, int numAnims) {
			this.numAnims = numAnims;
			this.label = label;
		}
	}

	/** Specifies the path to the directory containing images for the Frogger. */
	private static final String FROGGER_PATH = "file:src/main/resources/frogger/";

	/** Specifies the path to the directory containing images for death animations. */
	private static final String DEATH_PATH = FROGGER_PATH + "deathAnim/";

	/** Value of {@value #FROGGER_SIZE} which specifies the size of the image to display. */
	private static final int FROGGER_SIZE = 40;

	/** Value of {@value #FROGGER_PADDING} which specifies how much vertical padding to use when instantiating. Measured in pixels. */
	private static final double FROGGER_PADDING = 6.67;

	/** Value of {@value #MOVEMENT_Y} which specifies how much an Animal instance will move in the vertical direction. Measured in pixels. */
	private static final double MOVEMENT_Y = 13.3333333*2;

	/** Value of {@value #MOVEMENT_X} which specifies how much an Animal instance will move in the horizontal direction. Measured in pixels. */
	private static final double MOVEMENT_X = 10.666666*2;

	/** Value of {@value #INIT_X_POS} which specifies the initial x-coordinate of an Animal instance. */
	private static final double INIT_X_POS = 300;

	/** Specifies the initial y-coordinate of an Animal instance. */
	private static final double INIT_Y_POS = Level.Section.FOURTEEN.getY() + FROGGER_PADDING;

	/** Value of {@value #ANIMATION_FRAMERATE} which specifies the number of frames per second. Used in death animation timeline */
	private static final double ANIMATION_FRAMERATE = 5;

	/** A List to store references to images in standard Frogger animation */
	private static ArrayList<Image> froggerAnim;

	/** A HashMap to store Animation {@linkplain Timeline Timelines} for frogger death animations */
	private HashMap<DeathType, Timeline> deathTimelineMap;

	/** Specifies the y-coordinate where the water region begins*/
	private final double waterBoundary;

	/** Used for adding and removing property listeners and firing events on property change*/
	private PropertyChangeSupport propertyChangeSupport;

	/** Specifies the state of this instances jump animation. 0 for still. 1 for jump. */
	private int froggerAnimCounter;

	/** Specifies the DeathType of this instance. */
	private DeathType deathType;

	/** Specifies whether this instance is prohibited from moving.*/
	private boolean noMove;

	/** Specifies the furthest(minimum) y-coordinate that this instance has reached. Measured in pixels. */
	private double furthestY;

	/** Specifies the current player score. */
	private int points;

	/** Specifies the number of end goals reached */
	private int endsFilled;

	/**
	 * Creates an Animal while loading all required images(still and jumping frog along with all images required for
	 * death animations) and sets onKeyPressed and onKeyReleased EventHandlers.
	 * @param waterBoundary y-coordinate of where the water region begins in the Level this instance is a child of.
	 */
	public Animal(double waterBoundary) {
		this.waterBoundary = waterBoundary;
		this.propertyChangeSupport = new PropertyChangeSupport(this);

		if (froggerAnim == null) {
			froggerAnim = new ArrayList<>(2);
			froggerAnim.add(new Image(FROGGER_PATH + "froggerStill.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
			froggerAnim.add(new Image(FROGGER_PATH + "froggerJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		createDeathAnimationTimeline();

		setOnKeyPressed(this::handleMovement);
		setOnKeyReleased(this::handleMovement);
	}

	/**
	 * Calls {@link #outOfBoundsCheck()} and {@link #collisionCheck()} and plays a death animation, if any. Called
	 * every frame.
	 * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
	 */
	@Override
	public void act(long now) {
		outOfBoundsCheck();
		collisionCheck();
	}

	/**
	 * Creates Timelines for each DeathType (excluding DeathType.NONE) and stores them in a static HashMap
	 * only if the HashMap is null.
	 */
	public void createDeathAnimationTimeline() {
		deathTimelineMap = new HashMap<>(DeathType.values().length - 1);
		for (DeathType deathType : DeathType.values())  {
			if (deathType == DeathType.NONE) {
				continue;
			}
			Timeline timeline = new Timeline(10);
			for (int i = 0; i < deathType.numAnims; i++) {
				Image sprite = new Image(DEATH_PATH + deathType.label + i + ".png",
						FROGGER_SIZE, FROGGER_SIZE, true, true);
				KeyValue keyValue = new KeyValue(imageProperty(), sprite);
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000 / ANIMATION_FRAMERATE * i), keyValue));
			}
			timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000 / ANIMATION_FRAMERATE * deathType.numAnims), new KeyValue(imageProperty(), froggerAnim.get(0))));
			timeline.setCycleCount(1);
			timeline.setOnFinished(event -> {
				setPoints(points - 50);
				reset();
			});
			deathTimelineMap.put(deathType, timeline);
		}
	}

	/**
	 * Plays the Animation Timeline that corresponds to the current value of deathType. Has no effect if timeline
	 * is currently running.
	 */
	public void playDeathAnimation() {
		noMove = true;
		setRotate(0);
		if (!deathTimelineMap.get(deathType).getStatus().equals(Animation.Status.RUNNING)) {
			deathTimelineMap.get(deathType).playFromStart();
		}
	}

	/**
	 * Moves instance corresponding to key pressed and plays jump animation. EventHandlers called for
	 * KeyPressed and KeyReleased events.
	 * @param event The KeyEvent that is fired.
	 */
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

	/**
	 * Checks whether instance has left application window bounds and resets position if so.
	 */
	private void outOfBoundsCheck() {
		Bounds worldBounds = getWorld().getLayoutBounds();
		if (getY() < worldBounds.getMinY() || getY() > worldBounds.getMaxY() - FROGGER_SIZE) {
			setY(INIT_Y_POS);
		}
		if (getX() < worldBounds.getMinX()) {
			setX(worldBounds.getMinX());
		}
		else if (getX() > worldBounds.getMaxX() - FROGGER_SIZE) {
			setX(worldBounds.getMaxX() - FROGGER_SIZE);
		}
	}

	/**
	 * Checks whether this instance's bounds intersect with other Actor objects and handles it if so. If intersecting
	 * with an Obstacle instance, {@link #deathType} is set to DeathType.CAR. If intersecting with a Platform instance,
	 * move the Animal instance at the same speed as the Plaform instance. If intersected Platform is a SinkingPlatform
	 * and isSunk(), deathType is set to DeathType.WATER. Calls {@link #playDeathAnimation()} if applicable.
	 * Checking for whether this instance has reached one of the end goals is done here.
	 */
	private void collisionCheck() {
		if (getIntersectingObjects(InteractiveActor.class).size() >= 1) {
			if (getIntersectingObjects(Obstacle.class).size() >= 1) {
				deathType = DeathType.CAR;
				playDeathAnimation();
			}
			else if (getIntersectingObjects(Platform.class).size() >= 1 && !noMove) {
				Platform currentPlatform = getIntersectingObjects(Platform.class).get(0);
				move(currentPlatform.getSpeed(), 0);
				if (currentPlatform instanceof SinkingPlatform && ((SinkingPlatform) currentPlatform).isSunk()) {
					deathType = DeathType.WATER;
					playDeathAnimation();
				}
			}
			else if (getIntersectingObjects(End.class).size() >= 1) {
				End currentEnd = getIntersectingObjects(End.class).get(0);
				reset();
				if (currentEnd.isActivated()) {
					setPoints(points - 50);
					setEndsFilled(endsFilled - 1);
					currentEnd.setEnd(false);
				} else {
					setPoints(points + 50);
					setEndsFilled(endsFilled + 1);
					furthestY = getWorld().getLayoutBounds().getMaxY();
					currentEnd.setEnd(true);
				}
			}
		}
		else if (getY() + FROGGER_SIZE < waterBoundary){
			deathType = DeathType.WATER;
			playDeathAnimation();
		}
	}

	/** Initialises the default values for the instance. Resets points to 0. Resets end goals reached to 0. Reset
	 * furthest y-position reached to application window y-coordinate upper bound. Calls {@link #reset()}
	 */
	public void initialise() {
		setPoints(0);
		endsFilled = 0;
		furthestY = getWorld().getLayoutBounds().getMaxY();
		reset();
	}

	/**
	 * Resets the state and position of the instance. Resets animation counters. Resets deathType to DeathType.NONE.
	 * Resets x and y positions. Resets image displayed to a still Frogger image. Sets rotate to 0.
	 */
	public void reset() {
		froggerAnimCounter = 0;
		deathType = DeathType.NONE;
		setX(INIT_X_POS);
		setY(INIT_Y_POS);
		setImage(froggerAnim.get(froggerAnimCounter));
		setRotate(0);
		noMove = false;
	}

	/**
	 * Sets the number of endsFilled to a specified number and fires a PropertyChangeEvent to Listeners.
	 * @param endsFilled The new number of end goals reached.
	 */
	public void setEndsFilled(int endsFilled) {
		int oldEndsFilled = this.endsFilled;
		propertyChangeSupport.firePropertyChange("endsFilled", oldEndsFilled, endsFilled);
		this.endsFilled = endsFilled;
	}

	/**
	 * Get the number of end goals reached.
	 * @return The current number of end goals reached
	 */
	public int getEndsFilled() {
		return endsFilled;
	}

	/**
	 * Get the current score.
	 * @return The current points value.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Sets the current score to a specified value and fires a PropertyChangeEvent to Listeners.
	 * @param points The new score value.
	 */
	public void setPoints(int points) {
		int oldPoints = this.points;
		this.points = (Math.max(points, 0));
		propertyChangeSupport.firePropertyChange("points", oldPoints, this.points);
	}

	/**
	 * Adds a Listener to a list of PropertyChangeListeners.
	 * @param propertyName A String object representing the name of the property that the Listener is listening to.
	 * @param listener The Listener object.
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Removes a Listener from the list of PropertyChangeListeners.
	 * @param listener The Listener object.
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
