package com.example.weatherapp;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RequestWeatherData{
    UserLocation userLocation;
    WeatherInfo weatherInfo;
    TextView temp, pressure, windSpeed, humidity, weatherDescription;
    ImageView mainIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();

        if (activeNetworkInfo == null) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(true);
            alert.setTitle("An Error Occurred");
            alert.setMessage("Ensure your device has Network Access");
            alert.setNegativeButton("Exit", (dialog, which) -> finishAndRemoveTask());
            alert.show();
        }
        else {

            userLocation = new UserLocation(this);

            userLocation.requestLocation(this, new UserLocation.LocationCallback() {
                @Override
                public void onLocationResult(double latitude, double longitude) {
                    Log.d("Location", "Lat: " + latitude + " Long: " + longitude);
                    getWeatherData(latitude, longitude);

                }

                @Override
                public void onFailure(String errorMessage) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setCancelable(true);
                    alert.setTitle("An Error Occurred");
                    alert.setMessage("Ensure your device has Location turned on");
                    alert.setNegativeButton("Exit", (dialog, which) -> finishAndRemoveTask());
                    alert.show();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == UserLocation.getLocationPermissionRequestCode()) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userLocation.requestLocation(this, new UserLocation.LocationCallback() {
                    @Override
                    public void onLocationResult(double latitude, double longitude) {
                        Log.d("MainActivity", "Latitude: " + latitude + ", Longitude: " + longitude);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("MainActivity", "Failed to get location: " + errorMessage);
                    }
                });
            } else {
                Log.e("MainActivity", "Location permission denied");
            }
        }
    }

    public void getWeatherData(double latitude, double longitude){
        weatherInfo = new WeatherInfo(latitude, longitude);
        weatherInfo.getWeatherData(this);
    }

    @Override
    public void onWeatherDataLoaded(WeatherData weatherData) {
        displayCurrentWeather(weatherData);
    }

    @Override
    public void onWeatherListLoaded(List<WeatherData> weatherDataList) {
        displayTodayWeather(weatherDataList);
    }

    public void displayCurrentWeather(WeatherData weatherData){
        if (weatherData != null){
            Log.d("displayCurrent", "TEMP: " + weatherData.getTemp() + " WeatherType: " + weatherData.getWeatherType().getWeatherType());
            temp = findViewById(R.id.temp);
            temp.setText(String.format(Locale.getDefault(),"%s°C" ,weatherData.getTemp()));

            pressure = findViewById(R.id.pressure);
            pressure.setText(String.format(Locale.getDefault(),"%s hPa" ,weatherData.getPressure()));

            humidity = findViewById(R.id.humidity);
            humidity.setText(String.format(Locale.getDefault(),"%s%%" ,weatherData.getHumidity()));

            windSpeed = findViewById(R.id.windSpeed);
            windSpeed.setText(String.format(Locale.getDefault(),"%s km/h" ,weatherData.getWindSpeed()));

            weatherDescription = findViewById(R.id.weatherDescription);
            weatherDescription.setText(String.format(Locale.getDefault(),"%s" ,weatherData.getWeatherType().getWeatherType()));

            mainIcon = findViewById(R.id.mainIcon);
            mainIcon.setImageResource(weatherData.getWeatherType().getIcon());
        }
        else {
            Log.d("displayCurrent", "No Weather data");
        }
    }

    public void  displayTodayWeather(List<WeatherData> weatherDataList){
        LinearLayout linearLayout = findViewById(R.id.horizontalLinearLayout);
        for (int i = 0; i < weatherDataList.size(); i++) {
            LinearLayout linearLayoutBox = new LinearLayout(this);
            linearLayoutBox.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            boxParams.setMargins(24, 16, 24, 16);
            linearLayoutBox.setLayoutParams(boxParams);

            TextView textView = new TextView(this);
            textView.setText(String.format(Locale.getDefault(),"%s:00" ,weatherDataList.get(i).getHour()));
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(14);
            textView.setPadding(16, 14, 16, 14);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(textParams);

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams imageSize = new LinearLayout.LayoutParams(100,100);
            imageSize.gravity = Gravity.CENTER_HORIZONTAL;
            imageView.setLayoutParams(imageSize);
            imageView.setImageResource(weatherDataList.get(i).getWeatherType().getIcon());

            TextView textViewTemp = new TextView(this);
            textViewTemp.setText(String.format(Locale.getDefault(),"%s°C" ,weatherDataList.get(i).getTemp()));
            textViewTemp.setTextColor(Color.WHITE);
            textViewTemp.setTextSize(14);
            textViewTemp.setPadding(16, 14, 16, 14);
            LinearLayout.LayoutParams tempParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            tempParams.gravity = Gravity.CENTER_HORIZONTAL;
            textViewTemp.setLayoutParams(tempParams);

            linearLayoutBox.addView(textView);
            linearLayoutBox.addView(imageView);
            linearLayoutBox.addView(textViewTemp);
            linearLayout.addView(linearLayoutBox);
        }
    }
}