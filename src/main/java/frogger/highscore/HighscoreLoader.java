package frogger.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static frogger.Main.RESOURCES_PATH;

public abstract class HighscoreLoader {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String HIGHSCORES_PATH = RESOURCES_PATH.replace("file:", "") + "highscores/highscores";

    public static List<Highscore> readHighscores(int levelNumber) {
        List<Highscore> highscores = new ArrayList<>();
        File file = new File(HIGHSCORES_PATH + levelNumber + ".csv");
        Path path = Path.of(file.toURI());
        try {
            file.createNewFile();
            BufferedReader reader = Files.newBufferedReader(path, CHARSET);
            String row;
            while ((row = reader.readLine()) != null) {
                String[] temp = row.split(",");
                highscores.add(new Highscore(temp[0], Integer.parseInt(temp[1])));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        while (highscores.size() < 3) {
            highscores.add(new Highscore());
        }

        return highscores;
    }

    public static void writeHighscores(int levelNumber, List<Highscore> highscores) {
        File file = new File(HIGHSCORES_PATH + levelNumber + ".csv");
        Path path = Path.of(file.toURI());
        try (BufferedWriter writer = Files.newBufferedWriter(path, CHARSET)) {
            highscores.forEach(highscore -> {
                try {
                    writer.write(highscore.toString(), 0, highscore.toString().length());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
