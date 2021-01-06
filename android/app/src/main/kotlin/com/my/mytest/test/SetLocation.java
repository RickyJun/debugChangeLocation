package com.my.mytest.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;


import io.flutter.embedding.android.FlutterActivity;


public class SetLocation implements LocationListener {
    private String mMockProviderName = LocationManager.GPS_PROVIDER;
    private Thread thread;
    private LocationManager locationManager;
    private Boolean is_run = true;
    private FlutterActivity mainActivity;
    public SetLocation(FlutterActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    /**2. 初始化
     * inilocation 初始化 位置模拟
     *
     */
    public void init_location() {
        System.out.println("定位初始化");
        locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);
        locationManager.addTestProvider(mMockProviderName, false, true, false, false, true, true, true, 0, 5);
        locationManager.setTestProviderEnabled(mMockProviderName, true);
        locationManager.requestLocationUpdates(mMockProviderName, 0, 0, this);
    }
   // 3. 设置地理位置模拟
    @SuppressLint("NewApi")
    private void setLocation(double longitude, double latitude) {
        System.out.println("设置定位");
        Location location = new Location(mMockProviderName);
        location.setTime(System.currentTimeMillis());
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(2.0f);
        location.setAccuracy(3.0f);
        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(mMockProviderName, location);
        System.out.println("完成定位");
    }

    public void startSet(double longitude, double latitude){
//        System.out.println("开始定位");
//        if (Settings.Secure.getInt(this.mainActivity.getContentResolver(),Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0){
//            // 开启了地理位置模拟
            // 开启线程，一直修改GPS坐标
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (is_run) {
                        try {
                            Thread.sleep(500);
                            setLocation(longitude, latitude);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
//        }else{
//            System.out.println("没有权限啊");
//        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}