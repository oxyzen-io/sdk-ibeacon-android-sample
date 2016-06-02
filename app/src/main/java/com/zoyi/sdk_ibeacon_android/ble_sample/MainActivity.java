package com.zoyi.sdk_ibeacon_android.ble_sample;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
  private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This app needs location access");
        builder.setMessage("Please grant location access so this app can detect beacons.");
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialog) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              requestPermissions(new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                                 PERMISSION_REQUEST_COARSE_LOCATION);
            }
          }
        });
        builder.show();
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    switch (requestCode) {
      case PERMISSION_REQUEST_COARSE_LOCATION: {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Log.d("Permission", "coarse location permission granted");
        } else {
          final AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Functionality limited");
          builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
          builder.setPositiveButton(android.R.string.ok, null);
          builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
          });
          builder.show();
        }
        return;
      }
    }
  }
}
