package frogger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import static frogger.Main.MISC_PATH;

public class MusicPlayer {
	MediaPlayer mediaPlayer;
	public MusicPlayer() {
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



