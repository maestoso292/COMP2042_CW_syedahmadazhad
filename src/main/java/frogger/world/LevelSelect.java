package frogger.world;

import frogger.actor.BackgroundImage;
import frogger.actor.NavButton;
import frogger.navigation.Navigation;
import frogger.world.levels.*;
import javafx.scene.input.KeyCode;

/**
 * The LevelSelect class provides a screen for which a user can click preset {@linkplain NavButton NavButtons} to navigate
 * to the corresponding Level destinations using {@link Navigation}. An instance of the class allows the
 * user to return to the MainMenu destination on pressing the ESC key.
 */
public class LevelSelect extends World {
    /**
     * Creates a LevelSelect screen with preset buttons for navigating to Level destinations.
     */
    public LevelSelect() {
        add(new BackgroundImage("background_level_select.png", 600, 800));
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Navigation.getNavController(getScene()).navigateTo(MainMenu.class);
            }
        });

        add(new NavButton("1.png", 400, 100, Level.Section.FOUR.getY(), LevelOne.class));
        add(new NavButton("2.png", 400, 100, Level.Section.FIVE.getY(), LevelTwo.class));
        add(new NavButton("3.png", 400, 100, Level.Section.SIX.getY(), LevelThree.class));
        add(new NavButton("4.png", 400, 100, Level.Section.SEVEN.getY(), LevelFour.class));
        add(new NavButton("5.png", 400, 100, Level.Section.EIGHT.getY(), LevelFive.class));
        add(new NavButton("6.png", 400, 100, Level.Section.NINE.getY(), LevelSix.class));
        add(new NavButton("7.png", 400, 100, Level.Section.TEN.getY(), LevelSeven.class));
        add(new NavButton("8.png", 400, 100, Level.Section.ELEVEN.getY(), LevelEight.class));
        add(new NavButton("9.png", 400, 100, Level.Section.TWELVE.getY(), LevelNine.class));
        add(new NavButton("0.png", 400, 100, Level.Section.THIRTEEN.getY(), LevelRandom.class));
    }

    /**
     * Empty act method.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {

    }
}
