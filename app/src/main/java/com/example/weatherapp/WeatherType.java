package com.example.weatherapp;

import androidx.annotation.DrawableRes;

/*
    This class takes a int value and returns the weather type
 */
public class WeatherType {
    private final String weatherType;
    private final @DrawableRes int icon;

    public String getWeatherType(){
        return weatherType;
    }

    public int getIcon() {
        return icon;
    }

    public WeatherType(String weatherType, @DrawableRes int icon){
        this.weatherType = weatherType;
        this.icon = icon;
    }

    //takes the WMO code and returns the weather type and its drawable
    public static WeatherType getWMO(int WMOCode){
        switch (WMOCode){
            case 1:
                return MainlyClear;
            case 2:
                return PartlyCloudy;
            case 3:
                return Overcast;
            case 45:
                return Fog;
            case 48:
                return DepositingRimeFog;
            case 51:
                return LightDrizzle;
            case 53:
                return ModerateDrizzle;
            case 55:
                return DenseDrizzle;
            case 56:
                return LightFreezingDrizzle;
            case 57:
                return DenseFreezingDrizzle;
            case 61:
                return SlightRain;
            case 63:
                return ModerateRain;
            case 65:
                return HeavyRain;
            case 66:
                return LightFreezingRain;
            case 67:
                return HeavyFreezingRain;
            case 71:
                return SlightSnowFall;
            case 73:
                return ModerateSnowFall;
            case 75:
                return HeavySnowFall;
            case 77:
                return SnowGrains;
            case 80:
                return SlightRainShowers;
            case 81:
                return ModerateRainShowers;
            case 82:
                return ViolentRainShowers;
            case 85:
                return SlightSnowShowers;
            case 86:
                return HeavySnowShowers;
            case 95:
                return Thunderstorm;
            case 96:
                return SlightHailThunderstorm;
            case 99:
                return HeavyHailThunderstorm;
            case 0:
            default:
                return ClearSky;
        }
    }

    // assigns the weather type to its corresponding object
    static WeatherType ClearSky = new WeatherType("Clear sky", R.drawable.ic_sunny);

    static WeatherType MainlyClear = new WeatherType("Mainly clear", R.drawable.ic_cloudy);
    static WeatherType PartlyCloudy = new WeatherType("Partly cloudy", R.drawable.ic_cloudy);
    static WeatherType Overcast = new WeatherType("Overcast", R.drawable.ic_cloudy);

    static WeatherType Fog = new WeatherType("Fog", R.drawable.ic_very_cloudy);
    static WeatherType DepositingRimeFog = new WeatherType("Depositing rime fog", R.drawable.ic_very_cloudy);

    static WeatherType LightDrizzle = new WeatherType("Light drizzle", R.drawable.ic_rainshower);
    static WeatherType ModerateDrizzle = new WeatherType("Moderate drizzle", R.drawable.ic_rainshower);
    static WeatherType DenseDrizzle = new WeatherType("Dense drizzle", R.drawable.ic_rainshower);

    static WeatherType LightFreezingDrizzle = new WeatherType("Slight freezing drizzle", R.drawable.ic_snowyrainy);
    static WeatherType DenseFreezingDrizzle = new WeatherType("Dense freezing drizzle", R.drawable.ic_snowyrainy);

    static WeatherType SlightRain = new WeatherType("Slight rain", R.drawable.ic_rainy);
    static WeatherType ModerateRain = new WeatherType("Rainy", R.drawable.ic_rainy);
    static WeatherType HeavyRain = new WeatherType("Heavy rain", R.drawable.ic_rainy);

    static WeatherType LightFreezingRain = new WeatherType("Light freezing rain", R.drawable.ic_snowyrainy);
    static WeatherType HeavyFreezingRain = new WeatherType("Heavy freezing rain", R.drawable.ic_snowyrainy);

    static WeatherType SlightSnowFall = new WeatherType("Slight snow fall", R.drawable.ic_snowy);
    static WeatherType ModerateSnowFall = new WeatherType("Moderate snow fall", R.drawable.ic_heavysnow);
    static WeatherType HeavySnowFall = new WeatherType("Heavy snow fall", R.drawable.ic_heavysnow);

    static WeatherType SnowGrains = new WeatherType("Snow grains", R.drawable.ic_heavysnow);

    static WeatherType SlightRainShowers = new WeatherType("Slight rain showers", R.drawable.ic_rainshower);
    static WeatherType ModerateRainShowers = new WeatherType("Moderate rain showers", R.drawable.ic_rainshower);
    static WeatherType ViolentRainShowers = new WeatherType("Violent rain showers", R.drawable.ic_rainshower);

    static WeatherType SlightSnowShowers = new WeatherType("Light snow showers", R.drawable.ic_snowy);
    static WeatherType HeavySnowShowers = new WeatherType("Heavy snow showers", R.drawable.ic_snowy);

    static WeatherType Thunderstorm = new WeatherType("Moderate thunderstorm", R.drawable.ic_thunder);

    static WeatherType SlightHailThunderstorm = new WeatherType("Thunderstorm with slight hail", R.drawable.ic_rainythunder);
    static WeatherType HeavyHailThunderstorm = new WeatherType("Thunderstorm with heavy hail", R.drawable.ic_rainythunder);
}
