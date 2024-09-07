package backend.academy.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameLogic.class);
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

    @Getter private String word;
    @Getter private int attemptsLeft;
    private List<Character> guessedLetters;
    private char[] displayedWord;

    public GameLogic(String word, int attemptsLeft) {
        this.word = word;
        this.attemptsLeft = attemptsLeft;
        this.guessedLetters = new ArrayList<>();
        this.displayedWord = new char[word.length()];
        Arrays.fill(displayedWord, '_');
    }

    public void makeGuess(String guess) {
        boolean isValidGuess = true;
        String logMessage = "";

        if (guess.length() != 1) {
            logMessage = "Введите одну букву!";
            isValidGuess = false;
        }

        char letter = isValidGuess ? Character.toLowerCase(guess.charAt(0)) : ' ';

        if (isValidGuess && guessedLetters.contains(letter)) {
            logMessage = "Вы уже гадали эту букву. Попробуйте другую.";
            isValidGuess = false;
        }

        if (isValidGuess && letter == ' ') {
            logMessage = "Введите букву корректно!";
            isValidGuess = false;
        }

        if (isValidGuess) {
            guessedLetters.add(letter);

            if (word.contains(String.valueOf(letter))) {
                logMessage = "Правильно!";
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        displayedWord[i] = letter;
                    }
                }
            } else {
                logMessage = "Неправильно!";
                attemptsLeft--;
            }
        }

        LOGGER.info(logMessage);
    }

    public String getDisplayedWord() {
        return String.valueOf(displayedWord);
    }

    public boolean isGameOver() {
        return attemptsLeft == 0 || Arrays.equals(displayedWord, word.toCharArray());
    }

    public boolean isWon() {
        return Arrays.equals(displayedWord, word.toCharArray());
    }

    public void drawHangman() {
        StringBuilder hangman = new StringBuilder();

        // Основная часть виселицы
        hangman.append("\n");
        hangman.append(HANGMAN_TOP);
        hangman.append(HANGMAN_BODY);

        // Голова, тело, руки и ноги
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

        LOGGER.info(hangman.toString());
    }
}
