/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dd2018011702;

/**
 *
 * @author Student
 */
public class DD2018011702 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 80));
        
        for (Student s : dao.getList())
        {
            System.out.println(s.id + "," + s.name + "," + s.score);
        }
        
    }
    
}
