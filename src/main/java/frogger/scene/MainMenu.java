package frogger.scene;

import frogger.actor.BackgroundImage;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static frogger.Main.MISC_PATH;

public class MainMenu extends World {

    public MainMenu() {
        add(new BackgroundImage(MISC_PATH + "background_main_menu.png"));

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    stop();
                    // MainMenu.this.getScene().setRoot(new Level());
                }
            }
        });
    }

    @Override
    public void act(long now) {

    }
}
