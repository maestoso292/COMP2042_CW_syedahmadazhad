package frogger.actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import frogger.scene.World;

import java.util.ArrayList;


public abstract class Actor extends ImageView{

    public Actor() {
    }

    public Actor(String imageLink, int width, int height, boolean preserveRatio, boolean smooth) {
        setImage(new Image(imageLink, width, height, preserveRatio, smooth));
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public World getWorld() {
        return (World) getParent();
    }

    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }

    public abstract void act(long now);
}
