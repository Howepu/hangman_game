package backend.academy.hangman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogic {

    private static final int MAX_ATTEMPTS = 6;
    private static final int HEAD = 5;
    private static final int BODY = 4;
    private static final int LEFT_ARM = 3;
    private static final int RIGHT_ARM = 2;
    private static final int LEFT_LEG = 1;
    private static final int RIGHT_LEG = 0;

    private static final String HANGMAN_TOP = " ----- \n";
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
    private Set<Character> guessedLetters;
    private List<Character> displayedChars;

    public GameLogic(String word, int attemptsLeft) {
        this.word = word.toLowerCase();
        this.attemptsLeft = attemptsLeft;
        this.guessedLetters = new HashSet<>();
        this.displayedChars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            displayedChars.add('_');
        }
    }

    public void makeGuess(String guess) {


        char letter = guess.toLowerCase().charAt(0);

        if (guess.length() != 1 || guessedLetters.contains(letter) || !Pattern.matches("[а-яА-Я]", guess)) {
            log.info("Введите букву корректно!");
            return;
        }

        guessedLetters.add(letter);

        if (word.contains(String.valueOf(letter))) {
            log.info("Правильно!");
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    displayedChars.set(i, letter);
                }
            }
        } else {
            log.info("Неправильно!");
            attemptsLeft--;
        }
    }


    public String getDisplayedChars() {
        StringBuilder displayedWordBuilder = new StringBuilder();
        for (char c : displayedChars) {
            displayedWordBuilder.append(c);
        }
        return displayedWordBuilder.toString();
    }

    public boolean isGameOver() {
        return attemptsLeft == 0 || displayedChars.equals(new HashSet<>(Collections.singleton(word.toCharArray())));
    }

    public boolean isWon() {
        return displayedChars.equals(new HashSet<>(Collections.singleton(word.toCharArray())));
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

        log.info(hangman.toString());
    }
}
