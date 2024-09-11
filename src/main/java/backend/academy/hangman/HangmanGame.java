package backend.academy.hangman;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("uncommentedmain")
@Slf4j
public class HangmanGame {
    private static final int MAX_ATTEMPTS = 6;

    private HangmanGame() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        log.info("Игра Виселица");

        Category category = null;
        Difficulty difficulty = null;

        while (category == null) {
            log.info("Выберите категорию (животные/фрукты/города) или оставьте пустым для случайного выбора:");
            String categoryInput = console.nextLine().trim().toLowerCase();

            if (categoryInput.isEmpty()) {
                category = WordSelector.chooseRandomCategory(random);
            } else {
                category = Arrays.stream(Category.values())
                    .filter(c -> c.getName().equals(categoryInput))
                    .findFirst()
                    .orElse(null);
            }

            if (category == null) {
                log.info("Неверная категория. Попробуйте снова");
            }
        }

        while (difficulty == null) {
            log.info("Выберите уровень сложности (лёгкий/средний/сложный) или оставьте пустым для случайного выбора:");
            String difficultyInput = console.nextLine().trim().toLowerCase();

            if (difficultyInput.isEmpty()) {
                difficulty = WordSelector.chooseRandomDifficulty(random);
            } else {
                difficulty = Arrays.stream(Difficulty.values())
                    .filter(d -> d.getName().equals(difficultyInput))
                    .findFirst()
                    .orElse(null);
            }

            if (difficulty == null) {
                log.info("Неверная сложность. Попробуйте снова");
            }
        }

        String word = WordSelector.chooseRandomWord(random, category, difficulty);
        GameLogic game = new GameLogic(word, MAX_ATTEMPTS);
        game.drawHangman();

        while (!game.isGameOver()) {
            log.info("Слово: " + game.getDisplayedChars());
            if (game.attemptsLeft() >= MAX_ATTEMPTS - 1) {
                log.info("У вас осталось " + game.attemptsLeft() + " попыток!");
            } else if (game.attemptsLeft() >= 2) {
                log.info("У вас осталось" + " " + game.attemptsLeft() + " попытки!");
            } else if (game.attemptsLeft() == 1) {
                log.info("У вас осталась " + game.attemptsLeft() + " попытка!");
            }
            log.info("Введите букву: ");
            String guess = console.nextLine().trim().toLowerCase();
            game.makeGuess(guess);
            game.drawHangman();
        }

        if (game.isWon()) {
            log.info("Поздравляем! Вы угадали слово: " + game.word());
        } else {
            log.info("Вы проиграли! Загаданное слово было: " + game.word());
        }

        console.close();
    }
}
