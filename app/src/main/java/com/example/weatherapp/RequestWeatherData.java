package com.example.weatherapp;

import java.util.List;
/*
    Callback interface to allow sorted data to be used
 */
public interface RequestWeatherData {
    void onWeatherDataLoaded(WeatherData weatherData); // current hour weather
    void onWeatherListLoaded(List<WeatherData> weatherDataList); // current day hourly weather
}
