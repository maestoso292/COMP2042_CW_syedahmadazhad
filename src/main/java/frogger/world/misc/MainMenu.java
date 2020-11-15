package frogger.world.misc;

import frogger.actor.BackgroundImage;
import frogger.actor.NavButton;
import frogger.world.Level;
import frogger.world.World;

import static frogger.Main.X_UPPER_BOUND;

public class MainMenu extends World {

    public MainMenu() {
        add(new BackgroundImage("background_main_menu.png"));
        add(new NavButton("buttonPlay", 100, X_UPPER_BOUND / 2 - 100 / 2, Level.Section.EIGHT.getY(), LevelSelect.class));
        add(new NavButton("buttonInfo", 100, X_UPPER_BOUND / 2 - 100 / 2, Level.Section.NINE.getY(), InfoPage.class));
    }

    @Override
    public void act(long now) {

    }
}
