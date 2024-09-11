package backend.academy.hangman;

import java.util.Scanner;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


//Подсказка предлагается после половины оставшихся попыток до того момента пока игрок не согласится
//или не закончит игру
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
            Scanner scanner = new Scanner(System.in);
            String response;

            while (true) {
                log.info("Хотите получить подсказку? (да/нет)");
                response = scanner.nextLine().trim().toLowerCase();

                if ("да".equals(response)) {
                    String help = HelpProvider.getHelp(gameLogic.word());
                    log.info("Подсказка: " + help);
                    helpUsed = true;
                    break;
                } else if ("нет".equals(response) || "".equals(response)) {
                    log.info("Подсказка не была использована.");
                    break;
                } else {
                    log.info("Некорректный ввод. Пожалуйста, введите 'да' или 'нет'/пустую строчку.");
                }
            }
        }
    }

    private boolean shouldOfferHelp(int attemptsLeft) {
        return helpEnabled && attemptsLeft <= ATTEMPTS_TO_HELP && !helpUsed && attemptsLeft != 0;
    }
}
