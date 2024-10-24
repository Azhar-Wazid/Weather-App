package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
    Connects to Open-Metro to get data

    @GET shows the location to the data

    getWeatherInfo has two params @Query gives the lat & long
    Call<> to retrieve the data from that lat & long location
    stores the data in WeatherResponse class
 */

public interface OpenMeteoWeather {
    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,weather_code,pressure_msl,wind_speed_10m&timezone=auto")
    Call<WeatherResponse> getWeatherInfo(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude
            );
}
