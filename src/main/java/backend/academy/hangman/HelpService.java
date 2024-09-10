package backend.academy.hangman;

import java.util.Scanner;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelpService {
    private static final int ATTEMPTS_TO_HELP = 3;
    @Getter private boolean helpUsed = false;
    private boolean helpEnabled; // Новое поле для контроля состояния

    public HelpService(boolean helpEnabled) {
        this.helpEnabled = helpEnabled;
    }

    public void askForHelp(GameLogic gameLogic) {
        if (shouldOfferHelp(gameLogic.attemptsLeft())) {
            log.info("Хотите получить подсказку? (да/нет)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim().toLowerCase();
            if ("да".equals(response)) {
                String help = HelpProvider.getHelp(gameLogic.word());
                log.info("Подсказка: " + help);
                helpUsed = true;
            } else if ("нет".equals(response) || "".equals(response)) {
                log.info("Подсказка не была использована.");
            }
        }
    }

    private boolean shouldOfferHelp(int attemptsLeft) {
        return helpEnabled && attemptsLeft <= ATTEMPTS_TO_HELP && !helpUsed && attemptsLeft != 0;
    }


}

