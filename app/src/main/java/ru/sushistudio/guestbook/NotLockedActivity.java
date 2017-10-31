package ru.sushistudio.guestbook;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotLockedActivity extends AppCompatActivity {

    @BindView(R.id.nla_start_btn)
    Button mStartKioskModeButton;

    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mAdminComponentName;
    private PackageManager mPackageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_locked);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mAdminComponentName = DeviceAdminReceiver.getComponentName(this);
        mPackageManager = this.getPackageManager();

        mStartKioskModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDevicePolicyManager.isDeviceOwnerApp(getApplicationContext().getPackageName())) {
                    Intent lockIntent = new Intent(NotLockedActivity.this, MainActivity.class);
                    mPackageManager.setComponentEnabledSetting(
                            new ComponentName(getApplicationContext(), MainActivity.class),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                    startActivity(lockIntent);
                    finish();
                } else {
                    Toast.makeText(NotLockedActivity.this,
                            R.string.not_device_owner,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // check to see if started by MainActivity and disable MainActivity so
        Intent intent = getIntent();
        if (intent.getIntExtra(MainActivity.MAIN_ACTIVITY_KEY, 0) == MainActivity.FROM_MAIN_ACTIVITY) {
            mDevicePolicyManager.clearPackagePersistentPreferredActivities(mAdminComponentName, getPackageName());
            mPackageManager.setComponentEnabledSetting(
                    new ComponentName(getApplicationContext(), MainActivity.class),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }
}
















