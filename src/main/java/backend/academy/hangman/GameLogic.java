package backend.academy.hangman;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameLogic {

    private static final int MAX_ATTEMPTS = 6;
    @Getter private String word;
    private int attemptsLeft;
    private List<Character> guessedLetters;
    private char[] displayedWord;

    public GameLogic(String word, int attemptsLeft) {
        this.word = word;
        this.attemptsLeft = attemptsLeft;
        this.guessedLetters = new ArrayList<>();
        this.displayedWord = new char[word.length()];
        Arrays.fill(displayedWord, '_');
    }

    public void makeGuess(char guess) {

        if (guessedLetters.contains(guess)) {
            System.out.println("Вы уже гадали эту букву. Попробуйте другую.");
            return;
        }

        if (Character.toString(guess).equals(" ")) {
            System.out.print("Введите букву корректно!");
            return;
        }

        guessedLetters.add(guess);

        if (word.contains(String.valueOf(guess))) {
            System.out.println("Правильно!");
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    displayedWord[i] = guess;
                }
            }
        }
        else {
            System.out.println("Неправильно!");
            attemptsLeft--;
        }
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
        switch (attemptsLeft) {
            case 6:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |___");
                break;
            case 5:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |     O");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |___");
                break;
            case 4:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |     O");
                System.out.println(" |     |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |___");
                break;
            case 3:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |     O");
                System.out.println(" |    /|");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |___");
                break;
            case 2:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |     O");
                System.out.println(" |    /|\\");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |___");
                break;
            case 1:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |     O");
                System.out.println(" |    /|\\");
                System.out.println(" |    /");
                System.out.println(" |");
                System.out.println(" |___");
                break;
            case 0:
                System.out.println("  _____");
                System.out.println(" |     |");
                System.out.println(" |     O");
                System.out.println(" |    /|\\");
                System.out.println(" |    / \\");
                System.out.println(" |");
                System.out.println(" |___");
                break;
        }
    }

}


