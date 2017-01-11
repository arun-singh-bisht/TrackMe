package com.andevice.trackme.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.andevice.trackme.R;
import com.andevice.trackme.model.People;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arun.singh on 1/2/2017.
 */

public class NotificationFragment extends Fragment{

    View convertView;
    Menu menu;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_notification, null, false);

            //Setup Action bar
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notification");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        }
        return convertView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu=menu;
        menu.findItem(R.id.action_add_person).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_security).setVisible(false);
        menu.findItem(R.id.action_remove).setVisible(false);
        menu.findItem(R.id.action_location_off).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
