package frogger.world;

import frogger.actor.BackgroundImage;
import frogger.navigation.Navigation;
import javafx.scene.input.KeyCode;

/**
 * InfoPage is a class that provides a info page screen for the Frogger game. The class uses a BackgroundImage with
 * pre-written information in the Image. An instance of the class allows the user to return to the MainMenu
 * destination on pressing the ESC key.
 */
public class InfoPage extends World {
    /**
     * Creates an InfoPage with a preset background image that contains all the required information.
     */
    public InfoPage() {
        add(new BackgroundImage("background_info_page.png", 600, 800));
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Navigation.getNavController(getScene()).navigateTo(MainMenu.class);
            }
        });
    }

    /**
     * Empty act method.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {
    }
}
