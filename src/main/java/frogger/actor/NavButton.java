package frogger.actor;

import frogger.navigation.Navigation;
import frogger.world.World;
import javafx.scene.image.Image;

import static frogger.Main.RESOURCES_PATH;

/**
 * The NavButton class provides a displayable clickable button which on click, calls {@link Navigation} to navigate
 * to a specified destination. All buttons must be inside the resources/buttons/ directory.
 */
public class NavButton extends Actor{
    /**
     * Specifies the path to the directory containing the images to be displayed.
     */
    private static final String BUTTON_PATH = RESOURCES_PATH + "buttons/";

    /**
     * Creates a NavButton which when clicked, calls Navigation to navigate to a specified destination.
     * @param filename Specifies the name of the image file to be displayed.
     * @param size Specifies the desired width of the image to be displayed. Measured in pixels.
     * @param xpos Specifies the x-coordinate to display the image. Measured in pixels.
     * @param ypos Specifies the y-coordinate to display the image. Measured in pixels.
     * @param destination Specifies the class of the destination to be navigated to when the instance is clicked.
     */
    public NavButton(String filename, double size, double xpos, double ypos, Class<? extends World> destination) {
        setImage(new Image(BUTTON_PATH + filename, size, size, true, true));
        setX(xpos);
        setY(ypos);
        setOnMouseClicked((event -> Navigation.getNavigationController(getScene()).navigateTo(destination)));
    }

    /**
     * Empty act method.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    @Override
    public void act(long now) {

    }
}
