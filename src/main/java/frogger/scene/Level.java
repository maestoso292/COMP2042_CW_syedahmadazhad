package frogger.scene;

import frogger.actor.*;
import frogger.highscore.HighscoreLoader;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

import static frogger.Main.MISC_PATH;

public class Level extends World{
    private int score;
    private Animal animal;
    private ArrayList<End> ends = new ArrayList<>(5);
    private ArrayList<Digit> digits = new ArrayList<>();

    private HighscoreLoader highscoreLoader;


    public Level() {
        try {
            highscoreLoader = new HighscoreLoader();
            new Thread(() -> highscoreLoader.loadHighscores()).start();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        add(new BackgroundImage(MISC_PATH + "background_level1.png"));

        ends.add(new End(13,96));
        ends.add(new End(141,96));
        ends.add(new End(141 + 141-13,96));
        ends.add(new End(141 + 141-13+141-13+1,96));
        ends.add(new End(141 + 141-13+141-13+141-13+3,96));
        ends.forEach(this::add);

        add(new Log(Log.LogTypes.SHORT, 0, 166, 0.75));
        add(new Log(Log.LogTypes.SHORT , 220, 166, 0.75));
        add(new Log(Log.LogTypes.SHORT, 440, 166, 0.75));
        add(new Log(Log.LogTypes.LONG, 0, 276, -2));
        add(new Log(Log.LogTypes.LONG, 400, 276, -2));
        add(new Log(Log.LogTypes.SHORT, 50, 329, 0.75));
        add(new Log(Log.LogTypes.SHORT, 270, 329, 0.75));
        add(new Log(Log.LogTypes.SHORT, 490, 329, 0.75));

        add(new Turtle(500, 376, -1));
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

        add(new Car(Car.CarTypes.RED, 100, 649, -1));
        add(new Car(Car.CarTypes.RED, 250, 649, -1));
        add(new Car(Car.CarTypes.RED, 400, 649, -1));
        add(new Car(Car.CarTypes.RED, 550, 649, -1));

        add(new Truck(Truck.TruckTypes.SHORT, 0, 706, 1));
        add(new Truck(Truck.TruckTypes.SHORT, 300, 706, 1));
        add(new Truck(Truck.TruckTypes.SHORT, 600, 706, 1));

        animal = new Animal();
        add(animal);
    }

    @Override
    public void act(long now) {
        if (score != animal.getPoints()) {
            score = animal.getPoints();
            setNumber(animal.getPoints());
        }
        if (animal.getStop()) {
            stop();
        }
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

        if (highscoreLoader.isNewHighscore(animal.getPoints())) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("NEW HIGHSCORE!");
            dialog.setHeaderText("Your Score: " + animal.getPoints());
            dialog.setContentText("Name: ");
            dialog.setOnHidden(event -> displayHighscores());
            Platform.runLater(() -> dialog.showAndWait()
                    .ifPresent(s -> highscoreLoader.updateHighscores(s, animal.getPoints())));
        }
        else {
            displayHighscores();
        }
    }

    private void displayHighscores() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (highscoreLoader.isUpToDate()) {
                    stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("YOU WON!");
                    alert.setHeaderText("Current Highscores");
                    alert.setContentText(highscoreLoader.getHighscores());
                    alert.show();
                    System.out.println(highscoreLoader.getHighscores());
                }
            }
        }.start();
    }
}
