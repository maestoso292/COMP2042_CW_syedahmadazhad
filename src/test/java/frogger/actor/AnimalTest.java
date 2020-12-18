package frogger.actor;

import frogger.actor.Animal;
import frogger.actor.Platform;
import frogger.world.levels.Level;
import frogger.world.levels.LevelFactory;
import frogger.world.levels.LevelOne;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class AnimalTest{
    Level level;

    final double FROGGER_PADDING = 6.67;
    final double INIT_X_POS = 300;
    final double INIT_Y_POS = Level.Section.FOURTEEN.getY() + FROGGER_PADDING;
    final double MOVEMENT_Y = 13.3333333*2;
    final double MOVEMENT_X = 10.666666*2;

    @Start
    public void start(Stage stage) throws Exception {
        level = new Level(0, 0) {};
        level.getChildren().removeAll();
        Scene scene = new Scene(level, 600, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void animalResetTest(FxRobot robot) {
        Animal animal = new Animal(0);
        robot.interact(()->{
            level.add(animal);
        });
        animal.setX(500);
        animal.setY(200);
        animal.reset();
        FxAssert.verifyThat(animal, animal1 -> animal1.getX() == INIT_X_POS && animal1.getY() == INIT_Y_POS);
        robot.interact(()->{
            level.remove(animal);
        });
    }

    @Test
    public void animalInitialiseTest(FxRobot robot) {
        Animal animal = new Animal(413);
        robot.interact(()->{
            level.add(animal);
        });
        animal.initialise();
        FxAssert.verifyThat(animal, animal1 -> animal1.getPoints() == 0);
        FxAssert.verifyThat(animal, animal1 -> animal1.getEndsFilled() == 0);
        robot.interact(()->{
            level.remove(animal);
        });
    }
    @Test
    public void animalMovementTest(FxRobot robot) throws InterruptedException {
        Animal animal = new Animal(413);
        robot.interact(()->{
            level.add(animal);
        });
        animal.reset();
        robot.press(KeyCode.W).release(KeyCode.W);
        FxAssert.verifyThat(animal, animal1 -> animal1.getRotate() == 0);
        animal.reset();
        robot.press(KeyCode.S).release(KeyCode.S);
        FxAssert.verifyThat(animal, animal1 -> animal1.getRotate() == 180);
        animal.reset();
        robot.press(KeyCode.A).release(KeyCode.A);
        FxAssert.verifyThat(animal, animal1 -> animal1.getRotate() == -90);
        animal.reset();
        robot.press(KeyCode.D).release(KeyCode.D);
        FxAssert.verifyThat(animal, animal1 -> animal1.getRotate() == 90);
        robot.interact(()->{
            level.remove(animal);
        });
    }

}
