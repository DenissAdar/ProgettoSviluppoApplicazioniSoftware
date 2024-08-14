package catering.businesslogic.task;

import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.user.User;

public class Task {
    private String title;
    private int portions;
    private Recipe recipe;
    private Preparation preparation;
    private User chef;
    private boolean terminated;
    private int time;
}
