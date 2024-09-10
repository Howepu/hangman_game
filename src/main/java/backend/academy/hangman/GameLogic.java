package backend.academy.hangman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogic {

    @Getter private String word;
    @Getter private int attemptsLeft;
    @Getter private Set<String> guessedLetters;
    private List<Character> displayedChars;
    @Setter HelpService helpService;


    public GameLogic(String word, int attemptsLeft) {
        this.word = word.toLowerCase();
        this.attemptsLeft = attemptsLeft;
        this.guessedLetters = new HashSet<>();
        this.displayedChars = new ArrayList<>();
        this.helpService = new HelpService(true);
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

        helpService.askForHelp(this);
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
