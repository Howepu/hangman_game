package backend.academy.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


//класс для работы со словарём
public class WordList {

    private static final String[][] WORDS = {
        {"животные", "лёгкий", "лось", "лиса", "кот"},
        {"животные", "средний", "медведь", "кенгуру", "собака"},
        {"животные", "сложный", "выхухоль", "капибара", "ондатра"},
        {"фрукты", "лёгкий", "яблоко", "груша", "киви"},
        {"фрукты", "средний", "банан", "авокадо", "нектарин"},
        {"фрукты", "сложный", "алыча", "дуриан", "кешью"},
        {"города", "лёгкий", "омск", "москва", "берлин"},
        {"города", "средний", "хельсинки", "канберра", "бразилиа"},
        {"города", "сложный", "антанариву", "секешфехервар", "рейкьявик"},
    };

    public static String chooseRandomCategory(Random random) {
        List<String> categories = new ArrayList<>(Arrays.asList("животные", "фрукты", "города"));
        return categories.get(random.nextInt(categories.size()));
    }

    public static String chooseRandomDifficulty(Random random) {
        List<String> difficulties = new ArrayList<>(Arrays.asList("лёгкий", "средний", "сложный"));
        return difficulties.get(random.nextInt(difficulties.size()));
    }

    public static boolean isValidCategory(String category) {
        return category.equals("животные") || category.equals("фрукты") || category.equals("города");
    }

    public static boolean isValidDifficulty(String category) {
        return category.equals("лёгкий") || category.equals("средний") || category.equals("сложный");
    }

    public static String chooseRandomWord(Random random, String category, String difficulty) {
        for (String[] data : WORDS) {
            if (data[0].equals(category) && data[1].equals(difficulty)) {
                return data[random.nextInt(data.length - 2) + 2];
            }
        }
        return "";
    }

}
