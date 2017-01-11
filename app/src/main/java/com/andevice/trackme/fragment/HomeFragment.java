package com.andevice.trackme.fragment;


import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andevice.trackme.R;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import in.teramatrix.slidingpanel.SlidingUpPanelLayout;

/**
 * Created by arun.singh on 1/2/2017.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    View convertView;
    Menu menu;
    BottomSheetLayout bottomSheet;
    SlidingUpPanelLayout slidingUpPanelLayout;
    boolean is_detail_view_showing;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_home_layout, null, false);
            bottomSheet = (BottomSheetLayout) convertView.findViewById(R.id.bottomsheet);
            slidingUpPanelLayout = (SlidingUpPanelLayout) convertView.findViewById(R.id.sliding_layout);

            initView();
            initializeMap(savedInstanceState);
        }
        return convertView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(bottomSheet!=null)
            bottomSheet.dismissSheet();

    }

    private void initView()
    {
        convertView.findViewById(R.id.lay_records_list_summery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLocationHistory("You");

            }
        });
        convertView.findViewById(R.id.lay_bharat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationHistory("Bharat");
            }
        });
        convertView.findViewById(R.id.lay_roshan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLocationHistory("Roshan");

            }
        });
        convertView.findViewById(R.id.lay_bitu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationHistory("Bitu");

            }
        });
        convertView.findViewById(R.id.lay_location_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu=menu;
        showMenuForHomeDefault();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                onBackPressedConsumed();

            }
            break;
            case R.id.action_add_person:
            {
                PeopleListFragment peopleListFragment = new PeopleListFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,peopleListFragment);
                fragmentTransaction.addToBackStack("Home");
                fragmentTransaction.commit();
            }
            break;
            case R.id.action_notification:
            {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,notificationFragment);
                fragmentTransaction.addToBackStack("Home");
                fragmentTransaction.commit();
            }
            break;
            case R.id.action_security:
            {
                bottomSheet.showWithSheetView(LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, bottomSheet, false));
                bottomSheet.findViewById(R.id.lay_police_stations).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheet.dismissSheet();
                    }
                });
                bottomSheet.findViewById(R.id.lay_fake_caller).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        bottomSheet.dismissSheet();

                        /*Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity().getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
                        final Ringtone defaultRingtone = RingtoneManager.getRingtone(getActivity(), defaultRintoneUri);
                        defaultRingtone.play();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                defaultRingtone.stop();
                            }
                        },3*1000);*/

                        FakeCaller fakeCaller = new FakeCaller();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,fakeCaller);
                        fragmentTransaction.addToBackStack("Home");
                        fragmentTransaction.commit();

                    }
                });
            }
            break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeMap(Bundle savedInstanceState) {
        try {
            //Obtaining our MapView defined in xml
            final MapView mapView = (MapView) convertView.findViewById(R.id.mapView);
            if (savedInstanceState == null) {
                System.out.println("savedInstanceState null");
            }
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            MapsInitializer.initialize(getActivity());
            mapView.getMapAsync(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(28.2456, 75.36589);
        googleMap.addMarker(new MarkerOptions()
                .title("You")
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_512)));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    private void showLocationHistory(String person_name)
    {
        if(!is_detail_view_showing) {

            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            convertView.findViewById(R.id.lay_location_history).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.lay_all_friends_locations_1).setVisibility(View.GONE);
            convertView.findViewById(R.id.lay_all_friends_locations_2).setVisibility(View.GONE);

            showMenuForLocationDetails(person_name);

            is_detail_view_showing = true;
        }
    }
    public boolean onBackPressedConsumed()
    {
        if(is_detail_view_showing)
        {

            convertView.findViewById(R.id.lay_location_history).setVisibility(View.GONE);
            convertView.findViewById(R.id.lay_all_friends_locations_1).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.lay_all_friends_locations_2).setVisibility(View.VISIBLE);

            showMenuForHomeDefault();

            is_detail_view_showing = false;
            return true;
        }else if(slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
        {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            return true;
        }
        return false;
    }

    private void showMenuForHomeDefault()
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Track Me");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        menu.findItem(R.id.action_add_person).setVisible(true);
        menu.findItem(R.id.action_notification).setVisible(true);
        menu.findItem(R.id.action_security).setVisible(true);
        menu.findItem(R.id.action_remove).setVisible(false);
        menu.findItem(R.id.action_location_off).setVisible(false);
    }
    private void showMenuForLocationDetails(String person_name)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(person_name);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menu.findItem(R.id.action_add_person).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_security).setVisible(false);
        menu.findItem(R.id.action_remove).setVisible(true);
        menu.findItem(R.id.action_location_off).setVisible(true);
    }
}
