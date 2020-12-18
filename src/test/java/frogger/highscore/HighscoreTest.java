package frogger.highscore;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreTest{
    static HighscoreController highscoreController;
    static List<Highscore> originalHighscoresList;
    static List<Highscore> newHighscoresList;
    static final int LEVEL_NUMBER = 1;

    @BeforeAll
    public static void setup() {
        highscoreController = new HighscoreController(LEVEL_NUMBER);
        highscoreController.loadHighscores();
        originalHighscoresList = HighscoreLoader.readHighscores(LEVEL_NUMBER);
    }

    @AfterAll
    public static void reset() {
        HighscoreLoader.writeHighscores(LEVEL_NUMBER, originalHighscoresList);
    }

    @Test
    public void newHighscoreTest() {
        newHighscoresList = new ArrayList<>(originalHighscoresList);
        highscoreController.updateHighscores("AAA", newHighscoresList.get(0).getScore() + 100);
        Highscore newScore = new Highscore("AAA", newHighscoresList.get(0).getScore() + 100);
        newHighscoresList.add(newScore);
        newHighscoresList.sort(Collections.reverseOrder());
        newHighscoresList = newHighscoresList.subList(0, 3);

        List<Highscore> updatedScoresList = HighscoreLoader.readHighscores(LEVEL_NUMBER);
        Assertions.assertEquals(newHighscoresList.size(), updatedScoresList.size());
        for (int i = 0; i < newHighscoresList.size(); i++) {
            Assertions.assertEquals(newHighscoresList.get(i).getName(), updatedScoresList.get(i).getName());
            Assertions.assertEquals(newHighscoresList.get(i).getScore(), updatedScoresList.get(i).getScore());
        }
    }
}
