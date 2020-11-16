package frogger.world.misc;

import frogger.navigation.Navigation;
import frogger.actor.BackgroundImage;
import frogger.world.World;

public class InfoPage extends World {
    public InfoPage() {
        add(new BackgroundImage("background_info_page.png"));
        setOnKeyPressed(event -> Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class));
    }

    @Override
    public void act(long now) {

    }
}
