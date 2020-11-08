package frogger.scene;

import frogger.actor.*;
import frogger.highscore.HighscoreLoader;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import static frogger.Main.MISC_PATH;

public class Level extends World implements PropertyChangeListener {
    private static Level uniqueInstance;

    private int score;
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

        /*
        add(new Log(Log.LogTypes.SHORT, 0, 166, 0.75));
        add(new Log(Log.LogTypes.SHORT , 220, 166, 0.75));
        add(new Log(Log.LogTypes.SHORT, 440, 166, 0.75));
        add(new Log(Log.LogTypes.LONG, 0, 276, -2));
        add(new Log(Log.LogTypes.LONG, 400, 276, -2));
        add(new Log(Log.LogTypes.SHORT, 50, 329, 0.75));
        add(new Log(Log.LogTypes.SHORT, 270, 329, 0.75));
        add(new Log(Log.LogTypes.SHORT, 490, (800 * 6 / 15) + 8, 0.75));

        add(new Turtle(500, (800 * 7 / 15) + 1.7, -1));
        add(new Turtle(300, 376, -1));
        add(new WetTurtle(700, 376, -1));
        add(new WetTurtle(600, 217, -1));
        add(new WetTurtle(400, 217, -1));
        add(new WetTurtle(200, 217, -1));

        add(new Car(Car.CarTypes.WHITE, 500, 490, -2));
        add(new Car(Car.CarTypes.WHITE, 300, 490, -2));

        add(new Car(Car.CarTypes.WHITE, 0, 540, 5));

        add(new Truck(Truck.TruckTypes.LONG, 0, 597, 1));
        add(new Truck(Truck.TruckTypes.LONG, 500, 597, 1));

        add(new Car(Car.CarTypes.RED, 100, (800 * 12 / 15) + 9.5, -1));
        add(new Car(Car.CarTypes.RED, 250, 649, -1));
        add(new Car(Car.CarTypes.RED, 400, 649, -1));
        add(new Car(Car.CarTypes.RED, 550, 649, -1));

        add(new Truck(Truck.TruckTypes.SHORT, 0, (800 * 13 / 15) + 7, 1));
        add(new Truck(Truck.TruckTypes.SHORT, 300, 706, 1));
        add(new Truck(Truck.TruckTypes.SHORT, 600, 706, 1));

         */

        animal = Animal.getInstance();
        add(animal);
        animal.addPropertyChangeListener("points", this);
        animal.addPropertyChangeListener("endsFilled", this);

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
    }

    public static Level getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Level();
        }
        return uniqueInstance;
    }

    @Override
    public void act(long now) {
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
        score = animal.getPoints();
        setNumber(score);
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
