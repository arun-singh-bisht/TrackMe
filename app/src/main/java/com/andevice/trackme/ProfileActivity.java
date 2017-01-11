package com.andevice.trackme;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andevice.trackme.utils.PermissionUtils;
import com.andevice.trackme.utils.SPUtils;

import static com.andevice.trackme.utils.PermissionUtils.MULTIPLE_PERMISSIONS;

/**
 * Created by arun.singh on 1/3/2017.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        findViewById(R.id.btn_save).setOnClickListener(this);

        /*Asking Permissions*/
        new PermissionUtils().requestForPermission(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_save:
            {
                 if(validateInputName())
                 {
                     new SPUtils(ProfileActivity.this).setValue(SPUtils.USER_NAME,((EditText) findViewById(R.id.edName)).getText().toString());
                     finishAffinity();
                     startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                 }
            }
        }
    }

    private boolean validateInputName() {
        String phone_nubmer = ((EditText) findViewById(R.id.edName)).getText().toString();
        if (phone_nubmer.isEmpty()) {
            Toast.makeText(ProfileActivity.this, "Enter your display name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.
                    System.out.println("Permissino Granted");
                } else {
                    // no permissions granted.
                    System.out.println("Permissino not Granted");
                }
                return;
            }
        }
    }
}
