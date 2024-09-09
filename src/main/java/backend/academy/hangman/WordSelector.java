package backend.academy.hangman;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordSelector {

    private WordSelector() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }


    public static Category chooseRandomCategory(Random random) {
        List<Category> categories = Arrays.asList(Category.values());
        return categories.get(random.nextInt(categories.size()));
    }

    public static Difficulty chooseRandomDifficulty(Random random) {
        List<Difficulty> difficulties = Arrays.asList(Difficulty.values());
        return difficulties.get(random.nextInt(difficulties.size()));
    }

    public static String chooseRandomWord(Random random, Category category, Difficulty difficulty) {
        List<String> words = WordList.getWords().getOrDefault(category, Collections.emptyMap())
            .getOrDefault(difficulty, Collections.emptyList());
        if (words.isEmpty()) {
            return "";
        }
        return words.get(random.nextInt(words.size()));
    }
}
