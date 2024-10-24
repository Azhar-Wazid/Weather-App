package com.example.weatherapp;

import com.squareup.moshi.Json;

import java.util.List;

/*
    adds all the data to their list
 */
public class WeatherDataList {
    @Json(name = "time")
    List<String> time;

    @Json(name = "temperature_2m")
    List<Double> temp;

    @Json(name = "relative_humidity_2m")
    List<Integer> humidity;

    @Json(name = "pressure_msl")
    List<Double> pressure;

    @Json(name = "wind_speed_10m")
    List<Double> windSpeed;

    @Json(name = "weather_code")
    List<Integer> weatherCode;

    public int getTimeSize() {
        return time.size();
    }

    public String getTime(int index) {
        return time.get(index);
    }

    public double getTemp(int index) {
        return temp.get(index);
    }

    public int getHumidity(int index) {
        return humidity.get(index);
    }

    public double getPressure(int index) {
        return pressure.get(index);
    }

    public double getWindSpeed(int index) {
        return windSpeed.get(index);
    }

    public int getWeatherCode(int index) {
        return weatherCode.get(index);
    }
}
