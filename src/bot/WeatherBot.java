package bot;

import api.GeoNames;
import api.OpenWeatherMap;
import menu.Menu;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherBot extends TelegramLongPollingBot {

    private Logger log = Logger.getLogger(WeatherBot.class.getName());
    private Menu menu = new Menu();
    private String currentCity;
    private int mode = 1;
    private boolean change = false;

    public synchronized void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            switch (mode) {
                case 1 :
                    menu.createMainMenu();
                    menu.setCurrentKeyboard(menu.createMainMenu());
                    sendMessage.setReplyMarkup(menu.getReplyKeyboardMarkup());
                    break;
                case 2 :
                    menu.createSettingMenu();
                    menu.setCurrentKeyboard(menu.createSettingMenu());
                    sendMessage.setReplyMarkup(menu.getReplyKeyboardMarkup());
                    break;
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exeption: ", e.toString());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String message_text = update.getMessage().getText();
            switch (message_text){
                case "/start":
                    sendMsg(update.getMessage(), "Введите ваш город");
                    currentCity = message_text;
                    break;
                case "Настройки" :
                    mode = 2;
                    sendMsg(update.getMessage(), "Настройки");
                    break;
                case "Мой город" :
                    sendMsg(update.getMessage(), "Установите свой город\nНастройки -> Изменить город");
                    currentCity = update.getMessage().getText();
                    mode = 1;
                    break;
                case "Назад" :
                    mode = 1;
                    sendMsg(update.getMessage(), "Назад");
                    break;
                case "О Боте" :
                    sendMsg(update.getMessage(), "WeatherBot - бот, который показывает текущую погоду в любой точке мира.\n" +
                            "Автор: Малышкин Олег");
                    break;
                case "Изменить город" :
                    sendMsg(update.getMessage(), "Введите ваш город");
                    change = true;
                    mode = 1;
                    break;
                default:
                    if (change){
                        currentCity = message_text;
                        menu.setBtnCityText(currentCity);
                        change = false;
                    }
                        try{
                            sendMsg(update.getMessage(), message_text + " " + OpenWeatherMap.getWeather(GeoNames.cityNameToId(message_text)));
                        } catch (IOException e) {
                            sendMsg(update.getMessage(), "Город не найден");
                        } catch (Exception e) {
                            sendMsg(update.getMessage(), "Ошибка поиска id Город");
                        }
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "WeatherSimpleBot";
    }

    @Override
    public String getBotToken() {
        return "";
    }

}
