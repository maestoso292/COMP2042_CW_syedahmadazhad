package frogger;

import frogger.navigation.NavController;
import frogger.navigation.Navigation;
import frogger.world.levels.*;
import frogger.world.misc.InfoPage;
import frogger.world.misc.LevelSelect;
import frogger.world.misc.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class is the entry point the Frogger application lifecycle and performs what a JavaFX Application does.
 */
public class Main extends Application {
	/** Specifies a path to the resources directory. */
	public static final String RESOURCES_PATH = "file:src/main/resources/";

	/** Specifies a path to the miscellaneous resources directory. */
	public static final String MISC_PATH = RESOURCES_PATH + "misc/";

	/** Value of {@value #X_LOWER_BOUND} which specifies the minimum x-coordinate in the application window. Measured in pixels */
	public static final int X_LOWER_BOUND = 0;

	/** Value of {@value #X_UPPER_BOUND} which specifies the maximum x-coordinate in the application window. Measured in pixels */
	public static final int X_UPPER_BOUND = 600;

	/** Value of {@value #Y_LOWER_BOUND} which specifies the minimum y-coordinate in the application window. Measured in pixels */
	public static final int Y_LOWER_BOUND = 0;

	/** Value of {@value #Y_UPPER_BOUND} which specifies the maximum y-coordinate in the application window. Measured in pixels */
	public static final int Y_UPPER_BOUND = 800;

	/** Instance of NavController for adding and navigating to destinations. */
	private NavController navController;

	/** Instance of musicPlayer for playing media files indefinitely.*/
	private MusicPlayer musicPlayer;

	/**
	 * Main method that starts the Frogger application.
	 * @param args JVM arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Method for starting the application where the Scene is instanced and all destinations are added to the
	 * NavController.
	 * @param primaryStage The Stage for the Frogger application.
	 */
	@Override
	public void start(Stage primaryStage) {
		MainMenu mainMenu = new MainMenu();
	    Scene scene  = new Scene(mainMenu, X_UPPER_BOUND, Y_UPPER_BOUND);
	    navController = Navigation.getNavigationController(scene);

	    navController.addDestination(mainMenu);
	    navController.addDestination(InfoPage.class);
	    navController.addDestination(LevelSelect.class);

		navController.addDestination(LevelFactory.provideLevel(LevelOne.class));
		navController.addDestination(LevelFactory.provideLevel(LevelTwo.class));
		navController.addDestination(LevelFactory.provideLevel(LevelThree.class));
		navController.addDestination(LevelFactory.provideLevel(LevelFour.class));
		navController.addDestination(LevelFactory.provideLevel(LevelFive.class));
		navController.addDestination(LevelFactory.provideLevel(LevelSix.class));
		navController.addDestination(LevelFactory.provideLevel(LevelSeven.class));
		navController.addDestination(LevelFactory.provideLevel(LevelEight.class));
		navController.addDestination(LevelFactory.provideLevel(LevelNine.class));
	    navController.addDestination(LevelFactory.provideLevel(LevelRandom.class));

	    musicPlayer = new MusicPlayer();

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		start();
	}

	/**
	 * Navigate to MainMenu destination and plays the Frogger Main Song Theme.
	 */
	public void start() {
		navController.startCurrentDestination();
		musicPlayer.playMusic( MISC_PATH.replace("file:", "") + "Frogger Main Song Theme (loop).mp3");
    }

	/** Stops playing music. */
	public void stop() {
		musicPlayer.stopMusic();
    }
}
