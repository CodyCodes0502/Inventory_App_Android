package com.zybooks.cs_360_project.view;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zybooks.cs_360_project.R;

import android.content.Context;
import android.telephony.TelephonyManager;

public class AlertsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "AlertsPrefs";
    public static final String SMS_ALERT_KEY = "sms_Enabled";
    public static final String KEY_PHONE_NUMBER = "phone_Number";

    private Switch mSwitchSmsAlert;
    private EditText mEditTextPhoneNumber;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        mSwitchSmsAlert = findViewById(R.id.switch_sms_alert);
        mEditTextPhoneNumber = findViewById(R.id.edit_phone_number);
        mSharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        mSwitchSmsAlert.setChecked(mSharedPreferences.getBoolean(SMS_ALERT_KEY, false));
        
        String savedPhone = mSharedPreferences.getString(KEY_PHONE_NUMBER, "");
        if (savedPhone.isEmpty()) {
            savedPhone = getDevicePhoneNumber();
            if (!savedPhone.isEmpty()) {
                mSharedPreferences.edit().putString(KEY_PHONE_NUMBER, savedPhone).apply();
            }
        }
        mEditTextPhoneNumber.setText(savedPhone);

        mSwitchSmsAlert.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked && ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_PHONE_NUMBERS
                }, 1);
            }
            mSharedPreferences.edit().putBoolean(SMS_ALERT_KEY, isChecked).apply();
        });

        mEditTextPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mSharedPreferences.edit().putString(KEY_PHONE_NUMBER, s.toString()).apply();
            }
        });
    }

    private String getDevicePhoneNumber() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

            TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            try {
                String line1Number = tMgr.getLine1Number();
                return line1Number != null ? line1Number : "";
            } catch (SecurityException e) {
                return "";
            }
        }
        return "";
    }
}
