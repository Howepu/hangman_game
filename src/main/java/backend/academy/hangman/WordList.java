package backend.academy.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// класс для работы со словарём
public class WordList {

    private static final String CATEGORY_ANIMALS = "животные";
    private static final String CATEGORY_FRUITS = "фрукты";
    private static final String CATEGORY_CITIES = "города";

    private static final String DIFFICULTY_EASY = "лёгкий";
    private static final String DIFFICULTY_MEDIUM = "средний";
    private static final String DIFFICULTY_HARD = "сложный";

    private static final String[][] WORDS = {
        {CATEGORY_ANIMALS, DIFFICULTY_EASY, "лось", "лиса", "кот"},
        {CATEGORY_ANIMALS, DIFFICULTY_MEDIUM, "медведь", "кенгуру", "собака"},
        {CATEGORY_ANIMALS, DIFFICULTY_HARD, "выхухоль", "капибара", "ондатра"},
        {CATEGORY_FRUITS, DIFFICULTY_EASY, "яблоко", "груша", "киви"},
        {CATEGORY_FRUITS, DIFFICULTY_MEDIUM, "банан", "авокадо", "нектарин"},
        {CATEGORY_FRUITS, DIFFICULTY_HARD, "алыча", "дуриан", "кешью"},
        {CATEGORY_CITIES, DIFFICULTY_EASY, "омск", "москва", "берлин"},
        {CATEGORY_CITIES, DIFFICULTY_MEDIUM, "хельсинки", "канберра", "бразилиа"},
        {CATEGORY_CITIES, DIFFICULTY_HARD, "антанариву", "секешфехервар", "рейкьявик"},
    };

    // Частный конструктор для предотвращения создания экземпляров
    private WordList() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static String chooseRandomCategory(Random random) {
        List<String> categories = new ArrayList<>(
            Arrays.asList(CATEGORY_ANIMALS, CATEGORY_FRUITS, CATEGORY_CITIES)
        );
        return categories.get(random.nextInt(categories.size()));
    }

    public static String chooseRandomDifficulty(Random random) {
        List<String> difficulties = new ArrayList<>(
            Arrays.asList(DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD)
        );
        return difficulties.get(random.nextInt(difficulties.size()));
    }

    public static boolean isValidCategory(String category) {
        return category.equals(CATEGORY_ANIMALS)
            || category.equals(CATEGORY_FRUITS)
            || category.equals(CATEGORY_CITIES);
    }

    public static boolean isValidDifficulty(String difficulty) {
        return difficulty.equals(DIFFICULTY_EASY)
            || difficulty.equals(DIFFICULTY_MEDIUM)
            || difficulty.equals(DIFFICULTY_HARD);
    }

    public static String chooseRandomWord(Random random, String category, String difficulty) {
        for (String[] data : WORDS) {
            if (data[0].equals(category) && data[1].equals(difficulty)) {
                // data.length - 2 (значит мы берём случайное число в диапозоне от 1 до значения длины строки без учитывания категории и сложности
                // +2 означает, что index будет начиться с 3 слова в ряде, пропуская категорию и сложность
                int wordIndex = random.nextInt(data.length - 2) + 2;
                return data[wordIndex];
            }
        }
        return "";
    }
}
