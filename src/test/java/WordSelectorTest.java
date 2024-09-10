import backend.academy.hangman.Category;
import backend.academy.hangman.Difficulty;
import backend.academy.hangman.WordSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

public class WordSelectorTest {

    @Test
    public void testChooseRandomCategory() {
        Category category = WordSelector.chooseRandomCategory(new Random());
        Assertions.assertNotNull(category);
        Assertions.assertTrue(Arrays.asList(Category.values()).contains(category));
    }

    @Test
    public void testChooseRandomDifficulty() {
        Difficulty difficulty = WordSelector.chooseRandomDifficulty(new Random());
        Assertions.assertNotNull(difficulty);
        Assertions.assertTrue(Arrays.asList(Difficulty.values()).contains(difficulty));
    }

    @Test
    public void testChooseRandomWord() {
        String word = WordSelector.chooseRandomWord(new Random(), Category.ANIMALS, Difficulty.EASY);
        Assertions.assertNotNull(word);
        Assertions.assertFalse(word.isEmpty());
    }
}
