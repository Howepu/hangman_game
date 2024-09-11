import backend.academy.hangman.Category;
import backend.academy.hangman.Difficulty;
import backend.academy.hangman.WordSelector;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordSelectorTest {

    @Test
    public void testChooseRandomCategory() {
        Category category = WordSelector.chooseRandomCategory(new Random());
        assertNotNull(category);
        assertTrue(Arrays.asList(Category.values()).contains(category));
    }

    @Test
    public void testChooseRandomDifficulty() {
        Difficulty difficulty = WordSelector.chooseRandomDifficulty(new Random());
        assertNotNull(difficulty);
        assertTrue(Arrays.asList(Difficulty.values()).contains(difficulty));
    }

    @Test
    public void testChooseRandomWord() {
        String word = WordSelector.chooseRandomWord(new Random(), Category.ANIMALS, Difficulty.EASY);
        assertNotNull(word);
        assertFalse(word.isEmpty());
    }


}
