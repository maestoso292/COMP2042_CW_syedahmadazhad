package p4_group_8_repo;

import javafx.scene.image.Image;

import static p4_group_8_repo.Main.RESOURCES_PATH;

public class Digit extends Actor{
	int dim;
	Image im1;
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Digit(int n, int dim, int x, int y) {
		super(RESOURCES_PATH + n + ".png", dim, dim, x, y);
	}
	
}
