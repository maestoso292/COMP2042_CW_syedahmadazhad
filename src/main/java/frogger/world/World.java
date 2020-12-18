package frogger.world;

import frogger.actor.Actor;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * World is the base abstract class to be extended by all branch nodes in the JavaFX scene graph of the Frogger game.
 * A World object only supports adding children nodes of type Actor to the scene graph.
 * @see Actor
 */
public abstract class World extends Pane {
    /**
     * Stores reference to an {@link AnimationTimer} to be used.
     */
    private AnimationTimer timer;

    /**
     * Instantiates a World object that contains an anonymous ChangeListener which will listen to changes in
     * the {@link Node#sceneProperty()}. The instance contains the uses the following:
     * <ul>
     *     <li>{@link Scene#setOnKeyPressed(EventHandler)}</li>
     *     <li>{@link Scene#setOnKeyReleased(EventHandler)}</li>
     * </ul>
     *
     * If the event occurs, World will call its own EventHandlers if it exists.
     * EventHandlers of all child nodes are also called.
     */
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
				}

			}
		});
    }

    /**
     * Instantiates an {@link AnimationTimer} to store in the private timer field. This AnimationTimer acts as the
     * timer for all child nodes of the instance and calls {@link World#act(long)} and {@link Actor#act(long)}
     * every frame.
     */
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

    /**
     * Call the {@link #createTimer()} method and {@link AnimationTimer#start()}.
     */
    public void start() {
    	createTimer();
        timer.start();
    }

    /**
     * Stops the AnimationTimer using {@link AnimationTimer#stop()}.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Adds an Actor instance as a child node to the scene graph.
     * @param actor The Actor instance to be displayed by the scene graph.
     */
    public void add(Actor actor) {
        getChildren().add(actor);
    }

    /**
     * Removes an Actor instance from the scene graph.
     * @param actor The Actor instance to be removed.
     */
    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    /**
     * A method to get a list of all child nodes attached to the instance (root node), filtered by class.
     * @param cls The class used to filter the list of all child nodes.
     * @param <A> A class that must be a subclass of Actor
     * @return An ArrayList of all child nodes attached to the instance, filtered by class.
     */
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

    /**
     * Abstract method to be implemented by subclasses. Called every frame in {@link AnimationTimer#handle(long)}
     * by the timer instance stored in this instance of World.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    public abstract void act(long now);
}
