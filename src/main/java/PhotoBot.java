import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PhotoBot extends TelegramLongPollingBot {

  @Override
  public void onUpdateReceived(Update update) {
    Long chatId = update.getMessage().getChatId();

    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();

      SendMessage message = new SendMessage().setChatId(chatId).setText(messageText + ".\n Ти класний попас!");
      try {
        execute(message);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
      List<PhotoSize> photos = update.getMessage().getPhoto();
      String fileId = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getFileId();
      int fileWidth = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getWidth();
      int fileHeight = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getHeight();
      String fileCaption = "fileId: " + fileId + "\nwidth: " + fileWidth + "\nheight: " + fileHeight;

      SendPhoto message = new SendPhoto().setChatId(chatId).setPhoto(fileId).setCaption(fileCaption);
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
