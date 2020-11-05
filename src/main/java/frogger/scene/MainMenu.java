package frogger.scene;

import frogger.actor.BackgroundImage;
import javafx.scene.input.KeyCode;

import static frogger.Main.MISC_PATH;

public class MainMenu extends World {

    public MainMenu() {
        add(new BackgroundImage(MISC_PATH + "background_main_menu.png"));

        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                stop();
            }
        });
    }

    @Override
    public void act(long now) {

    }
}
