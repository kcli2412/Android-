package com.wenzon.ppm.PM;

import java.util.ArrayList;

/**
 * Created by Wenzon on 2018/1/20.
 */

public class ProjectMemoryDAO implements ProjectDAO {
    public ArrayList<Project> pmlist;
    public ProjectMemoryDAO()
    {
        pmlist = new ArrayList<>();
    }
    public boolean add(Project p)
    {
       pmlist.add(p);
        return true;
    }
    public ArrayList<Project> getList()
    {
        return pmlist;
    }
    public Project getProject(int id)
    {
        for (Project p : pmlist)
        {
            if (p.id == id)
            {
                return p;
            }
        }
        return null;
    }
    public boolean update(Project p)
    {
        for (Project t : pmlist)
        {
            if (t.id == p.id)
            {
                t.name = p.name;

                t.schedule = p.schedule;
                t.quality = p.quality;
                t.budget = p.budget;
                t.remark = p.remark;
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id)
    {
        for (int i=0;i<pmlist.size();i++)
        {
            if (pmlist.get(i).id == id)
            {
                pmlist.remove(i);
                return true;
            }
        }
        return false;
    }
}
