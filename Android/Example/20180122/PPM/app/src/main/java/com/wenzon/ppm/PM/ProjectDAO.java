package com.wenzon.ppm.PM;

import java.util.ArrayList;

/**
 * Created by Wenzon on 2018/1/20.
 */

public interface ProjectDAO {
    public boolean add(Project p);
    public ArrayList<Project> getList();
    public Project getProject(int id);
    public boolean update(Project p);
    public boolean delete(int id);
}
