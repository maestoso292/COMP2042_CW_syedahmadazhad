package frogger.world.levels;

import java.lang.reflect.InvocationTargetException;

/**
 * LevelFactory is an abstract class solely used for returning instances of subclasses of Level. LevelFactory handles
 * all the parameters for instancing any levels so that the Main class does not need do it.
 */
public abstract class LevelFactory {
    /**
     * Obtain a new instance of the Level subclass passed in as a parameter.
     * @param cls The subclass of Level to be instanced.
     * @return A preset level for the game which is an instance of the cls parameter.
     */
    public static Level provideLevel(Class<? extends Level> cls) {
        switch (cls.getSimpleName()) {
            case "LevelRandom":
                return new LevelRandom(0, Level.Section.EIGHT.getY());
            case "LevelOne":
                return new LevelOne(1, Level.Section.EIGHT.getY());
            case "LevelTwo":
                return new LevelTwo(2, Level.Section.NINE.getY());
            case "LevelThree":
                return new LevelThree(3, Level.Section.SIX.getY());
            case "LevelFour":
                return new LevelFour(4, Level.Section.FOUR.getY());
            case "LevelFive":
                return new LevelFive(5, Level.Section.FOURTEEN.getY());
            case "LevelSix":
                return new LevelSix(6, Level.Section.THREE.getY());
            case "LevelSeven":
                return new LevelSeven(7, Level.Section.FOURTEEN.getY());
            case "LevelEight":
                return new LevelEight(8, Level.Section.SIX.getY());
            case "LevelNine":
                return new LevelNine(9, Level.Section.FOURTEEN.getY());
            default:
                return null;
        }
    }

    /**
     * Obtain a new instance of the Level subclass with the specified level number and water boundary.
     * @param cls The subclass of Level to be instanced.
     * @param levelNumber Specifies the level number.
     * @param waterBoundary Specifies the y-coordinate where the water region begins. Measured in pixels.
     * @return A level for the game which is an instance of the cls parameter
     */
    public Level provideLevel(Class<? extends Level> cls, int levelNumber, double waterBoundary) {
        try {
            return cls.getConstructor(int.class, double.class).newInstance(levelNumber, waterBoundary);
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
