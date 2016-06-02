package com.zoyi.sdk_ibeacon_android.ble_sample;

import android.app.Application;
import android.util.Log;
import com.zoyi.sdk_ibeacon_android.lib.ZBeaconManager;

/**
 * Created by mika on 2016. 6. 2..
 */
public class MyApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    String email = "your@email";
    String token = "yourToken";
    int brandId = 0; // your brand id

    ZBeaconManager manager = new ZBeaconManager(this, email, token, brandId);

    // If you want to know android id or package id
    String androidId = manager.getAndroidId();
    String packageId = manager.getPackageId();
  }
}
