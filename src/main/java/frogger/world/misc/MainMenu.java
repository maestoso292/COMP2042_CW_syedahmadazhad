package frogger.world.misc;

import frogger.actor.BackgroundImage;
import frogger.actor.NavButton;
import frogger.world.levels.Level;
import frogger.world.World;
import javafx.application.Platform;

import static frogger.Main.X_UPPER_BOUND;

/**
 * The MainMenu class provides a preset starting screen with {@linkplain NavButton NavButtons} for navigating to the
 * {@link InfoPage} and {@link LevelSelect} destinations.
 */
public class MainMenu extends World {
    /**
     * Creates a MainMenu screen with preset NavButtons.
     */
    public MainMenu() {
        add(new BackgroundImage("background_main_menu.png"));
        add(new NavButton("buttonPlay.png", 100, X_UPPER_BOUND / 2.0 - 100 / 2.0, Level.Section.EIGHT.getY(), LevelSelect.class));
        add(new NavButton("buttonInfo.png", 100, X_UPPER_BOUND / 2.0 - 100 / 2.0, Level.Section.NINE.getY(), InfoPage.class));
        add(new NavButton("buttonExit.png", 100, X_UPPER_BOUND / 2.0 - 100 / 2.0, Level.Section.TEN.getY(), event -> Platform.exit()));
    }

    /**
     * Empty act method.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {

    }
}
