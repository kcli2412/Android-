package com.wenzon.ppm.PM;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Wenzon on 2018/1/20.
 */

public class ProjectFileDAO implements ProjectDAO {
    public ArrayList<Project> pmlist;
    Context context;
    public ProjectFileDAO(Context context)
    {
        this.context = context;
       pmlist = new ArrayList<>();
    }
    public boolean add(Project p)
    {
        pmlist.add(p);
        saveFile();
        return true;
    }
    private void saveFile()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(pmlist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void load()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            pmlist = gson.fromJson(str, new TypeToken<ArrayList<Project>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Project> getList()
    {
        load();
        return pmlist;
    }
    public Project getProject(int id)
    {
        load();
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
        load();
        for (Project t : pmlist)
        {
            if (t.id == p.id)
            {
                t.name = p.name;
                t.schedule = p.schedule;

                t.quality = p.quality;
                t.budget = p.budget;
                t.remark = p.remark;
                saveFile();
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id)
    {
        load();
        for (int i=0;i<pmlist.size();i++)
        {
            if (pmlist.get(i).id == id)
            {
                pmlist.remove(i);
                saveFile();
                return true;
            }
        }
        return false;
    }
}
