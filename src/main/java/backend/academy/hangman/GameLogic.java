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

    private static final int ATTEMPTS_TO_HELP = 3;

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
            log.info(invalidGuess ? "Введите букву корректно!" : "Эту букву уже гадали!");
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

    private void askForHelp() {
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
        HangmanDrawer.drawHangman(attemptsLeft);
    }
}
