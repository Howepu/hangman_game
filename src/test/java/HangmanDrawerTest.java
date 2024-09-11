import backend.academy.hangman.HangmanDrawer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanDrawerTest {

    private int attemptsLeft;

    @Test
    void testDrawHangmanWithMaxAttempts() {
        int attemptsLeft = 6;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testDrawHangmanWithHead() {
        attemptsLeft = 5;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     O\n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testDrawHangmanWithBody() {
        int attemptsLeft = 4;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     O\n" +
                " |     |\n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testDrawHangmanWithLeftArm() {
        int attemptsLeft = 3;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     O\n" +
                " |    /|\n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testDrawHangmanWithBothArms() {
        int attemptsLeft = 2;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     O\n" +
                " |    /|\\\n" +
                " |     \n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testDrawHangmanWithLeftLeg() {
        int attemptsLeft = 1;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     O\n" +
                " |    /|\\\n" +
                " |    /\n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testDrawHangmanWithBothLegs() {
        int attemptsLeft = 0;
        String expected =
            "  ----- \n" +
                " |     |\n" +
                " |     O\n" +
                " |    /|\\\n" +
                " |    / \\\n" +
                " |     \n" +
                " |     \n" +
                "_|__   \n";

        String actual = HangmanDrawer.drawHangman(attemptsLeft);
        assertEquals(expected.trim(), actual.trim());
    }
}
