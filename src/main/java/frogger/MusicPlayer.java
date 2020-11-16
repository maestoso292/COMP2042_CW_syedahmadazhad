package frogger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The MusicPlayer class is used for loading and playing a sound file indefinitely.
 */
public class MusicPlayer {
	/**
	 * Stores an instance of a JavaFX MediaPlayer
	 */
	private MediaPlayer mediaPlayer;

	/**
	 * Creates a MusicPlayer
	 */
	public MusicPlayer() {
	}

	/**
	 * Loads the media file specified in the url by instantiating a Media instance. This means only url formats
	 * that follow the constraints set by {@link Media#Media(String)} class may be used. A JavaFX MediaPlayer
	 * is instantiated to play the media file indefinitely.
	 * @param url The URL source of the media file.
	 */
	public void playMusic(String url) {
		// Valid URI for Media class is different than Image
		Media sound = new Media(new File(url).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	/**
	 * Stops the indefinite playing of the media file specified in {@link #playMusic(String)}
	 */
	public void stopMusic() {
		mediaPlayer.stop();
	}

}



