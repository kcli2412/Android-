package com.wenzon.ppm.PM;

import android.media.Image;
import android.provider.ContactsContract;

/**
 * Created by Wenzon on 2018/1/20.
 */

public class Project {
    public int id;
    public String name;

    public String schedule;
    public String quality;
    public String budget;
    public String remark;

    public Project(int id, String name,  String schedule,String quality,String budget,String remark)
    {
        this.id = id;
        this.name = name;

        this.schedule = schedule;
        this.quality = quality;
        this.budget = budget;
        this.remark= remark;
    }
}
