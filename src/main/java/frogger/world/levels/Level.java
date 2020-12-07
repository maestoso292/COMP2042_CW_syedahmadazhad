package frogger.world.levels;

import frogger.navigation.Navigation;
import frogger.actor.*;
import frogger.highscore.HighscoreController;
import frogger.world.World;
import frogger.world.misc.MainMenu;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Level is the base abstract class which all Frogger levels must be a subclass of. Each instance of Level may act as
 * the root node of the JavaFX scene graph. Level provides the generation of all child nodes that must exist in all
 * Frogger levels such as {@link Animal} and {@link BackgroundImage}. Level also contains a
 * {@link HighscoreController} that will handle keeping track of highscores for each level. Level implements
 * Java Beans {@link PropertyChangeListener} to listen to changes in highscore and the number of end goals reached by
 * the user.
 */
public abstract class Level extends World implements PropertyChangeListener {
    /**
     * A constant with value {@value #SECTION_HEIGHT} that specifies the height of every row in the Frogger game.
     */
    public static final double SECTION_HEIGHT = 800.0 / 15;

    /**
     * An enumeration that specifies each row of the Frogger level and its corresponding y-coordinate.
     */
    public enum Section {
        /** Specifies the zeroth row with y-coordinate of 0 */
        ZERO (0),
        /** Specifies the first row with y-coordinate of Level.SECTION_HEIGHT */
        ONE (SECTION_HEIGHT),
        /** Specifies the second row with y-coordinate of 2 multiplied by Level.SECTION_HEIGHT */
        TWO (SECTION_HEIGHT * 2),
        /** Specifies the third row with y-coordinate of 3 multiplied by Level.SECTION_HEIGHT */
        THREE (SECTION_HEIGHT * 3),
        /** Specifies the fourth row with y-coordinate of 4 multiplied by Level.SECTION_HEIGHT */
        FOUR (SECTION_HEIGHT * 4),
        /** Specifies the fifth row with y-coordinate of 5 multiplied by Level.SECTION_HEIGHT */
        FIVE (SECTION_HEIGHT * 5),
        /**
         * Specifies the sixth row with y-coordinate of 6 multiplied by Level.SECTION_HEIGHT
         */
        SIX (SECTION_HEIGHT * 6),
        /**
         * Specifies the seventh row with y-coordinate of 7 multiplied by Level.SECTION_HEIGHT
         */
        SEVEN (SECTION_HEIGHT * 7),
        /**
         * Specifies the eighth row with y-coordinate of 8 multiplied by Level.SECTION_HEIGHT
         */
        EIGHT (SECTION_HEIGHT * 8),
        /**
         * Specifies the ninth row with y-coordinate of 9 multiplied by Level.SECTION_HEIGHT
         */
        NINE (SECTION_HEIGHT * 9),
        /**
         * Specifies the tenth row with y-coordinate of 10 multiplied by Level.SECTION_HEIGHT
         */
        TEN (SECTION_HEIGHT * 10),
        /**
         * Specifies the eleventh row with y-coordinate of 11 multiplied by Level.SECTION_HEIGHT
         */
        ELEVEN (SECTION_HEIGHT * 11),
        /**
         * Specifies the twelfth row with y-coordinate of 12 multiplied by Level.SECTION_HEIGHT
         */
        TWELVE (SECTION_HEIGHT * 12),
        /**
         * Specifies the thirteenth row with y-coordinate of 13 multiplied by Level.SECTION_HEIGHT
         */
        THIRTEEN (SECTION_HEIGHT * 13),
        /**
         * Specifies the fourteenth row with y-coordinate of 14 multiplied by Level.SECTION_HEIGHT
         */
        FOURTEEN (SECTION_HEIGHT * 14);

        /**
         * Field used to store the associated y-coordinate.
         */
        private final double y;

        /**
         * Stores the y-coordinate of the enumeration values.
         * @param y Specifies the y-coordinate of the row. Measured in pixels.
         */
        Section(double y) {
            this.y = y;
        }

        /**
         * Obtain the y-coordinate of the current Section value.
         * @return Specifies the y-coordinate of the row. Measured in pixels.
         */
        public double getY() {
            return y;
        }
    }

    /**
     * A reference to the Animal instance. This is the character the player controls. Only one should exists per level.
     */
    private final Animal animal;

