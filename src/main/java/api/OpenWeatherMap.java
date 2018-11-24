package api;

import entity.Weather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class OpenWeatherMap {

    public static String getWeather(Integer cityId) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + cityId +
                "&units=metric&lang=ru&appid=");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while(in.hasNext()){
            result += in.nextLine();
        }
        Weather weather = new Weather();
        JSONObject object = new JSONObject(result);
        JSONArray jsonWeather = object.getJSONArray("weather");
        for(int i = 0; i < jsonWeather.length(); i++){
            JSONObject objWeater = jsonWeather.getJSONObject(i);
            weather.setMain(objWeater.getString("description"));
        }
        weather.setTemp(object.getJSONObject("main").getDouble("temp"));
        weather.setHumidity(object.getJSONObject("main").getDouble("humidity"));
        weather.setClouds(object.getJSONObject("clouds").getDouble("all"));
        weather.setWindSpeed(object.getJSONObject("wind").getDouble("speed"));
        weather.setPressure(object.getJSONObject("main").getDouble("pressure"));
        return weather.toString();
    }

}
