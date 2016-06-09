package com.zoyi.sdk_ibeacon_android.ble_sample;

import android.app.Application;
import android.util.Log;
import com.zoyi.sdk_ibeacon_android.lib.ZBeaconManager;

/**
 * Created by mika on 2016. 6. 2..
 */
public class MyApp extends Application {
  static ZBeaconManager manager;
  static boolean autoStart = true;

  @Override
  public void onCreate() {
    super.onCreate();

    String email = "your@email.com";
    String token = "yourToken";
    int brandId = 0; // your brand id

    manager = new ZBeaconManager(this, email, token, brandId);
    manager.setDebugMode(true);

    // you must save variable for check auto start scanning
    // if user turn off scanning,
    // you must set some variable to local settings and check here by it.

    // DO NOT USE variable like this sample. it doesn't save when app is restar.
    // YOU MUST save it to local storage like 'SharedPreferences'.

    if (autoStart) {
      manager.start();
    }

    // If you want to know android id or package id
    String androidId = manager.getDeviceId();
    String packageId = manager.getPackageId();
  }

  public static void start() {
    manager.start();
  }

  public static void stop() {
    manager.stop();
  }
}
