package backend.academy.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HangmanTest {

    private GameLogic game;

    @BeforeEach
    void setUp() {
        String word = "медведь";
        game = new GameLogic(word, 6);
    }

    @Test
    void testWordSelection() {
        Random random = new Random();
        String category = "животные";
        String difficulty = "средний";
        String word = WordList.chooseRandomWord(random, category, difficulty);
        assertTrue(word.equals("медведь") || word.equals("кенгуру") || word.equals("собака"));
    }

    @Test
    void testGameConditionAfterGuess() {
        game.makeGuess("м");
        assertEquals("м______", game.getDisplayedWord());

        game.makeGuess("д");
        assertEquals(("м_д__д_"), game.getDisplayedWord());

    }

    @Test
    void testDifferentCases() {
        game.makeGuess("Ь");
        assertEquals(("______ь"), game.getDisplayedWord());
    }

    @Test
    void testGameEnds() {
        game.makeGuess("я");
        game.makeGuess("и");
        game.makeGuess("ж");
        game.makeGuess("г");
        game.makeGuess("с");
        game.makeGuess("ш");

        assertTrue(game.isGameOver());
        assertFalse(game.isWon());
    }

    @Test
    void testCorrectGameConditionAfterCorrectAndIncorrectGuess() {
        game.makeGuess("е");
        assertEquals("_е__е__", game.getDisplayedWord());

        game.makeGuess("и");
        assertEquals("_е__е__", game.getDisplayedWord());

        assertEquals(5, game.attemptsLeft());
    }

    @Test
    public void testInvalidMultipleCharacterInput() {
        game.makeGuess("мцйуйцу");
        assertEquals("_______", game.getDisplayedWord());
        assertEquals(6, game.attemptsLeft());
    }


}
