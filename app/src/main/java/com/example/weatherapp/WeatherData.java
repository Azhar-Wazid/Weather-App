package com.example.weatherapp;

import java.time.LocalDateTime;

/*
    This takes and assigns data
 */
public class WeatherData {
    private LocalDateTime time;
    private double temp;
    private double pressure;
    private double windSpeed;
    private int humidity;
    private WeatherType weatherType;

    public WeatherData() {

    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public int getHour(){
        return getTime().getHour();
    }


    public void setTemp(double temp) {
        this.temp = temp;
    }
    public double getTemp() {
        return temp;
    }


    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public double getWindSpeed() {
        return windSpeed;
    }


    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
    public double getPressure() {
        return pressure;
    }


    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getHumidity() {
        return humidity;
    }


    public void setWeatherType(int WMOCode) {
        weatherType = WeatherType.getWMO(WMOCode);
    }
    public WeatherType getWeatherType() {
        return weatherType;
    }
}
