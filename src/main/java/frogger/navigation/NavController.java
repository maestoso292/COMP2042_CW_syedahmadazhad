package frogger.navigation;

import frogger.world.World;
import javafx.scene.Scene;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * The NavController is a controller class used for manipulating a JavaFX scene graph. The NavController cannot
 * be directly instantiated and is instead provided by {@link Navigation}. The NavController is attached to only one
 * scene, and stores references to all possible destinations (potential root nodes of the scene graph) in a HashMap.
 * The NavController currently only supports destinations that are subclasses of {@link World}.
 *
 * @see Navigation
 * @see World
 */
public class NavController {
    /**
     * A HashMap to store all current destinations (potential root nodes) associated with the current scene.
     */
    private HashMap<Class<? extends World>, World> worldHashMap;
    /**
     * A reference to the Scene that the controller is attached to.
     */
    private final Scene scene;

    /**
     * Creates a NavController that can manipulate a scene graph and attach it to a Scene. Can only be called by the
     * Navigation class. Creates an empty list of destinations stored in the controller.
     * @param scene The Scene to which the controller should attach itself to.
     */
    protected NavController(Scene scene) {
        this.scene = scene;
        worldHashMap = new HashMap<>();
    }

    /**
     * Removes a destination from the list of potential root nodes.
     * @param cls The class of the destination to be removed.
     */
    public void removeDestination(Class<? extends World> cls) {
        worldHashMap.remove(cls);
    }

    /**
     * Changes to root node of the scene graph to the desired node. The previous root node's stop() method is called
     * and the new root node's {@link World#start()} method is called.
     * @param cls The class of the destination to be navigated to.
     */
    public void navigateTo(Class<? extends World> cls) {
        if (worldHashMap.containsKey(cls)) {
            try {
                ((World) scene.getRoot()).stop();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
            scene.setRoot(worldHashMap.get(cls));
            ((World) scene.getRoot()).start();
        }
    }

    /**
     * Adds a destination to the list of potential root nodes. This only adds the destination if it is not part
     * of the current list of destinations.
     * @param cls The class of the destination to be added.
     * @param world The instance of the destination to be added.
     */
    public void addDestination(Class<? extends World> cls, World world) {
        if (!worldHashMap.containsKey(cls)) {
            worldHashMap.put(cls, world);
        }
    }

    /**
     * Adds a destination to the list of potential root nodes by calling the default constructor of the class passed in.
     * This only adds the destination if it is not part of the current list of destinations.
     * @param cls The class of the destination to be added.
     */
    public void addDestination(Class<? extends World> cls) {
        try {
            addDestination(cls, cls.getConstructor().newInstance());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a destination to the list of potential root nodes. This only adds the destination if it is not part of
     * the current list of destinations.
     * @param world The instance of World to added as a destination.
     */
    public void addDestination(World world) {
        addDestination(world.getClass(), world);
    }

    /**
     * Calls the {@link World#stop()} method of the current destination (root node)
     */
    public void stopCurrentDestination() {
        ((World) scene.getRoot()).stop();
    }
}
