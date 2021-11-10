package com.wf.lifecycle.lifeCycle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author : wf
 * @date : 2021年11月02日 15:32
 */
public class MyLocationObserver implements LifecycleObserver {

    private MyLocationService mMyLocationService;
    private Context mContext;
    private LocationManager mLocationManager;
    private MyLocationListener mLocationListener;

    public MyLocationObserver(Context context) {
        mContext = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void startGps() {
        Log.d("wf","startGps");
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, mLocationListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void stopGps() {
        Log.d("wf","stopGps");
        mLocationManager.removeUpdates(mLocationListener);
    }

    static class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Log.d("wf","onLocationChanged");
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
}