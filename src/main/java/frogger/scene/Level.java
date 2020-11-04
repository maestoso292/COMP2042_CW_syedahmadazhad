package frogger.scene;

import frogger.actor.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static frogger.Main.MISC_PATH;

public class Level extends World{
    private Animal animal;
    private ArrayList<End> ends;
    private ArrayList<Digit> digits = new ArrayList<>();

    public Level() {
        //Obstacle obstacle = new Obstacle("file:src/p4_group_8_repo/truck1Right.png", 25, 25, 3);
        //Obstacle obstacle1 = new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 100, 100,2 );
        //Obstacle obstacle2 = new Obstacle("file:src/p4_group_8_repo/truck1Right.png",0,  150, 1);

        add(new BackgroundImage(MISC_PATH + "background_level1.png"));

        ends = new ArrayList<>(5);
        ends.add(new End(13,96));
        ends.add(new End(141,96));
        ends.add(new End(141 + 141-13,96));
        ends.add(new End(141 + 141-13+141-13+1,96));
        ends.add(new End(141 + 141-13+141-13+141-13+3,96));
        ends.forEach(new Consumer<End>() {
            @Override
            public void accept(End end) {
                add(end);
            }
        });

        add(new Log(Log.LogTypes.SHORT, 0, 166, 0.75));
        add(new Log(Log.LogTypes.SHORT , 220, 166, 0.75));
        add(new Log(Log.LogTypes.SHORT, 440, 166, 0.75));
        //background.add(new Log("file:src/p4_group_8_repo/log2.png", 150, 0, 166, 0.75));
        add(new Log(Log.LogTypes.LONG, 0, 276, -2));
        add(new Log(Log.LogTypes.LONG, 400, 276, -2));
        //background.add(new Log("file:src/p4_group_8_repo/log0.png", 300, 800, 276, -2));
        add(new Log(Log.LogTypes.SHORT, 50, 329, 0.75));
        add(new Log(Log.LogTypes.SHORT, 270, 329, 0.75));
        add(new Log(Log.LogTypes.SHORT, 490, 329, 0.75));
        //background.add(new Log("file:src/p4_group_8_repo/log2.png", 150, 570, 329, 0.75));

        add(new Turtle(500, 376, -1));
        add(new Turtle(300, 376, -1));
        add(new WetTurtle(700, 376, -1));
        add(new WetTurtle(600, 217, -1));
        add(new WetTurtle(400, 217, -1));
        add(new WetTurtle(200, 217, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 200, 100, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 0, 100, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 100, 120, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 200, 120, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 100, 140, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 200, 140, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 100, 160, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 300, 160, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 100, 180, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 200, 180, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 100, 200, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 200, 200, -1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 100, 220, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 200, 220, 1));
        //background.add(new Log("file:src/p4_group_8_repo/log1.png", 400, 220, 1));
        //End end2 = new End();
        //End end3 = new End();
        //End end4 = new End();
        //End end5 = new End();
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

        //background.add(obstacle);
        //background.add(obstacle1);
        //background.add(obstacle2);

        animal = new Animal();
        add(animal);
    }

    @Override
    public void act(long now) {

    }

    @Override
    public void createTimer() {
        super.createTimer(new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                List<Actor> actors = getObjects(Actor.class);

                for (Actor anActor: actors) {
                    anActor.act(now);
                }

                if (animal.isScoreChanged()) {
                    setNumber(animal.getPoints());
                }
                if (animal.getStop()) {
                    System.out.println("STOP:");
                    Level.this.stop();
                    /*
                    background.stopMusic();
                    sceneController.switchPane("Main");
                    stop();
                    background.stop();
                    */

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("You Have Won The Game!");
                    alert.setHeaderText("Your High Score: " + animal.getPoints() + "!");
                    alert.setContentText("Highest Possible Score: 850");
                    alert.show();
                }
            }
        });
    }

    public void setNumber(int n) {
        digits.forEach(new Consumer<Digit>() {
            @Override
            public void accept(Digit digit) {
                remove(digit);
            }
        });
        digits.clear();
        int shift = 0;
         do {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            digits.add(new Digit(k, 360 - shift, 25));
            shift+=30;
        } while (n > 0);
        digits.forEach(new Consumer<Digit>() {
            @Override
            public void accept(Digit digit) {
                add(digit);
            }
        });
    }

    @Override
    public void start() {
        animal.initialise();
        setNumber(animal.getPoints());
        ends.forEach(new Consumer<End>() {
            @Override
            public void accept(End end) {
                end.reset();
            }
        });
        super.start();
    }
}
