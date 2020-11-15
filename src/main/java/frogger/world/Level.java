package frogger.world;

import frogger.Navigation;
import frogger.actor.*;
import frogger.highscore.HighscoreController;
import frogger.world.levels.*;
import frogger.world.misc.MainMenu;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public abstract class Level extends World implements PropertyChangeListener {
    public static final double SECTION_HEIGHT = 800.0 / 15;

    public enum LevelType {
        RANDOM (0, LevelRandom.class, Section.EIGHT.getY()),
        ONE (1, LevelOne.class, Section.EIGHT.getY()),
        TWO (2, LevelTwo.class, Section.NINE.getY()),
        THREE (3, LevelThree.class, Section.SIX.getY()),
        FOUR (4, LevelFour.class, Section.FOUR.getY()),
        FIVE (5, LevelFive.class, Section.FOURTEEN.getY());
        public final int number;
        public final Class<? extends Level> cls;
        public final double waterBoundary;

        LevelType(int number, Class<? extends Level> cls, double waterBoundary) {
            this.number = number;
            this.cls = cls;
            this.waterBoundary = waterBoundary;
        }
    }

    public enum Section {
        ZERO (0),
        ONE (SECTION_HEIGHT),
        TWO (SECTION_HEIGHT * 2),
        THREE (SECTION_HEIGHT * 3),
        FOUR (SECTION_HEIGHT * 4),
        FIVE (SECTION_HEIGHT * 5),
        SIX (SECTION_HEIGHT * 6),
        SEVEN (SECTION_HEIGHT * 7),
        EIGHT (SECTION_HEIGHT * 8),
        NINE (SECTION_HEIGHT * 9),
        TEN (SECTION_HEIGHT * 10),
        ELEVEN (SECTION_HEIGHT * 11),
        TWELVE (SECTION_HEIGHT * 12),
        THIRTEEN (SECTION_HEIGHT * 13),
        FOURTEEN (SECTION_HEIGHT * 14);

        private final double y;
        Section(double y) {
            this.y = y;
        }

        public double getY() {
            return y;
        }
    }

    private Animal animal;

    private ArrayList<End> ends = new ArrayList<>(5);
    private ArrayList<Digit> digits = new ArrayList<>();
    private HighscoreController highscoreController;
    private LevelType levelType;

    public Level(LevelType levelType) {
        this.levelType = levelType;

        add(new BackgroundImage("background_level" + levelType.number + ".png"));

        for(int i = 0; i < 5; i++) {
            ends.add(new End(10 + 128 * i, 92));
        }
        ends.forEach(this::add);

        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class);
                    break;
                default:
            }
        });

        animal = new Animal(levelType.waterBoundary);
        add(animal);
        animal.addPropertyChangeListener("points", this);
        animal.addPropertyChangeListener("endsFilled", this);

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

    @Override
    public void act(long now) {

    }

    public Animal getAnimal() {
        return animal;
    }

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

    @Override
    public void start() {
        animal.initialise();
        setNumber(animal.getPoints());
        ends.forEach(End::reset);
        highscoreController = new HighscoreController(levelType.number);
        highscoreController.addPropertyChangeListener("highscores", this);
        super.start();
    }

    public void win() {
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

    private void displayHighscores() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOU WON!");
        alert.setHeaderText("Your Score: " + animal.getPoints());
        alert.setContentText("Current Highscores\n" + highscoreController.getHighscoresFormattedDisplay());
        alert.setOnHidden(event -> Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class));
        alert.show();
    }

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
