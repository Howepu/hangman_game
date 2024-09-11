import backend.academy.hangman.GameLogic;
import backend.academy.hangman.HelpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpServiceTest {

    private HelpService helpService;
    private GameLogic game;

    @BeforeEach
    public void setUp() {
        helpService = new HelpService(true);
        game = Mockito.spy(new GameLogic("кот", 3));
        game.helpService(helpService);
    }

    @Test
    void testAskForHelpYes() {
        String input = "да\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helpService.askForHelp(game);

        assertTrue(helpService.helpUsed());
        System.setIn(stdin);
    }

    @Test
    void testAskForHelpNo() {
        String input = "нет\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helpService.askForHelp(game);

        assertFalse(helpService.helpUsed());
        System.setIn(stdin);
    }

    @Test
    void testAskForHelpNoAttemptsLeft() {
        game = new GameLogic("кот", 0);
        helpService.askForHelp(game);

        assertFalse(helpService.helpUsed());
    }


}
