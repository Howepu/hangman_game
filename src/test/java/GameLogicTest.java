import backend.academy.hangman.GameLogic;
import backend.academy.hangman.HelpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameLogicTest {

    private GameLogic game;
    private HelpService helpService;

    @BeforeEach
    public void setUp() {
        game = new GameLogic("медведь", 6);
        helpService = new HelpService(false);
        game.helpService(helpService);

    }

    @Test
    void testGameConditionAfterGuess() {
        game.makeGuess("м");
        assertEquals("м______", game.getDisplayedChars());

        game.makeGuess("д");
        assertEquals(("м_д__д_"), game.getDisplayedChars());
    }

    @Test
    void testCorrectGameConditionAfterCorrectAndIncorrectGuess() {
        game.makeGuess("е");
        assertEquals("_е__е__", game.getDisplayedChars());

        game.makeGuess("и");
        assertEquals("_е__е__", game.getDisplayedChars());

        assertEquals(5, game.attemptsLeft());
    }

    @Test
    void testUpperCases() {
        game.makeGuess("Ь");
        assertEquals(("______ь"), game.getDisplayedChars());
    }

    @Test
    void testGameStateAfterIncorrectGuess() {
        game.makeGuess("б");
        assertEquals("_______", game.getDisplayedChars());
        assertEquals(5, game.attemptsLeft());
    }

    @Test
    void testGameStateAfterEmptyGuess() {
        game.makeGuess(""); // Пустой ввод
        assertEquals("_______", game.getDisplayedChars());
        assertEquals(6, game.attemptsLeft());
    }

    @Test
    void testGameStateAfterNonLetterGuess() {
        game.makeGuess("/");
        assertEquals("_______", game.getDisplayedChars());
        assertEquals(6, game.attemptsLeft());
    }

    @Test
    void testGameStateAfterNonCyrillicGuess() {
        game.makeGuess("b");
        assertEquals("_______", game.getDisplayedChars());
        assertEquals(6, game.attemptsLeft());
    }

    @Test
    void testInvalidMultipleCharacterInput() {
        game.makeGuess("мцйуйцу");
        assertEquals("_______", game.getDisplayedChars());
        assertEquals(6, game.attemptsLeft());
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
    void testGameWon() {
        game.makeGuess("м");
        game.makeGuess("е");
        game.makeGuess("д");
        game.makeGuess("в");
        game.makeGuess("е");
        game.makeGuess("д");
        game.makeGuess("ь");

        assertTrue(game.isGameOver());
        assertTrue(game.isWon());
    }

    @Test
    public void testGameContinuesWithRemainingAttempts() {
        game.makeGuess("л");
        game.makeGuess("б");
        assertFalse(game.isGameOver());
    }
}
