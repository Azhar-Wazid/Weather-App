package com.example.weatherapp;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/*
    This Class provides the API Data
 */
public class OpenMeteoWeatherCall {
    private static OpenMeteoWeather api;
    private static Retrofit retrofit;

    private static OpenMeteoWeather getOpenMeteoWeather(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.open-meteo.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
            api = retrofit.create(OpenMeteoWeather.class);
        }
        return api;
    }

    /*
        Calls API and gives the data to a Callback interface
     */
    public void getOpenMeteoWeatherData(double latitude, double longitude, WeatherApiCallBack weatherApiCallBack){
        api = OpenMeteoWeatherCall.getOpenMeteoWeather();
        Call<WeatherResponse> call = api.getWeatherInfo(latitude, longitude);
        call.enqueue(new Callback<WeatherResponse>(){
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response){
                if (response.isSuccessful() && response.body() != null){
                    weatherApiCallBack.onWeatherDataLoaded(response.body());
                }
                else {
                    weatherApiCallBack.onWeatherDataLoadFailed(new Throwable("Response Failed"));
                }
            }
            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                t.getStackTrace();
                weatherApiCallBack.onWeatherDataLoadFailed(t);
            }
        });
    }
}
