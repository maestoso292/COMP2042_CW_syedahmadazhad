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
import java.util.stream.Collectors;

import static frogger.Main.MISC_PATH;

public class HighscoreLoader {
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private List<Highscore> highscores;
    private final Path path;

    private boolean upToDate;

    public HighscoreLoader() throws IOException {
        highscores = new ArrayList<>();
        File file = new File(MISC_PATH.replace("file:", "") + "highscores.csv");
        file.createNewFile();
        path = Path.of(file.toURI());
    }

    public void loadHighscores() {
        try (BufferedReader reader = Files.newBufferedReader(path, CHARSET)) {
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

        upToDate = true;
    }

    public boolean isNewHighscore(int score) {
        boolean temp = highscores.stream().anyMatch(highscore -> highscore.getScore() < score);
        upToDate = !temp;
        return temp;
    }

    public void updateHighscores(String name, int score) {
        highscores.add(new Highscore(name, score));
        highscores.sort(Collections.reverseOrder());
        highscores = highscores.stream().limit(3).collect(Collectors.toList());

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

        upToDate = true;
    }

    public String getHighscores() {
        StringBuilder s = new StringBuilder();
        for (Highscore highscore : highscores) {
            s.append(highscore.getName()).append(": ").append(highscore.getScore()).append("\n");
        }
        return s.toString();
    }

    public boolean isUpToDate() {
        return upToDate;
    }
}
