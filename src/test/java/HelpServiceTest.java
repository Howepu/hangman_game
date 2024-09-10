import backend.academy.hangman.GameLogic;
import backend.academy.hangman.HelpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HelpServiceTest {

    private HelpService helpService;
    private GameLogic gameLogic;

    @BeforeEach
    public void setUp() {
        helpService = new HelpService(false);
        gameLogic = Mockito.spy(new GameLogic("кот", 3));
    }

    @Test
    public void testAskForHelpYes() {
        String input = "да\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helpService.askForHelp(gameLogic);

        Assertions.assertTrue(helpService.helpUsed());
        System.setIn(stdin);
    }

    @Test
    public void testAskForHelpNo() {
        String input = "нет\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helpService.askForHelp(gameLogic);

        Assertions.assertFalse(helpService.helpUsed());
        System.setIn(stdin);
    }

    @Test
    public void testAskForHelpNoAttemptsLeft() {
        gameLogic = new GameLogic("кот", 0);
        helpService.askForHelp(gameLogic);

        Assertions.assertFalse(helpService.helpUsed());
    }


}
