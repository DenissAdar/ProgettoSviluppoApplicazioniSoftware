package catering.businesslogic.task;

import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

import java.util.ArrayList;

public class Task {
    private String title;
    private int portions;
    private ArrayList<Preparation> preparations;
    //private User cook;
    private boolean terminated;
    private int time;

    public Task(String title, ArrayList<Preparation> preparations, int portions, int time) {
        this.title = title;
        this.portions = portions;
        this.preparations = preparations;
        //this.cook = cook;
        this.terminated = false;
        this.time = time;
    }
    public String getTitle() {return title;}
    public int getPortions() {return portions;}
    public ArrayList<Preparation> getPreparations() {return preparations;}
    public boolean isTerminated() {return terminated;}
    public int getTime() {return time;}

    //Persistenza
    public static void saveTask(int sheetId, String task_title,ArrayList<Preparation> preps,int portions, int time){
        String insert = "INSERT INTO catering.task (title,portions,time) VALUES ('"+task_title+"' , "+portions+","+time+");";
        PersistenceManager.executeUpdate(insert);
        insert = "INSERT INTO catering.sheettasks (sheet_id, task_title) VALUES ("+sheetId+",'"+task_title+"');";
        PersistenceManager.executeUpdate(insert);
        for(Preparation p : preps){
            String prep_name = p.getName();
            insert = "INSERT INTO catering.task_preparations (task_title, preparation_name) VALUES('"+task_title+"','"+prep_name+"');";
            PersistenceManager.executeUpdate(insert);
        }

    }
    public static void removeTask(int sheetId, String task_title,ArrayList<Preparation> preps,int portions, int time) {
        String insert = "DELETE FROM catering.task WHERE title = '"+task_title+"';";
        PersistenceManager.executeUpdate(insert);
        insert = "DELETE FROM catering.sheettasks WHERE task_title = '"+task_title+"' AND sheet_id = "+sheetId+";";
        PersistenceManager.executeUpdate(insert);
        for(Preparation p : preps){
            String prep_name = p.getName();
            insert = "DELETE FROM catering.task_preparations WHERE task_title = '"+task_title+"'AND preparation_name = '"+prep_name+"' ;";
            PersistenceManager.executeUpdate(insert);
        }
    }


}
