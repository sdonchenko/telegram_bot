import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MainDev {

    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new LoggingTestBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        System.out.println("LoggingTestBot successfully started!");
    }
}
