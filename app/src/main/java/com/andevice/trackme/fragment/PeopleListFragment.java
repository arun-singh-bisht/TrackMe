package com.andevice.trackme.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

public class PeopleListFragment extends Fragment{

    View convertView;
    Menu menu;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_people_list, null, false);

            //Setup Action bar
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Peoples");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            //
            viewPager = (ViewPager) convertView.findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) convertView.findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        PeoplePageFragment peoplePageFragment_all = new PeoplePageFragment();
        peoplePageFragment_all.setType(People.TYPE_ALL);
        adapter.addFrag(peoplePageFragment_all, "All");

        PeoplePageFragment peoplePageFragment_following = new PeoplePageFragment();
        peoplePageFragment_following.setType(People.TYPE_FOLLOWING);
        adapter.addFrag(peoplePageFragment_following, "Following");

        PeoplePageFragment peoplePageFragment_accept = new PeoplePageFragment();
        peoplePageFragment_accept.setType(People.TYPE_ACCEPT);
        adapter.addFrag(peoplePageFragment_accept, "Request");

        PeoplePageFragment peoplePageFragment_pending = new PeoplePageFragment();
        peoplePageFragment_pending.setType(People.TYPE_PENDING);
        adapter.addFrag(peoplePageFragment_pending, "Pending");

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
