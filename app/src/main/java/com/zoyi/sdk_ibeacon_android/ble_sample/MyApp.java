package com.zoyi.sdk_ibeacon_android.ble_sample;

import android.app.Application;
import android.provider.Settings;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.zoyi.sdk_ibeacon_android.lib.Target;
import com.zoyi.sdk_ibeacon_android.lib.ZBeaconManager;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mika on 2016. 6. 2..
 */
public class MyApp extends MultiDexApplication {
  static ZBeaconManager manager;
  static boolean autoStart = true;

  @Override
  public void onCreate() {
    super.onCreate();

    String email = "your@email.com";
    String token = "yourToken";

    manager = new ZBeaconManager(this, email, token, Target.DEVELOPMENT);
    // *** Target: PRODUCTION | DEVELOPMENT ***
    // *** if you want to send data to dev server, init with Target.DEVELOPMENT ***

    manager.setDebugMode(true);
    // if you want to see logs.
    // manager.setDebugMode(true);

    // you MUST set customer id for send signal.
    String deviceId = Settings.Secure.getString(
        getApplicationContext().getContentResolver(),
        Settings.Secure.ANDROID_ID);
    String customerId = hmacDigest(deviceId + "YOUR_SALT", "YOUR_KEY_FOR_HMAC", "HmacSHA512");

    if (customerId != null) {
      Log.i("CustomerId", customerId);
      manager.setCustomerId(customerId);
    }

    // You must start manager manually.
    manager.start();
  }
  
  private static String hmacDigest(String msg, String keyString, String algo) {
    String digest = null;
    try {
      SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
      Mac mac = Mac.getInstance(algo);
      mac.init(key);
      byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));
      StringBuffer hash = new StringBuffer();
      for (int i = 0; i < bytes.length; i++) {
        String hex = Integer.toHexString(0xFF & bytes[i]);
        if (hex.length() == 1) {
          hash.append('0');
        }
        hash.append(hex);
      }
      digest = hash.toString();
    } catch (UnsupportedEncodingException e) {
    } catch (InvalidKeyException e) {
    } catch (NoSuchAlgorithmException e) {
    }
    return digest;
  }

  public static void start() {
    manager.start();
  }

  public static void stop() {
    manager.stop();
  }
}
