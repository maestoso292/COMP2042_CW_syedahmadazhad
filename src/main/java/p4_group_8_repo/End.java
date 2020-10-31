package p4_group_8_repo;

import javafx.scene.image.Image;

import static p4_group_8_repo.Main.RESOURCES_PATH;

public class End extends Actor{
	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}
	
	public End(int x, int y) {
		super(RESOURCES_PATH + "End.png", 60, 60, x, y);
	}
	
	public void setEnd() {
		setImage(new Image(RESOURCES_PATH + "FrogEnd.png", 70, 70, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	

}
