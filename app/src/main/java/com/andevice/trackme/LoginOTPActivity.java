package com.andevice.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andevice.trackme.utils.SPUtils;

import static com.andevice.trackme.R.id.btn_submit_otp;

/**
 * Created by arun.singh on 1/3/2017.
 */

public class LoginOTPActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        findViewById(R.id.img_back_btn).setOnClickListener(this);
        findViewById(R.id.btn_submit_otp).setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_to_right, R.anim.slide_out_left_to_right);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.img_back_btn:
                onBackPressed();
                break;
            case R.id.btn_submit_otp:

                if(validateInputOTP())
                {

                    new SPUtils(LoginOTPActivity.this).setValue(SPUtils.REGISTERED_OTP,((EditText) findViewById(R.id.edOTP)).getText().toString());
                    Intent intent = new Intent(LoginOTPActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
    private boolean validateInputOTP() {
        String otp = ((EditText) findViewById(R.id.edOTP)).getText().toString();
        if (otp.isEmpty()) {
            Toast.makeText(LoginOTPActivity.this, "Enter received OTP", Toast.LENGTH_SHORT).show();
            return false;
        } else if (otp.length() != 6) {
            Toast.makeText(LoginOTPActivity.this, "Enter valid OTP", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
