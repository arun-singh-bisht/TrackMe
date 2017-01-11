package com.andevice.trackme.model;

/**
 * Created by arun.singh on 1/4/2017.
 */

public class People {
    public final static String TYPE_ALL ="All";
    public final static String TYPE_FOLLOWING ="Following";
    public final static String TYPE_ACCEPT ="Accept";
    public final static String TYPE_PENDING ="Pending";

    public String name;
    public String status;

    public People(String name,String status)
    {
        this.name = name;
        this.status = status;
    }

}
