package frogger;

import frogger.scene.Level;
import frogger.scene.MainMenu;
import frogger.scene.MusicPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import frogger.scene.SceneController;

public class Main extends Application {
	// TODO Maybe store all resource paths to an enum?
	public static final String RESOURCES_PATH = "file:src/main/resources/";
	public static final String MISC_PATH = RESOURCES_PATH + "misc/";
	public static final int X_LOWER_BOUND = 0;
	public static final int X_UPPER_BOUND = 600;
	public static final int Y_LOWER_BOUND = 0;
	public static final int Y_UPPER_BOUND = 800;

	Scene scene;
	SceneController sceneController;
	MainMenu mainMenu;
	Level level1;
	MusicPlayer musicPlayer;
	AnimationTimer timer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    level1 = new Level();
	    mainMenu = new MainMenu();
	    scene  = new Scene(mainMenu, X_UPPER_BOUND, Y_UPPER_BOUND);
	    sceneController = new SceneController(2, scene);
	    sceneController.addPane("Game", level1);
	    sceneController.addPane("Main", mainMenu);
	    sceneController.switchPane("Main");
	    sceneController.startPane();

	    musicPlayer = new MusicPlayer();

		primaryStage.setScene(scene);
		primaryStage.show();
		start();
	}

	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (scene.getRoot() == mainMenu && !mainMenu.isRunning()) {
            		System.out.println("Game started");
            		sceneController.switchPane("Game");
            		sceneController.startPane();
				}
            	else if (scene.getRoot() == level1 && !level1.isRunning()) {
					System.out.println("Game ended");
            		sceneController.switchPane("Main");
            		sceneController.startPane();
				}
            }
        };
    }

	public void start() {
		musicPlayer.playMusic();
    	createTimer();
        timer.start();
    }

    public void stop() {
		musicPlayer.stopMusic();
        timer.stop();
    }
}
