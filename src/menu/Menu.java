package menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private ReplyKeyboardMarkup replyKeyboardMarkup;

    private KeyboardButton btnSetting;
    private KeyboardButton btnCurrentCity;
    private KeyboardButton btnChangeCity;
    private KeyboardButton btnAbout;
    private KeyboardButton btnBack;

    private String btnCityText;

    public Menu(){

        replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        btnSetting = new KeyboardButton();
        btnCurrentCity = new KeyboardButton();
        btnChangeCity = new KeyboardButton();
        btnAbout = new KeyboardButton();
        btnBack = new KeyboardButton();

    }

    public List<KeyboardRow> createMainMenu(){
        if(btnCityText != null) {
            btnCurrentCity.setText(btnCityText);
        } else {
            btnCurrentCity.setText("Мой город");
        }
        btnSetting.setText("Настройки");
        List<KeyboardRow> keyboardMain = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(btnCurrentCity);
        keyboardFirstRow.add(btnSetting);
        keyboardMain.add(keyboardFirstRow);
        return keyboardMain;
    }

    public List<KeyboardRow> createSettingMenu(){
        btnChangeCity.setText("Изменить город");
        btnAbout.setText("О Боте");
        btnBack.setText("Назад");
        List<KeyboardRow> keyboardSetting = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add(btnChangeCity);
        keyboardFirstRow.add(btnAbout);
        keyboardSecondRow.add(btnBack);
        keyboardSetting.add(keyboardFirstRow);
        keyboardSetting.add(keyboardSecondRow);
        return  keyboardSetting;
    }

    public void setCurrentKeyboard(List<KeyboardRow> keyboard) {
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public String getBtnCityText() {
        return btnCityText;
    }

    public void setBtnCityText(String btnCityText) {
        this.btnCityText = btnCityText;
    }

    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        return replyKeyboardMarkup;
    }

    public void setReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup) {
        this.replyKeyboardMarkup = replyKeyboardMarkup;
    }
}
