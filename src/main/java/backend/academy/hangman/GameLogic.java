package backend.academy.hangman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogic {

    private static final int MAX_ATTEMPTS = 6;
    private  static final int ATTEMPTS_TO_HELP = 3;
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
    @Getter private Set<String> guessedLetters;
    private List<Character> displayedChars;
    private boolean helpUsed;

    public GameLogic(String word, int attemptsLeft) {
        this.word = word.toLowerCase();
        this.attemptsLeft = attemptsLeft;
        this.guessedLetters = new HashSet<>();
        this.displayedChars = new ArrayList<>();
        this.helpUsed = false;
        for (char c : word.toCharArray()) {
            displayedChars.add('_');
        }
    }

    public void makeGuess(String guess) {

        String normalizedGuess = guess.toLowerCase();
        boolean invalidGuess = !isValidGuess(normalizedGuess);
        boolean alreadyGuessed = guessedLetters.contains(normalizedGuess);

        if (invalidGuess || alreadyGuessed) {
            if (invalidGuess) {
                log.info("Введите букву корректно!");
            } else {
                log.info("Эту букву уже гадали!");
            }
            return;
        }

        guessedLetters.add(normalizedGuess);

        if (word.contains(normalizedGuess)) {
            log.info("Правильно!");
            updateDisplayedChars(normalizedGuess.charAt(0));
        } else {
            log.info("Неправильно!");
            attemptsLeft--;
        }

        askForHelp();

    }


    private boolean isValidGuess(String guess) {
        return !(guess.isEmpty() || guess.length() != 1 || !Pattern.matches("[а-яА-Я]", guess));
    }

    private void updateDisplayedChars(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                displayedChars.set(i, letter);
            }
        }
    }

    private boolean shouldOfferHelp() {
        return attemptsLeft <= ATTEMPTS_TO_HELP && !helpUsed && attemptsLeft != 0;
    }



    public void askForHelp() {
        if (shouldOfferHelp()) {
            log.info("Хотите получить подсказку? (да/нет)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim().toLowerCase();
            if ("да".equals(response)) {
                String hint = HelpProvider.getHelp(word);
                log.info("Подсказка: " + hint);
                helpUsed = true;
            } else if ("нет".equals(response) || "".equals(response)) {
                log.info("Подсказка не была использована.");
            }
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
        return attemptsLeft == 0 || !displayedChars.contains('_');
    }

    public boolean isWon() {
        return !displayedChars.contains('_');
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
