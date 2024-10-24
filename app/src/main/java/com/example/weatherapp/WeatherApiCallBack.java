package com.example.weatherapp;

public interface WeatherApiCallBack {
    void onWeatherDataLoaded(WeatherResponse weatherResponse);
    void onWeatherDataLoadFailed(Throwable throwable);
}
