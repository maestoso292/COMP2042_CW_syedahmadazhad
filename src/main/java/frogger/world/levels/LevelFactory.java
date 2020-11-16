package frogger.world.levels;

public abstract class LevelFactory {
    private LevelFactory(){}

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
            default:
                return null;
        }
    }
}
