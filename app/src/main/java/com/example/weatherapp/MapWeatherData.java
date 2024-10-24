package com.example.weatherapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapWeatherData {
    private int index;
    private final WeatherDataList weatherDataList;
    private final List<MapDataList> mapDataList;

    public MapWeatherData(WeatherDataList weatherDataList) {
        index = 0;
        this.weatherDataList = weatherDataList;
        mapDataList = new ArrayList<>();
    }

    public Map<Integer, List<WeatherData>> readWeatherData() {
        for (index = 0; index < weatherDataList.getTimeSize(); index++) {
            WeatherData weatherData = new WeatherData();
            weatherData.setTime(LocalDateTime.parse(weatherDataList.getTime(index), DateTimeFormatter.ISO_DATE_TIME));
            weatherData.setTemp(weatherDataList.getTemp(index));
            weatherData.setHumidity(weatherDataList.getHumidity(index));
            weatherData.setPressure(weatherDataList.getPressure(index));
            weatherData.setWindSpeed(weatherDataList.getWindSpeed(index));
            weatherData.setWeatherType(weatherDataList.getWeatherCode(index));
            addMapDataList(index, weatherData);
        }
        return getMapDataList().stream().collect(
                Collectors.groupingBy(
                        weatherList -> weatherList.getIndex()/24,
                        Collectors.mapping(MapDataList::getWeatherData, Collectors.toList())
                )
        );
    }



    private void addMapDataList(int currentIndex, WeatherData currentWeatherData){
        mapDataList.add(new MapDataList(currentIndex, currentWeatherData));
    }
    private List<MapDataList> getMapDataList(){
        return mapDataList;
    }
}

class MapDataList {
    int index;
    WeatherData weatherData;

    public MapDataList(int index, WeatherData weatherData){
        this.index = index;
        this.weatherData = weatherData;
    }
    
    public int getIndex() {
        return index;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }
}