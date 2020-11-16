package frogger.world.misc;

import frogger.navigation.Navigation;
import frogger.actor.BackgroundImage;
import frogger.actor.NavButton;
import frogger.world.levels.Level;
import frogger.world.World;
import frogger.world.levels.*;

public class LevelSelect extends World {
    public LevelSelect() {
        add(new BackgroundImage("background_level_select.png"));
        setOnKeyPressed(event -> Navigation.getNavigationController(getScene()).navigateTo(MainMenu.class));

        add(new NavButton("1", 400, 100, Level.Section.FOUR.getY(), LevelOne.class));
        add(new NavButton("2", 400, 100, Level.Section.FIVE.getY(), LevelTwo.class));
        add(new NavButton("3", 400, 100, Level.Section.SIX.getY(), LevelThree.class));
        add(new NavButton("4", 400, 100, Level.Section.SEVEN.getY(), LevelFour.class));
        add(new NavButton("5", 400, 100, Level.Section.EIGHT.getY(), LevelFive.class));
        add(new NavButton("0", 400, 100, Level.Section.NINE.getY(), LevelRandom.class));
    }

    @Override
    public void act(long now) {

    }
}
