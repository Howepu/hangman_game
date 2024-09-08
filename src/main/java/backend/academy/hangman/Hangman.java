package backend.academy.hangman;

import java.util.Random;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SuppressWarnings("uncommentedmain")
public class Hangman {
    private static final int MAX_ATTEMPTS = 6;

    // Частный конструктор для предотвращения создания экземпляров
    private Hangman() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    // Метод для управления игрой
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        log.info("Игра Виселица");

        String category = "";
        String difficulty = "";

        while (!WordList.isValidCategory(category)) {
            log.info("Выберите категорию (животные/фрукты/города)");
            String categoryInput = console.nextLine().toLowerCase();
            category = categoryInput.isEmpty() ? WordList.chooseRandomCategory(random) : categoryInput;
            if (!WordList.isValidCategory(category)) {
                log.info("Неверная категория. Попробуйте снова");
            }
        }

        while (!WordList.isValidDifficulty(difficulty)) {
            log.info("Выберите уровень сложности (лёгкий/средний/сложный)");
            String difficultyInput = console.nextLine().toLowerCase();
            difficulty = difficultyInput.isEmpty() ? WordList.chooseRandomDifficulty(random) : difficultyInput;
            if (!WordList.isValidDifficulty(difficulty)) {
                log.info("Неверная сложность. Попробуйте снова");
            }
        }
        String word = WordList.chooseRandomWord(random, category, difficulty);
        GameLogic game = new GameLogic(word, MAX_ATTEMPTS);

        while (!game.isGameOver()) {
            game.drawHangman();
            log.info("Слово: " + game.getDisplayedChars());
            log.info("Введите букву: ");
            String guess = console.nextLine();
            game.makeGuess(guess);
        }

        if (game.isWon()) {
            log.info("Поздравляем! Вы угадали слово: " + game.word());
        } else {
            game.drawHangman();
            log.info("Вы проиграли! Загаданное слово было: " + game.word());
        }

        console.close();
    }
}
