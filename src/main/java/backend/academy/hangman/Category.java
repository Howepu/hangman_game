package backend.academy.hangman;

public enum Category {
    ANIMALS("животные"),
    FRUITES("фрукты"),
    CITIES("города");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
