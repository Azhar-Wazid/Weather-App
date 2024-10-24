package com.example.weatherapp;


import com.squareup.moshi.Json;

public class WeatherResponse {
    @Json(name = "hourly")
    WeatherDataList weatherData;

    public WeatherDataList getWeatherData() {
        return weatherData;
    }
}
