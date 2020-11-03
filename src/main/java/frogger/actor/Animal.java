package frogger.actor;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import static frogger.Main.*;


public class Animal extends Actor {
	private static final String FROGGER_PATH = RESOURCES_PATH + "frogger/";
	private static final String DEATH_PATH = FROGGER_PATH + "deathAnim/";

	private static final int FROGGER_SIZE = 40;
	private static final double MOVEMENT_Y = 13.3333333*2;
	private static final double MOVEMENT_X = 10.666666*2;
	private static final double INIT_X_POS = 300;
	private static final double INIT_Y_POS = 679.8 + (MOVEMENT_Y * 3);

	private Image imgW1;
	private Image imgA1;
	private Image imgS1;
	private Image imgD1;
	private Image imgW2;
	private Image imgA2;
	private Image imgS2;
	private Image imgD2;

	private ArrayList<Image> waterDeathAnim;

	int points = 0;
	int end = 0;
	private boolean second = false;
	boolean noMove = false;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean stop = false;
	boolean changeScore = false;
	int carD = 0;
	double w = 800;
	ArrayList<End> inter = new ArrayList<End>();

	public Animal() {
		imgW1 = new Image(FROGGER_PATH + "froggerUp.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgA1 = new Image(FROGGER_PATH + "froggerLeft.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgS1 = new Image(FROGGER_PATH + "froggerDown.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgD1 = new Image(FROGGER_PATH + "froggerRight.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgW2 = new Image(FROGGER_PATH + "froggerUpJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgA2 = new Image(FROGGER_PATH + "froggerLeftJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgS2 = new Image(FROGGER_PATH + "froggerDownJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true);
		imgD2 = new Image(FROGGER_PATH + "froggerRightJump.png", FROGGER_SIZE, FROGGER_SIZE, true, true);

		waterDeathAnim = new ArrayList<>(4);
		for (int i = 0; i < 4; i++) {
			waterDeathAnim.add(new Image(DEATH_PATH + "waterdeath" + i + ".png", FROGGER_SIZE, FROGGER_SIZE, true, true));
		}

		setImage(imgW1);
		setX(INIT_X_POS);
		setY(INIT_Y_POS);

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if (noMove) {

				}
				else if (second) {
					switch (event.getCode()) {
						case W:
							move(0, -MOVEMENT_Y);
							changeScore = false;
							setImage(imgW1);
							break;
						case A:
							move(-MOVEMENT_X, 0);
							setImage(imgA1);
							break;
						case S:
							move(0, MOVEMENT_Y);
							setImage(imgS1);
							break;
						case D:
							move(MOVEMENT_X, 0);
							setImage(imgD1);
							break;
						default:
					}
					second = false;
				}
				else {
					switch (event.getCode()) {
						case W:
							move(0, -MOVEMENT_Y);
							setImage(imgW2);
							break;
						case A:
							move(-MOVEMENT_X, 0);
							setImage(imgA2);
							break;
						case S:
							move(0, MOVEMENT_Y);
							setImage(imgS2);
							break;
						case D:
							move(MOVEMENT_X, 0);
							setImage(imgD2);
							break;
						default:
					}
					second = true;
				}
			}
		});
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (noMove) {

				}
				else {
					switch (event.getCode()) {
						case W:
							if (getY() < w) {
								changeScore = true;
								w = getY();
								points+=10;
							}
							move(0, -MOVEMENT_Y);
							setImage(imgW1);
							break;
						case A:
							move(-MOVEMENT_X, 0);
							setImage(imgA1);
							break;
						case S:
							move(0, MOVEMENT_Y);
							setImage(imgS1);
							break;
						case D:
							move(MOVEMENT_X, 0);
							setImage(imgD1);
							break;
						default:
					}
					second = false;
	        	}
			}
		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			}
		});
	}
	
	@Override
	public void act(long now) {
		if (getY() < Y_LOWER_BOUND || getY() > Y_UPPER_BOUND) {
			setX(300);
			setY(679.8+ MOVEMENT_Y);
		}
		if (getX()<0) {
			move(MOVEMENT_Y * 2, 0);
		}
		if (carDeath) {
			noMove = true;
			if ((now)% 11 ==0) {
				carD++;
			}
			if (carD==1) {
				setImage(new Image(DEATH_PATH + "cardeath1.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
			}
			if (carD==2) {
				setImage(new Image(DEATH_PATH + "cardeath2.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
			}
			if (carD==3) {
				setImage(new Image(DEATH_PATH + "cardeath3.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
			}
			if (carD == 4) {
				setX(INIT_X_POS);
				setY(INIT_Y_POS);
				carDeath = false;
				carD = 0;
				setImage(new Image(FROGGER_PATH + "froggerUp.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
				noMove = false;
				if (points>50) {
					points-=50;
					changeScore = true;
				}
			}
			
		}
		if (waterDeath) {
			noMove = true;
			if ((now)% 11 ==0) {
				carD++;
			}
			if (carD < 4) {
				setImage(waterDeathAnim.get(carD));
			}
			else if (carD >= 4) {
				setX(INIT_X_POS);
				setY(INIT_Y_POS);
				waterDeath = false;
				carD = 0;
				setImage(new Image(FROGGER_PATH + "froggerUp.png", FROGGER_SIZE, FROGGER_SIZE, true, true));
				noMove = false;
				if (points>50) {
					points-=50;
					changeScore = true;
				}
			}
		}
		
		if (getX()>600) {
			move(-MOVEMENT_Y *2, 0);
		}
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		/*
		if (getX() == 240 && getY() == 82) {
			stop = true;
		}
		 */
		else if (getIntersectingObjects(Platform.class).size() >= 1 && !noMove) {
			Platform currentPlatform = getIntersectingObjects(Platform.class).get(0);
			move(currentPlatform.getSpeed(), 0);
			if (currentPlatform.getClass() == WetTurtle.class) {
				waterDeath = ((WetTurtle) currentPlatform).isSunk();
			}
		}
		else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			if (getIntersectingObjects(End.class).get(0).isActivated()) {
				end--;
				points-=50;
			}
			points+=50;
			changeScore = true;
			w=800;
			getIntersectingObjects(End.class).get(0).setEnd();
			end++;
			setX(INIT_X_POS);
			setY(INIT_Y_POS);
		}
		else if (getY()<413){
			waterDeath = true;
			//setX(300);
			//setY(679.8+movement);
		}
	}
	public boolean getStop() {
		return end==5;
	}
	
	public int getPoints() {
		return points;
	}
	
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
		
	}

	public void reset() {
		points = 0;
		end = 0;
	}
}
