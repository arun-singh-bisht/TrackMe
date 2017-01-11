package com.andevice.trackme.fragment;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andevice.trackme.R;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

/**
 * Created by arun.singh on 1/5/2017.
 */

public class FakeCaller extends Fragment {

    View convertView;
    Ringtone defaultRingtone;
    Timer timer;
    TimerTask timerTask;
    TextView textView_seconds;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_fake_caller, null, false);

            //Setup Action bar
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            //
            Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity().getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
            defaultRingtone = RingtoneManager.getRingtone(getActivity(), defaultRintoneUri);
            initViews();

        }
        return convertView;
    }


    private void initViews()
    {
        textView_seconds = (TextView) convertView.findViewById(R.id.txt_seconds);
        final ImageView imageView_play_plause = (ImageView) convertView.findViewById(R.id.img_play_pause);
        imageView_play_plause.setTag("running");


        imageView_play_plause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String)imageView_play_plause.getTag();
                if(tag.equalsIgnoreCase("running"))
                {
                    timer.cancel();
                    defaultRingtone.stop();
                    imageView_play_plause.setTag("stoped");
                    imageView_play_plause.setImageResource(R.mipmap.ic_play);
                    textView_seconds.setText("10");

                }else if(tag.equalsIgnoreCase("stoped"))
                {
                    scheduleTimer();
                    imageView_play_plause.setTag("running");
                    imageView_play_plause.setImageResource(R.mipmap.ic_paus);

                }

            }
        });

        scheduleTimer();

    }

    private void scheduleTimer()
    {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String sec = textView_seconds.getText()+"";
                        int se = Integer.parseInt(sec);
                        textView_seconds.setText((se -1)+"");
                        if(se == 1) {
                            timer.cancel();
                            defaultRingtone.play();

                        }
                    }
                });

            }
        };
        timer.schedule(timerTask,0,1000);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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

    @Override
    public void onStop() {
        super.onStop();
        if(timer !=null)
            timer.cancel();
        if(timerTask != null)
            timerTask.cancel();

    }
}
