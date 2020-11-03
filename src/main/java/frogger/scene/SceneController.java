package frogger.scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SceneController {
    private HashMap<String, Pane> paneMap;
    private Scene scene;

    public SceneController(int numPane, Scene scene) {
        paneMap = new HashMap<>(numPane);
        this.scene = scene;
    }

    public void addPane(String label, Pane pane) {
        paneMap.put(label, pane);
    }

    public void removePane(String label) {
        paneMap.remove(label);
    }

    public void switchPane(String label) {
        scene.setRoot(paneMap.get(label));
    }

    public void startPane() {
        ((World) scene.getRoot()).start();
    }

    public  void stopPane() {
        ((World) scene.getRoot()).stop();
    }
}
