package frogger.navigation;

import javafx.scene.Scene;

import java.util.HashMap;

/**
 * The Navigation class provides utilities for switching between screens in the Frogger application by using a
 * NavController. The Navigation class supports applications that have multiple scenes by storing separate
 * controllers for each Scene.
 */
public abstract class Navigation {
    /**
     * A static HashMap to store all controllers, each of them attached to a JavaFX scene
     */
    private static final HashMap<Scene, NavController> controllerHashMap = new HashMap<>();

    /**
     * Private constructor to prevent the class from being instantiated.
     */
    private Navigation() {}

    /**
     * Obtain the NavController attached to a Scene. If it doesn't exist, instantiate a new NavController
     * and attach to the specified Scene.
     * @param scene The Scene for which the NavController is attached/will be attached to.
     * @return The NavController that can manipulate the scene graph of the scene parameter.
     */
    public static NavController getNavigationController(Scene scene) {
        if (!controllerHashMap.containsKey(scene)) {
            controllerHashMap.put(scene, new NavController(scene));
        }
        return controllerHashMap.get(scene);
    }
}
