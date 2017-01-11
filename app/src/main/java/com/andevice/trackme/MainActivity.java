package com.andevice.trackme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.andevice.trackme.fragment.FakeCaller;
import com.andevice.trackme.fragment.HomeFragment;
import com.andevice.trackme.fragment.NotificationFragment;
import com.andevice.trackme.fragment.PeopleListFragment;
import com.andevice.trackme.utils.SPUtils;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        homeFragment =new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,homeFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_logou:
            {
                new SPUtils(MainActivity.this).clearPreferences();
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if(fragment instanceof HomeFragment)
        {
            if(!homeFragment.onBackPressedConsumed())
                finish();
        }else if(fragment instanceof PeopleListFragment || fragment instanceof NotificationFragment || fragment instanceof FakeCaller)
        {
            getSupportFragmentManager().popBackStackImmediate();
        }

    }
}
