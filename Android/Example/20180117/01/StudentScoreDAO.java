/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dd2018011702;

import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class StudentScoreDAO {
    public ArrayList<Student> mylist;
    public StudentScoreDAO()
    {
        mylist = new ArrayList<>();
    }
    public void add(Student s)
    {
        mylist.add(s);
    }
    public ArrayList<Student> getList()
    {
        return mylist;
    }
}
