package frogger.highscore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HighscoreController {
    private List<Highscore> highscores;

    private final int levelNumber;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public HighscoreController(int levelNumber) {
        this.levelNumber = levelNumber;
        new Thread(() -> highscores = HighscoreLoader.readHighscores(levelNumber)).start();
    }

    public void updateHighscores(String name, int score) {
        List<Highscore> oldHighscores = highscores;
        highscores.add(new Highscore(name, score));
        highscores.sort(Collections.reverseOrder());
        highscores = highscores.stream().limit(3).collect(Collectors.toList());
        HighscoreLoader.writeHighscores(levelNumber, highscores);
        propertyChangeSupport.firePropertyChange("highscores", oldHighscores, highscores);
    }

    public boolean isNewHighscore(int score) {
        return highscores.stream().anyMatch(highscore -> highscore.getScore() < score);
    }

    public String getHighscoresFormattedDisplay() {
        String displayText = "";
        for (Highscore highscore : highscores) {
            displayText = displayText.concat(highscore.getName() + ": " + highscore.getScore() + "\n");
        }
        return displayText;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
