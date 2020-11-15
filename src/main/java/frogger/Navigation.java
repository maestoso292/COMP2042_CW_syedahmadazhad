package frogger;

import frogger.world.World;
import javafx.scene.Scene;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Navigation {
    private static HashMap<Scene, Navigation> controllerHashMap = new HashMap<>();
    private HashMap<Class<? extends World>, World> worldHashMap;
    private final Scene scene;

    private Navigation(Scene scene) {
        worldHashMap = new HashMap<>();
        this.scene = scene;
    }

    public static Navigation getNavigationController(Scene scene) {
        if (!controllerHashMap.containsKey(scene)) {
            controllerHashMap.put(scene, new Navigation(scene));
        }
        return controllerHashMap.get(scene);
    }

    public void addDestination(Class<? extends World> cls) {
        if (!worldHashMap.containsKey(cls)) {
            try {
                worldHashMap.put(cls, cls.getConstructor().newInstance());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeDestination(Class<? extends World> cls) {
        worldHashMap.remove(cls);
    }

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

    public void stopCurrentDestinations() {
        ((World) scene.getRoot()).stop();
    }
}
