package frogger.actor;

import frogger.world.World;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Actor is the base abstract class for all child nodes to be displayed in the scene graph of the Frogger game.
 */
public abstract class Actor extends ImageView{
    /**
     * Moves the instance graphically by the specified amount.
     * @param dx Specifies the change in x-position. Measured in pixels.
     * @param dy Specifies the change in y-position. Measured in pixels.
     */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Get the parent node of this instance.
     * @return Specifies the World object that is the parent of this instance in the scene graph.
     */
    public World getWorld() {
        return (World) getParent();
    }

    /**
     * Get width of the ImageView.
     * @return The width of the displayed node. Measured in pixels.
     */
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    /**
     * Get height of the ImageView.
     * @return The height of the displayed node. Measured in pixels.
     */
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    /**
     * Get a List of all nodes, filtered by class, whose ImageView bounds intersect with the bounds of this instance.
     * @param cls The class by which to filter the List of all intersecting nodes.
     * @param <A> The class that must extend Actor.
     * @return A List of all Actor instances that intersect this instance.
     */
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }

    /**
     * Abstract method to be implemented by subclasses. Called every frame in {@link AnimationTimer#handle(long)}
     * by the timer instance stored in the parent node of this instance.
     * @param now Time in nanoseconds. Passed as argument from AnimationTimer.handle().
     */
    public abstract void act(long now);
}
