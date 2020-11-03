package frogger.actor;

public class BackgroundImage extends Actor{

	@Override
	public void act(long now) {
		
		
	}
	
	public BackgroundImage(String imageLink) {
		super(imageLink, 600, 800, false, true);
	}

}
