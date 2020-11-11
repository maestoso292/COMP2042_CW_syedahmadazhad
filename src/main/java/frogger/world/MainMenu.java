package frogger.world;

import frogger.Navigation;
import frogger.actor.BackgroundImage;
import frogger.world.levels.LevelOne;
import frogger.world.levels.LevelRandom;
import frogger.world.levels.LevelTwo;

import static frogger.Main.MISC_PATH;

public class MainMenu extends World {

    public MainMenu() {
        add(new BackgroundImage(MISC_PATH + "background_main_menu.png"));

        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DIGIT0:
                    Navigation.getNavigationController(getScene()).navigateTo(LevelRandom.class);
                    break;
                case DIGIT1:
                    Navigation.getNavigationController(getScene()).navigateTo(LevelOne.class);
                    break;
                case DIGIT2:
                    Navigation.getNavigationController(getScene()).navigateTo(LevelTwo.class);
                    break;
                default:
            }
        });
    }

    @Override
    public void act(long now) {

    }
}
