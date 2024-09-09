package backend.academy.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class WordList {

    private static final Map<Category, Map<Difficulty, List<String>>> WORDS = new HashMap<>();

    static {
        // Инициализация словаря
        for (Category category : Category.values()) {
            Map<Difficulty, List<String>> difficultyMap = new HashMap<>();
            for (Difficulty difficulty : Difficulty.values()) {
                difficultyMap.put(difficulty, new ArrayList<>());
            }
            WORDS.put(category, difficultyMap);
        }

        // Заполнение словаря
        addWords(Category.ANIMALS, Difficulty.EASY, Arrays.asList("лось", "лиса", "кот"));
        addWords(Category.ANIMALS, Difficulty.MEDIUM, Arrays.asList("медведь", "кенгуру", "собака"));
        addWords(Category.ANIMALS, Difficulty.HARD, Arrays.asList("выхухоль", "капибара", "ондатра"));
        addWords(Category.FRUITES, Difficulty.EASY, Arrays.asList("яблоко", "груша", "киви"));
        addWords(Category.FRUITES, Difficulty.MEDIUM, Arrays.asList("банан", "авокадо", "нектарин"));
        addWords(Category.FRUITES, Difficulty.HARD, Arrays.asList("алыча", "дуриан", "кешью"));
        addWords(Category.CITIES, Difficulty.EASY, Arrays.asList("омск", "москва", "берлин"));
        addWords(Category.CITIES, Difficulty.MEDIUM, Arrays.asList("хельсинки", "канберра", "бразилиа"));
        addWords(Category.CITIES, Difficulty.HARD, Arrays.asList("антанариву", "секешфехервар", "рейкьявик"));
    }

    private WordList() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    private static void addWords(Category category, Difficulty difficulty, List<String> words) {
        WORDS.get(category).put(difficulty, words);
    }

    public static Map<Category, Map<Difficulty, List<String>>> getWords() {
        return WORDS;
    }
}
