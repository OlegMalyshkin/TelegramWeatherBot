package main;

import bot.WeatherBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramBotMain {

    static Logger log = Logger.getLogger(TelegramBotMain.class.getName());

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new WeatherBot());
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exeption: ", e.toString());
        }
    }

}