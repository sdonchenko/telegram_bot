import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageBot extends TelegramLongPollingBot {

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();
      Long chatId = update.getMessage().getChatId();

      SendMessage message = new SendMessage().setChatId(chatId).setText(messageText + ". Ти класний попас!");
      try {
        execute(message);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String getBotUsername() {
    return "Coadjutor_bot";
  }

  @Override
  public String getBotToken() {
    return "624973617:AAGDvTkS3g6gmI0O0Zb13YQvsVm4W0xyIqA";
  }
}