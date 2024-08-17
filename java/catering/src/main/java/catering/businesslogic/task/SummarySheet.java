package catering.businesslogic.task;
import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
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
        this.tasks = new ArrayList<Task>();
        this.preparations = new ArrayList<Preparation>();
        this.recipes = new ArrayList<Recipe>();
    }


    public int getId() {
        return id;
    }

    public void restoreSheet() {
        this.tasks.clear();
        this.recipes.clear();
        this.preparations.clear();
    }

    // TODO Riguardare
    public Preparation addPreparation(String name) {
        Preparation prep = new Preparation(name);
        preparations.add(prep);
        return prep;

    }

    public Recipe addRecipe(String name) {
        Recipe recipe = new Recipe(name);
        recipes.add(recipe);
        return recipe;

    }
    public Task addTask(String title, ArrayList<Preparation> preparations, int portions, User cook, int time) {
        Task tsk = new Task(title,preparations,portions,cook,time);
        tasks.add(tsk);
        return tsk;

    }

    public void removePreparation(Preparation prep) throws UseCaseLogicException {
        if(preparations.contains(prep))
            preparations.remove(prep);
        else throw new UseCaseLogicException();
    }

    public void removeRecipe(Recipe recipe) throws UseCaseLogicException {
        if(recipes.contains(recipe))
            recipes.remove(recipe);
        else throw new UseCaseLogicException();
    }
    public void removeTask(Task tsk) throws UseCaseLogicException {
        if(tasks.contains(tsk))
            tasks.remove(tsk);
        else throw new UseCaseLogicException();
    }

}
