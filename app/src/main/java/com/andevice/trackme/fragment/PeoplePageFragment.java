package com.andevice.trackme.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andevice.trackme.R;
import com.andevice.trackme.model.People;

import java.util.ArrayList;

/**
 * Created by arun.singh on 1/4/2017.
 */

public class PeoplePageFragment extends Fragment {

    View convertView;
    private RecyclerView recyclerView;
    private PeopleAdapter mAdapter;
    private ArrayList<People> peopleArrayList = new ArrayList<>();
    private String people_type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_people_page, null, false);

            initPeopleList();
        }
        return convertView;
    }

    private void initPeopleList() {
        recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prepareList();
        mAdapter = new PeopleAdapter(peopleArrayList);
        recyclerView.setAdapter(mAdapter);
    }

    private void prepareList() {

        if(people_type.equalsIgnoreCase(People.TYPE_ALL))
        {
            peopleArrayList.add(new People("Van Ronie", People.TYPE_ALL));
            peopleArrayList.add(new People("Mia Khalifi", People.TYPE_ALL));
            peopleArrayList.add(new People("Sachin Tendulkar", People.TYPE_ACCEPT));
            peopleArrayList.add(new People("Sourav Ganguli", People.TYPE_FOLLOWING));
            peopleArrayList.add(new People("Virat Kohli", People.TYPE_PENDING));
            peopleArrayList.add(new People("Robin Sharma", People.TYPE_FOLLOWING));
            peopleArrayList.add(new People("Mahesh Manjekar", People.TYPE_PENDING));
            peopleArrayList.add(new People("Rahul Dravid", People.TYPE_FOLLOWING));
            peopleArrayList.add(new People("Mohmad Kaif", People.TYPE_ACCEPT));
            peopleArrayList.add(new People("Pankaj Mishra", People.TYPE_PENDING));
        }else if(people_type.equalsIgnoreCase(People.TYPE_FOLLOWING))
        {
            peopleArrayList.add(new People("Sourav Ganguli", People.TYPE_FOLLOWING));
            peopleArrayList.add(new People("Robin Sharma", People.TYPE_FOLLOWING));
            peopleArrayList.add(new People("Rahul Dravid", People.TYPE_FOLLOWING));
        }else if(people_type.equalsIgnoreCase(People.TYPE_ACCEPT))
        {
            peopleArrayList.add(new People("Sachin Tendulkar", People.TYPE_ACCEPT));
            peopleArrayList.add(new People("Mohmad Kaif", People.TYPE_ACCEPT));
        }else if(people_type.equalsIgnoreCase(People.TYPE_PENDING))
        {
            peopleArrayList.add(new People("Mahesh Manjekar", People.TYPE_PENDING));
            peopleArrayList.add(new People("Pankaj Mishra", People.TYPE_PENDING));
        }

    }

    class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

        ArrayList<People> peopleArrayList;

        public PeopleAdapter(ArrayList<People> peopleArrayList) {
            this.peopleArrayList = peopleArrayList;
        }

        @Override
        public PeopleAdapter.PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.people_item, parent, false);
            return new PeopleAdapter.PeopleViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(PeopleViewHolder holder, int position) {
            People people = peopleArrayList.get(position);
            String s = people.name;
            holder.textView_name.setText(s + "");
            if (people.status.equalsIgnoreCase(People.TYPE_ALL)) {
                holder.textView_status.setText("Follow");
            } else if (people.status.equalsIgnoreCase(People.TYPE_FOLLOWING)) {
                holder.textView_status.setText("Following");
                if(people_type.equalsIgnoreCase(People.TYPE_ALL)) {
                    holder.textView_status.setBackgroundResource(R.drawable.rect_back_blue_fill);
                    holder.textView_status.setTextColor(Color.WHITE);
                }
            } else if (people.status.equalsIgnoreCase(People.TYPE_ACCEPT)) {
                holder.textView_status.setText("Accept");
            } else if (people.status.equalsIgnoreCase(People.TYPE_PENDING)) {
                holder.textView_status.setText("Pending...");
                holder.textView_status.setBackgroundResource(R.drawable.rect_back_gray);
                holder.textView_status.setTextColor(getActivity().getResources().getColor(R.color.color_gray_light_2));
            }
        }

        @Override
        public int getItemCount() {
            return peopleArrayList.size();
        }

        class PeopleViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView_name;
            TextView textView_status;

            public PeopleViewHolder(View view) {
                super(view);
                textView_name = (TextView) view.findViewById(R.id.txt_people_name);
                textView_status = (TextView) view.findViewById(R.id.txt_status);
            }
        }
    }

    public void setType(String people_type) {
        this.people_type = people_type;
    }

}
