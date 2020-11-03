package frogger.scene;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static frogger.Main.MISC_PATH;
import static frogger.Main.RESOURCES_PATH;

public class MusicPlayer{
	javafx.scene.media.MediaPlayer mediaPlayer;
	public MusicPlayer() {
		
//		mediaPlayer.play();
//		mediaPlayer.setOnEndOfMedia(new Runnable() {
//
//			@Override
//			public void run() {
//				mediaPlayer.seek(Duration.ZERO);
//				
//			}
//			
//		});
//		mediaPlayer.play();
	}

	public void playMusic() {
		// Valid URI for Media class is different than Image
		String musicFile = MISC_PATH.replace("file:", "") + "Frogger Main Song Theme (loop).mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new javafx.scene.media.MediaPlayer(sound);
		mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public void stopMusic() {
		mediaPlayer.stop();
	}

}



