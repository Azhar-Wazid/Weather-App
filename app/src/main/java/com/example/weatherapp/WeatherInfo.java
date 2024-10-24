package com.example.weatherapp;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class WeatherInfo {
    OpenMeteoWeatherCall apiCall;
    MapWeatherData mapWeatherData;
    RequestWeatherData requestWeatherData;
    double latitude;
    double longitude;

    public WeatherInfo(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        apiCall = new OpenMeteoWeatherCall();
        getApiData();
    }

    private void getApiData(){
            apiCall.getOpenMeteoWeatherData(latitude, longitude, new WeatherApiCallBack() {
                @Override
                public void onWeatherDataLoaded(WeatherResponse weatherResponse) {
                    Log.d("WeatherData", "Loaded time: " + weatherResponse.getWeatherData().getTime(18) + " Temp: " + weatherResponse.getWeatherData().getTemp(18));
                    mapWeatherData = new MapWeatherData(weatherResponse.getWeatherData());
                    Map<Integer, List<WeatherData>> mapData = mapWeatherData.readWeatherData();
                    requestWeatherData.onWeatherDataLoaded(currentHourWeather(mapData));
                    requestWeatherData.onWeatherListLoaded(mapData.get(0));
                }

                @Override
                public void onWeatherDataLoadFailed(Throwable throwable) {
                    Log.e("WeatherDataError", "Failed to load data: " + throwable.getMessage());
                }
            });
    }

    private int userTime(LocalDateTime now){
        if (now.getMinute() > 30){
            return now.getHour() + 1;
        }
        else {
            return now.getHour();
        }
    }

    public WeatherData currentHourWeather(Map<Integer, List<WeatherData>> sortedData){
        LocalDateTime now = LocalDateTime.now();
        int hour = userTime(now);
        Log.d("currentTime", "" + hour);
        List<WeatherData> listData = sortedData.get(0);

        if (listData != null){
            return listData.stream()
                    .filter(time -> time.getHour() == hour)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public void getWeatherData(RequestWeatherData requestWeatherData) {
        this.requestWeatherData = requestWeatherData;
    }

}