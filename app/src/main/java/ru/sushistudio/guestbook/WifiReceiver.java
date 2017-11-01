package ru.sushistudio.guestbook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by android on 01.11.17.
 */

public class WifiReceiver extends BroadcastReceiver {
    private static final String TAG = WifiReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Log.i(TAG, "onReceive: have wifi connection");
            WifiHelper.setWifiConnected(true);
        } else {
            Log.i(TAG, "onReceive: dont have wifi connection");
            WifiHelper.setWifiConnected(false);
        }
    }
}
