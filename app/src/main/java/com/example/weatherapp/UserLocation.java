package com.example.weatherapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

/*
    GPS prompt
    permission denied error message
 */

public class UserLocation {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private final FusedLocationProviderClient fusedLocationClient;
    private final Context context;

    public UserLocation(Context context) {
        this.context = context;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public static int getLocationPermissionRequestCode() {
        return LOCATION_PERMISSION_REQUEST_CODE;
    }

    public void requestLocation(Activity activity, final LocationCallback callback) {
        // Check for location permission
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission is already granted
            getLastKnownLocation(callback);
        }
    }

    private void getLastKnownLocation(final LocationCallback callback) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            Log.e("UserLocation", "Error getting location: User Denied");
            callback.onFailure("Location denied");
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        Log.d("UserLocation", "Latitude: " + latitude + ", Longitude: " + longitude);
                        callback.onLocationResult(latitude, longitude);
                    } else {
                        Log.d("UserLocation", "Location is null");
                        callback.onFailure("Location is null");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("UserLocation", "Error getting location: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    // Define a callback interface to handle the result
    public interface LocationCallback {
        void onLocationResult(double latitude, double longitude);
        void onFailure(String errorMessage);
    }
}
