package frogger.highscore;

import frogger.world.levels.Level;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The HighscoreController class provides utilities for determining a whether a new score is a new highscore, and
 * uses a HighscoreLoader for reading and writing a list of Highscores to a file.
 */
public class HighscoreController {
    /**
     * A list storing the current highscores.
     */
    private List<Highscore> highscores;

    /**
     * Specifies the level number of the Level the HighscoreController is instanced in.
     */
    private final int levelNumber;

    /**
     * Specifies a PropertyChangeSupport object for firing events related to property changes.
     */
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Creates a HighscoreController.
     * @param levelNumber Specifies the level number of the Level the instance is created in and determines which file
     *                    the instance reads from and writes to.
     */
    public HighscoreController(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Creates a new Thread which calls the HighscoreLoader to read a file and stores the scores in a list.
     */
    public void loadHighscores() {
        new Thread(() -> highscores = HighscoreLoader.readHighscores(levelNumber)).start();
    }

    /**
     * Updates the current list of Highscores by inserting a new Highscore object and writes the updated list to a
     * file. The list is sorted in descending order and only the top 3 Highscores will remain. Listeners of the list
     * of highscores are updated that the list has changed.
     * @param name Specifies the name of the person who achieved the new highscore.
     * @param score Specifies the score value of the new highscore.
     */
    public void updateHighscores(String name, int score) {
        List<Highscore> oldHighscores = highscores;
        highscores.add(new Highscore(name, score));
        highscores.sort(Collections.reverseOrder());
        highscores = highscores.stream().limit(3).collect(Collectors.toList());
        HighscoreLoader.writeHighscores(levelNumber, highscores);
        propertyChangeSupport.firePropertyChange("highscores", oldHighscores, highscores);
    }

    /**
     * Checks whether a score value is greater than any score values stored in the current list of Highscores.
     * @param score The potential new highscore value.
     * @return <ul>
     *     <li>true - if score argument is a new highscore</li>
     *     <li>false - if score argument is not a new highscore</li>
     * </ul>
     */
    public boolean isNewHighscore(int score) {
        return highscores.stream().anyMatch(highscore -> highscore.getScore() < score);
    }

    /**
     * Returns a String representation of the Highscores list to display
     * @return A String object representing the current Highscores in the list formatted in the following way:
     * "name : score"
     */
    public String getHighscoresFormattedDisplay() {
        String displayText = "";
        for (Highscore highscore : highscores) {
            displayText = displayText.concat(highscore.getName() + " : " + highscore.getScore() + "\n");
        }
        return displayText;
    }

    /**
     * Adds a Listener to a list of PropertyChagneListeners.
     * @param propertyName A String object representing the name of the property that the Listener is listening to.
     * @param listener The Listener object.
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Removes a Listener from the list of PropertyChangeListeners.
     * @param listener The Listener object.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
