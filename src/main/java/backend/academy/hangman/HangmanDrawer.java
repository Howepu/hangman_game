package backend.academy.hangman;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class HangmanDrawer {
    private static final int MAX_ATTEMPTS = 6;
    private static final int HEAD = 5;
    private static final int BODY = 4;
    private static final int LEFT_ARM = 3;
    private static final int RIGHT_ARM = 2;
    private static final int LEFT_LEG = 1;
    private static final int RIGHT_LEG = 0;

    private static final String HANGMAN_TOP = "  ----- \n";
    private static final String HANGMAN_HEAD = " |     O\n";
    private static final String HANGMAN_BODY = " |     |\n";
    private static final String HANGMAN_LEFT_ARM = " |    /|\n";
    private static final String HANGMAN_LEFT_ARM_FULL = " |    /|\\\n";
    private static final String HANGMAN_LEFT_LEG = " |    /\n";
    private static final String HANGMAN_LEFT_LEG_FULL = " |    / \\\n";
    private static final String HANGMAN_BASE = "_|__   \n";
    private static final String HANGMAN_EMPTY = " |     \n";

    private HangmanDrawer() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }


    public static String drawHangman(int attemptsLeft) {
        StringBuilder hangman = new StringBuilder();

        // Основная часть виселицы
        hangman.append("\n");
        hangman.append(HANGMAN_TOP);
        hangman.append(HANGMAN_BODY);

        switch (attemptsLeft) {
            case MAX_ATTEMPTS:
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            case HEAD:
                hangman.append(HANGMAN_HEAD);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            case BODY:
                hangman.append(HANGMAN_HEAD);
                hangman.append(HANGMAN_BODY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            case LEFT_ARM:
                hangman.append(HANGMAN_HEAD);
                hangman.append(HANGMAN_LEFT_ARM);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            case RIGHT_ARM:
                hangman.append(HANGMAN_HEAD);
                hangman.append(HANGMAN_LEFT_ARM_FULL);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            case LEFT_LEG:
                hangman.append(HANGMAN_HEAD);
                hangman.append(HANGMAN_LEFT_ARM_FULL);
                hangman.append(HANGMAN_LEFT_LEG);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            case RIGHT_LEG:
                hangman.append(HANGMAN_HEAD);
                hangman.append(HANGMAN_LEFT_ARM_FULL);
                hangman.append(HANGMAN_LEFT_LEG_FULL);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
            default:
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_EMPTY);
                hangman.append(HANGMAN_BASE);
                break;
        }

        log.info(hangman.toString());
        return hangman.toString();
    }
}
