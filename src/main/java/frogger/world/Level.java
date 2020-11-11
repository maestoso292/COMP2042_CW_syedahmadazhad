package frogger.world;

import frogger.Navigation;
import frogger.actor.Animal;
import frogger.actor.BackgroundImage;
import frogger.actor.Digit;
import frogger.actor.End;
import frogger.highscore.HighscoreLoader;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import static frogger.Main.MISC_PATH;

public abstract class Level extends World implements PropertyChangeListener {
    public static final double SECTION_HEIGHT = 800.0 / 15;

    public enum ActorType {
        LOG (8),
        TURTLE (1.7),
        WET_TURTLE (1.7),
        CAR (9.5),
        TRUCK (7),
        FROGGER (6.67);
        // How padding is calculated: Padding = (Section height - Ingame image height) / 2
        private final double padding;
        ActorType(double padding) {
            this.padding = padding;
        }

        public double getPadding() {
            return padding;
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

        private double y;
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

    public Level() {
        new Thread(() -> {
            try {
                HighscoreLoader.loadHighscores();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        add(new BackgroundImage(MISC_PATH + "background_level1.png"));

        ends.add(new End(10,92));
        ends.add(new End(141,96));
        ends.add(new End(141 + 141-13,96));
        ends.add(new End(141 + 141-13+141-13+1,96));
        ends.add(new End(141 + 141-13+141-13+141-13+3,96));
        ends.forEach(this::add);

        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class);
                    break;
                default:
            }
        });
    }

    @Override
    public void act(long now) {

    }

    public void addAnimal() {
        animal = Animal.getInstance();
        add(animal);
        animal.addPropertyChangeListener("points", this);
        animal.addPropertyChangeListener("endsFilled", this);
    }

    public void setNumber(int n) {
        digits.forEach(this::remove);
        digits.clear();
        int shift = 0;
        do {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            digits.add(new Digit(k, 360 - shift, 25));
            shift+=30;
        } while (n > 0);
        digits.forEach(this::add);
    }

    @Override
    public void start() {
        animal.initialise();
        setNumber(animal.getPoints());
        ends.forEach(End::reset);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();

        if (HighscoreLoader.isNewHighscore(animal.getPoints())) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("NEW HIGHSCORE!");
            dialog.setHeaderText("Your Score: " + animal.getPoints());
            dialog.setContentText("Name: ");
            dialog.setOnHidden(event -> displayHighscores());
            Platform.runLater(() -> dialog.showAndWait()
                    .ifPresent(s -> HighscoreLoader.updateHighscores(s, animal.getPoints())));
        }
        else {
            displayHighscores();
        }
    }

    private void displayHighscores() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (HighscoreLoader.isUpToDate()) {
                    stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("YOU WON!");
                    alert.setHeaderText("Your Score: " + animal.getPoints());
                    alert.setContentText("Current Highscores\n" + HighscoreLoader.getHighscores());
                    alert.show();
                    System.out.println(HighscoreLoader.getHighscores());
                    Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class);
                }
            }
        }.start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals("points")) {
            setNumber(animal.getPoints());
        }
        else if (propertyName.equals("endsFilled")) {
            stop();
        }
    }

}
