package frogger;

import frogger.world.levels.*;
import frogger.world.misc.InfoPage;
import frogger.world.misc.LevelSelect;
import frogger.world.misc.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	// TODO Maybe store all resource paths to an enum?
	public static final String RESOURCES_PATH = "file:src/main/resources/";
	public static final String MISC_PATH = RESOURCES_PATH + "misc/";
	public static final int X_LOWER_BOUND = 0;
	public static final int X_UPPER_BOUND = 600;
	public static final int Y_LOWER_BOUND = 0;
	public static final int Y_UPPER_BOUND = 800;

	Navigation navController;
	MusicPlayer musicPlayer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Create an empty scene.
	    Scene scene  = new Scene(new MainMenu(), X_UPPER_BOUND, Y_UPPER_BOUND);
	    navController = Navigation.getNavigationController(scene);

	    navController.addDestination(MainMenu.class);
	    navController.addDestination(InfoPage.class);
	    navController.addDestination(LevelSelect.class);
		navController.addDestination(LevelOne.class);
		navController.addDestination(LevelTwo.class);
		navController.addDestination(LevelThree.class);
		navController.addDestination(LevelFour.class);
		navController.addDestination(LevelFive.class);
	    navController.addDestination(LevelRandom.class);

	    musicPlayer = new MusicPlayer();

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		start();
	}


	public void start() {
		navController.navigateTo(MainMenu.class);
		musicPlayer.playMusic();
    }

    public void stop() {
		musicPlayer.stopMusic();
    }
}
