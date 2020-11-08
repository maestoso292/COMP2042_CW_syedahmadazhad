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

public abstract class HighscoreLoader {
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private static List<Highscore> highscores = new ArrayList<>();
    private static File file = new File(MISC_PATH.replace("file:", "") + "highscores.csv");
    private static Path path = Path.of(file.toURI());

    private static boolean upToDate;

    public static void loadHighscores() throws IOException {
        file.createNewFile();
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

    public static boolean isNewHighscore(int score) {
        boolean temp = highscores.stream().anyMatch(highscore -> highscore.getScore() < score);
        upToDate = !temp;
        return temp;
    }

    public static void updateHighscores(String name, int score) {
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

    public static String getHighscores() {
        StringBuilder s = new StringBuilder();
        for (Highscore highscore : highscores) {
            s.append(highscore.getName()).append(": ").append(highscore.getScore()).append("\n");
        }
        return s.toString();
    }

    public static boolean isUpToDate() {
        return upToDate;
    }
}
