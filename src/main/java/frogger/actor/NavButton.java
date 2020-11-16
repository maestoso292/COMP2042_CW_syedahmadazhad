package frogger.actor;

import frogger.navigation.Navigation;
import frogger.world.World;
import javafx.scene.image.Image;

import static frogger.Main.RESOURCES_PATH;

public class NavButton extends Actor{
    private static final String BUTTON_PATH = RESOURCES_PATH + "buttons/";

    public NavButton(String label, double size, double xpos, double ypos, Class<? extends World> destination) {
        setImage(new Image(BUTTON_PATH + label + ".png", size, size, true, true));
        setX(xpos);
        setY(ypos);
        setOnMouseClicked((event -> Navigation.getNavigationController(getScene()).navigateTo(destination)));
    }

    @Override
    public void act(long now) {

    }
}
