package catering.businesslogic.task;
import catering.businesslogic.CatERing;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.user.User;

import java.util.ArrayList;

public class SummarySheet {
    private int id;
    // private EventInfo event;
    private ArrayList<Task> tasks;
    private ArrayList<Recipe> recipes;
    private ArrayList<Preparation> preparations;

    public SummarySheet(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }
}
