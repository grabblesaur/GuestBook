package ru.sushistudio.guestbook;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

/**
 * Created by android on 31.10.17.
 */

public class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver {
    private static final String TAG = "DeviceAdminReceiver";

    /**
     * @param context The context of the application.
     * @return The component name of this component in the given context.
     */
    public static ComponentName getComponentName(Context context) {
        ComponentName componentName = new ComponentName(context.getApplicationContext(), DeviceAdminReceiver.class);
        Log.i(TAG, "getComponentName: " + componentName);
        Log.i(TAG, "getComponentName.getPackageName(): " + componentName.getPackageName());
        Log.i(TAG, "getComponentName.getClassName(): " + componentName.getClassName());
        return componentName;
    }
}
