package entity;

public class Weather {

    private String city;
    private String main;
    private Double temp;
    private Double humidity;
    private Double clouds;
    private Double windSpeed;
    private Double pressure;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getClouds() {
        return clouds;
    }

    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getPressure() {
        return pressure;
    }

    //1 hPa = 0.75006375541921 mmHg
    public void setPressure(Double pressure) {
        this.pressure = pressure * 0.75006375541921;
    }

    @Override
    public String toString() {
        StringBuilder resultWeather = new StringBuilder();
        resultWeather.
//                append(city).
                append(" - ").
                append(main).append("\n").
                append("Температура ").append(String.format("%.0f", temp)).append("°C").append("\n").
                append("Влажность ").append(String.format("%.0f", humidity)).append("%").append("\n").
                append("Облачность ").append(String.format("%.0f",clouds)).append("%").append("\n").
                append("Скорость ветра ").append(String.format("%.0f",windSpeed)).append(" м/с").append("\n").
                append("Давление ").append(String.format("%.0f",pressure)).append(" мм.рт.ст");
        return resultWeather.toString();
    }
}
