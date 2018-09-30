import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PhotoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

/*        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage().setChatId(chatId).setText(messageText + ".\n Ти класний попас!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else*/
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
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
        } else if (messageText.equals("/start")) {
            SendMessage message = new SendMessage().setChatId(chatId).setText(messageText);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (messageText.equals("/pic")) {
            SendPhoto message = new SendPhoto().setChatId(chatId).setPhoto("AgADAgADPKkxG7LTiUm_WvQRSAtlkr3qtw4ABIab71aC7umTW20AAgI").setCaption("Photo");
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (messageText.equals("/markup")) {
            SendMessage message = new SendMessage().setChatId(chatId).setText("Here is your keyboard");
            // Create ReplyKeyboardMarkup object
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            row.add("Row 1 Button 1");
            row.add("Row 1 Button 2");
            row.add("Row 1 Button 3");
            // Add the first row to the keyboard
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
            // Set each button for the second line
            row.add("Row 2 Button 1");
            row.add("Row 2 Button 2");
            row.add("Row 2 Button 3");
            // Add the second row to the keyboard
            keyboard.add(row);
            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            // Add it to the message
            message.setReplyMarkup(keyboardMarkup);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (messageText.equals("Row 1 Button 1")) {
            SendPhoto msg = new SendPhoto()
                    .setChatId(chatId).setPhoto("AgADAgADPKkxG7LTiUm_WvQRSAtlkr3qtw4ABIab71aC7umTW20AAgI").setCaption("Photo");
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (messageText.equals("/hide")) {
            SendMessage msg = new SendMessage().setChatId(chatId).setText("Keyboard hidden");
            ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
            msg.setReplyMarkup(keyboardMarkup);
            try {
                execute(msg); // Call method to send the photo
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            SendMessage message = new SendMessage().setChatId(chatId).setText("Unknown command");
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