    /**
     * An ArrayList of all the end goals in the level. Exactly five must exist per level.
     */
    private final ArrayList<End> ends = new ArrayList<>(5);

    /**
     * An ArrayList storing the digits to be displayed as the score.
     */
    private final ArrayList<Digit> digits = new ArrayList<>();

    /**
     * A reference to the HighscoreController instance that handles all logic regarding player highscores.
     */
    private final HighscoreController highscoreController;

    /**
     * Creates a Level with a HighscoreController. A BackgroundImage is created based on the levelNumber parameter.
     * Images for level backgrounds must be name "background_level[levelNumber].png" in the background
     * images directory. An Animal instance is also created which uses the waterBoundary parameter for it's
     * collision checking. A Level instance listens to a user pressing the ESC key and will call
     * {@link frogger.navigation.NavController#navigateTo(Class)} to navigate to {@link MainMenu}.
     * @param levelNumber Specifies the level number corresponding to the level.
     * @param waterBoundary Specifies the y-coordinate where the water region begins in the level.
     */
    public Level(int levelNumber, double waterBoundary) {
        highscoreController = new HighscoreController(levelNumber);
        highscoreController.addPropertyChangeListener("highscores", this);

        add(new BackgroundImage("background_level" + levelNumber + ".png"));

        for(int i = 0; i < 5; i++) {
            ends.add(new End(10 + 128 * i, 92));
        }
        ends.forEach(this::add);

        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class);
            }
        });

        animal = new Animal(waterBoundary);
        add(animal);
        animal.addPropertyChangeListener("points", this);
        animal.addPropertyChangeListener("endsFilled", this);

        // Generate guiding lines for every row.
        /*
        class Temp extends Actor {
            public Temp(double x, double y) {
                setImage(new Image(MISC_PATH + "line.png", 600, 1, true, true));
                setX(x);
                setY(y);
            }
            @Override
            public void act(long now) {

            }
        }

        for (int i = 0; i < 15; i++) {
            add(new Temp(0, 800 * i /15));
        }
         */


        setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class);
            }
        }));
    }

    /**
     * Empty act method. Called every frame by AnimationTimer.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {

    }

    /**
     * Obtain the instance of Animal in the Level.
     * @return The instance of Animal.
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Changes the score display to match the parameter.
     * @param n Specifies the value to display as the score.
     */
    public void setNumber(int n) {
        digits.forEach(this::remove);
        digits.clear();
        int shift = 0;
        do {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            digits.add(new Digit(k, 540 - shift, 30));
            shift+=30;
        } while (n > 0);
        digits.forEach(this::add);
    }

    /**
     * Resets the level and calls {@link World#start()}. Resets instances of Animal and End. Sets score to display 0.
     */
    @Override
    public void start() {
        animal.initialise();
        setNumber(animal.getPoints());
        ends.forEach(end -> end.setEnd(false));
        highscoreController.loadHighscores();
        super.start();
    }

    /**
     * Stops the timer and calls a dialog popup which either prompts for user input or displays the current highscores.
     * Called when all instances of End have been activated. Called by Java Beans PropertyChangeListener.
     */
    private void win() {
        stop();
        if (highscoreController.isNewHighscore(animal.getPoints())) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("NEW HIGHSCORE!");
            dialog.setHeaderText("Your Score: " + animal.getPoints());
            dialog.setContentText("Name: ");
            Platform.runLater(() -> dialog.showAndWait()
                    .ifPresent(name -> highscoreController.updateHighscores(name, animal.getPoints())));
        }
        else {
            displayHighscores();
        }
    }

    /**
     * Displays the current highscores as an Alert.
     */
    private void displayHighscores() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOU WON!");
        alert.setHeaderText("Your Score: " + animal.getPoints());
        alert.setContentText("Current Highscores\n" + highscoreController.getHighscoresFormattedDisplay());
        alert.setOnHidden(event -> Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class));
        alert.show();
    }

    /**
     * Method from Beans PropertyChangeListener which is called when observed property is modified. Called when
     * a PropertyChangeSupport fires an event.
     * @param evt Specifies the event fired by the PropertyChangeSupport
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        switch(propertyName) {
            case "points" :
                setNumber(animal.getPoints());
                break;
            case "endsFilled":
                if ((int) evt.getNewValue() == 5) {
                    win();
                }
                break;
            case "highscores":
                displayHighscores();
                break;
            default:
        }
    }

}
