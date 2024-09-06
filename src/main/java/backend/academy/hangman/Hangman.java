package backend.academy.hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Игра Виселица");

        String category = "";
        String difficulty = "";

        while (!WordList.isValidCategory(category)) {
            System.out.println("Выберите категорию (животные/фрукты/города)");
            String categoryInput = console.nextLine().toLowerCase();
            category = categoryInput.isEmpty() ? WordList.chooseRandomCategory(random) : categoryInput;
            if (!WordList.isValidCategory(category)) {
                System.out.println("Неверная категория. Попробуйте снова");
            }
        }

        while (!WordList.isValidDifficulty(difficulty)) {
            System.out.println("Выберите уровень сложности (лёгкий/средний/сложный)");
            String difficultyInput = console.nextLine().toLowerCase();
            difficulty = difficultyInput.isEmpty() ? WordList.chooseRandomDifficulty(random) : difficultyInput;
            if (!WordList.isValidDifficulty(difficulty)) {
                System.out.println("Неверная сложность. Попробуйте снова");
            }
        }

        String word = WordList.chooseRandomWord(random, category, difficulty);
        GameLogic game = new GameLogic(word, MAX_ATTEMPTS);

        while (!game.isGameOver()) {
            game.drawHangman();
            System.out.println("Слово: " + game.getDisplayedWord());
            System.out.print("Введите букву: ");
            while (true) {
                String input = console.nextLine();
                if (input.length() != 1) {
                    System.out.println("Введите одну букву!");
                } else {
                    char guess = input.charAt(0);
                    game.makeGuess(guess);
                    break;
                }
            }
        }

        if (game.isWon()) {
            System.out.println("Поздравляем! Вы угадали слово: " + game.word());
        } else {
            game.drawHangman();
            System.out.println("Вы проиграли! Загаданное слово было: " + game.word());
        }

        console.close();

    }


}
