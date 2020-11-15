package frogger.world.misc;

import frogger.Navigation;
import frogger.actor.BackgroundImage;
import frogger.world.World;

import static frogger.Main.MISC_PATH;

public class InfoPage extends World {
    public InfoPage() {
        add(new BackgroundImage("background_info_page.png"));
        setOnKeyPressed(event -> Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class));
    }

    @Override
    public void act(long now) {

    }
}
