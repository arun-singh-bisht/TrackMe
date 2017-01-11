package com.andevice.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andevice.trackme.utils.SPUtils;

/**
 * Created by arun.singh on 1/3/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String registered_otp = new SPUtils(LoginActivity.this).getString(SPUtils.REGISTERED_OTP);
        String registered_username = new SPUtils(LoginActivity.this).getString(SPUtils.USER_NAME);
        if (registered_otp != null && registered_otp.length() > 0) {

            if (registered_username != null && registered_username.length() > 0) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            }
            finish();
        }


        findViewById(R.id.btn_submit).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit: {

                if (validateInputPhoneNumber())
                    startActivity(new Intent(LoginActivity.this, LoginOTPActivity.class));
            }
            break;
        }
    }

    private boolean validateInputPhoneNumber() {
        String phone_nubmer = ((EditText) findViewById(R.id.edPhoneNumber)).getText().toString();
        if (phone_nubmer.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phone_nubmer.length() != 10) {
            Toast.makeText(LoginActivity.this, "Enter 10 digits phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
