package frogger.scene;


import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import frogger.actor.Actor;


public abstract class World extends Pane {
    private AnimationTimer timer;
    private boolean isRunning;
    
    public World() {
    	
    	sceneProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					newValue.setOnKeyReleased(event -> {
                        if (getOnKeyReleased() != null)
                            getOnKeyReleased().handle(event);
                        List<Actor> myActors = getObjects(Actor.class);
                        for (Actor anActor : myActors) {
                            if (anActor.getOnKeyReleased() != null) {
                                anActor.getOnKeyReleased().handle(event);
                            }
                        }
                    });

					newValue.setOnKeyPressed(event -> {
                        if (getOnKeyPressed() != null)
                            getOnKeyPressed().handle(event);
                        List<Actor> myActors = getObjects(Actor.class);
                        for (Actor anActor : myActors) {
                            if (anActor.getOnKeyPressed() != null) {
                                anActor.getOnKeyPressed().handle(event);
                            }
                        }
                    });

					newValue.setOnMouseClicked(event -> {
                        if (getOnMouseClicked() != null) {
                            getOnMouseClicked().handle(event);
                        }
                        List<Actor> myActors = getObjects(Actor.class);
                        for (Actor anActor : myActors) {
                            if (anActor.getOnMouseClicked() != null) {
                                anActor.getOnMouseClicked().handle(event);
                            }
                        }
                    });
				}

			}

		});
    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                List<Actor> actors = getObjects(Actor.class);
                
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
      
            }
        };
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start() {
        isRunning = true;
    	createTimer();
        timer.start();
    }

    public void stop() {
        isRunning = false;
        timer.stop();
    }
    
    public void add(Actor actor) {
        getChildren().add(actor);
    }

    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    @SuppressWarnings("unchecked")
    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }

    public abstract void act(long now);
}
