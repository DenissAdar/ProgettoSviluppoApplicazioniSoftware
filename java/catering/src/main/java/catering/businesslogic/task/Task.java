package catering.businesslogic.task;

import catering.businesslogic.user.User;

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
}
