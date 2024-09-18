package catering.businesslogic.task;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.MenuItem;
import catering.businesslogic.menu.Section;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummarySheet {
    private static int id;
    // private EventInfo event;
    private ArrayList<Task> tasks;
    private ArrayList<Recipe> recipes;
    private ArrayList<Preparation> preparations;

    //Creazione di un nuovo Foglio Riepilogativo(op 1.a.1)
    public SummarySheet(int id) {
        this.id = id;
        this.tasks = new ArrayList<Task>();
        this.preparations = new ArrayList<Preparation>();
        this.recipes = new ArrayList<Recipe>();
    }
    public SummarySheet() {

        this.tasks = new ArrayList<Task>();
        this.preparations = new ArrayList<Preparation>();
        this.recipes = new ArrayList<Recipe>();
    }





    //Ripristino del Foglio Riepilogativo su cui si richiama (1.b.1)
    public void restoreSheet() {
        this.tasks.clear();
        this.preparations.clear();
        this.recipes.clear();
    }

    //OP 2
    public Preparation addPreparation(String name) {
        Preparation prep = new Preparation(name);
        preparations.add(prep);
        return prep;

    }
    //OP 3
    public Recipe addRecipe(String name) {
        Recipe recipe = new Recipe(name);
        recipes.add(recipe);
        return recipe;

    }
    //OP 5
    public Task addTask(String title, ArrayList<Preparation> preparations, int portions, int time,String cook) {
        Task tsk = new Task(title,preparations,portions, time,cook);
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

    //   Ordinamento OP4, visto Menu
    public void changeTaskOrder(Task task, int position){
        tasks.remove(task);
        tasks.add(position, task);
    }
    public int getTaskPosition(Task task) {
        return this.tasks.indexOf(task);
    }
    public int getTaskCount() {
        return tasks.size();
    }


    public int getId() {
        return id;
    }
    private ArrayList<Recipe> getRecipes() {return recipes;}
    private ArrayList<Task> getTasks() {return tasks;}
    public ArrayList<Preparation> getPreparations() {
        return preparations;
    }
    //----------------------------------------------

    public String toString(){
        String prep="";
        String tas = "";
        String rec ="";
        for(Preparation p:preparations){
            prep = prep + " - " +p.getName();
        }
        for(Task t: tasks){
            String preps_names ="";
            for(Preparation preps: t.getPreparations()){
                 preps_names = preps_names +" / "+ preps.getName();
            }
            tas = tas +" -\n-- " + t.getTitle()+
                    ": \n--- Numero delle Preparazioni all'interno del Task:" + t.getPreparations().size() +
                    "\n----Elenco Preparazioni :" + preps_names +
                    "\n--- Numero delle porzioni: " +t.getPortions() + " e tempo necessario per la realizzazione del Compito: " + t.getTime()+"\n"+"--- Cuoco Responsabile di questo Compito: "+t.getCook()+"\n";
        }
        for(Recipe r: recipes){
            rec = rec + " - " + r.getName();
        }
        return "Id del Foglio Riepilogativo: "+this.getId()
                + "\n Numero di Preparazioni: "+ this.preparations.size()
                + "\n Elenco Preparazioni: " + prep
                + "\n Numero di Ricette: "+ this.recipes.size()
                + "\n Elenco delle Ricette:  " + rec
                + "\n Numero dei Compiti: " + this.tasks.size()
                + "\n Elenco dei Compiti: "+ tas + "\n--------------------------------";
    }


    //Methods for Persistence--------------------------------------------------------

    public static SummarySheet loadSheet(int id){
        SummarySheet sheet = new SummarySheet(0);
        String sheetQuery = "SELECT * FROM catering.summarysheet WHERE id="+id;
        PersistenceManager.executeQuery(sheetQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
               sheet.id= rs.getInt("id");
            }
        });
        String prepQuery = "SELECT * FROM catering.sheetpreparations WHERE sheet_id="+sheet.id;
        ArrayList<Preparation> p = new ArrayList<>();
        PersistenceManager.executeQuery(prepQuery,new ResultHandler(){
            @Override
            public void handle(ResultSet rs) throws SQLException {

                String prep_name = rs.getString("preparation_name");
                p.add(new Preparation(prep_name));
                sheet.addPreparation(prep_name);
            }
        });

        String recQuery = "SELECT * FROM catering.sheetrecipes WHERE sheet_id="+sheet.id;
        ArrayList<Recipe> r = new ArrayList<>();
        PersistenceManager.executeQuery(recQuery,new ResultHandler(){
            @Override
            public void handle(ResultSet rs) throws SQLException {

                String rec_name = rs.getString("recipe_name");
                r.add(new Recipe(rec_name));
                sheet.addRecipe(rec_name);
            }
        });



        String taskQuery = "SELECT * FROM catering.sheettasks WHERE sheet_id="+sheet.id;
        PersistenceManager.executeQuery(taskQuery,new ResultHandler(){
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String task_title = rs.getString("task_title");

                String q = "SELECT * FROM catering.task WHERE title='"+task_title+"'";
                ArrayList<Task> t = new ArrayList<>();
                PersistenceManager.executeQuery(q,new ResultHandler(){

                            @Override
                            public void handle(ResultSet rs) throws SQLException {
                                int portions = rs.getInt("portions");
                                int time = rs.getInt("time");
                                String cook = rs.getString("cook");

                                String qt = "SELECT * FROM catering.task_preparations WHERE task_title='"+task_title+"'";
                                ArrayList<Preparation> preps = new ArrayList<>();
                                PersistenceManager.executeQuery(qt,new ResultHandler(){

                                    @Override
                                    public void handle(ResultSet rs) throws SQLException {
                                        String prep_name = rs.getString("preparation_name");
                                        Preparation p = new Preparation(prep_name);
                                        preps.add(p);
                                    }

                                });
                                t.add(new Task(task_title,preps, portions, time,cook));
                                sheet.addTask(task_title,preps, portions, time,cook);
                            }
                        });
            }
        });


        return sheet;
    }
    public static void saveNewSheet(SummarySheet sh){
            String sheetInsert = "INSERT INTO catering.Summarysheet (id) VALUES (?);";
            int[] result = PersistenceManager.executeBatchUpdate(sheetInsert, 1, new BatchUpdateHandler() {
                @Override
                public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                    ps.setInt(1, sh.getId());
                }
                @Override
                public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                    if (count == 0) {
                    //sh.id = rs.getInt(1);
                    }
            }
        });
        if (result[0] > 0) {}
    }

    public static void deleteSheet(SummarySheet s){
        String insert;
        for(Preparation p: s.getPreparations()){
            insert = "DELETE FROM catering.preparation WHERE name = '"+p.getName()+"';";
            PersistenceManager.executeUpdate(insert);
        }
        insert = "DELETE FROM catering.sheetpreparations WHERE sheet_id = '"+s.getId()+"';";
        PersistenceManager.executeUpdate(insert);



        for(Recipe r: s.getRecipes()){
            insert = "DELETE FROM catering.recipes WHERE name = '"+r.getName()+"';";

            PersistenceManager.executeUpdate(insert);
        }
        insert = "DELETE FROM catering.sheetrecipes WHERE sheet_id = '"+s.getId()+"';";
        PersistenceManager.executeUpdate(insert);



        for(Task t : s.getTasks()){
            insert = "DELETE FROM catering.task WHERE title = '"+t.getTitle()+"';";
            PersistenceManager.executeUpdate(insert);
            insert = "DELETE FROM catering.task_preparations WHERE task_title = '"+t.getTitle()+"';";
            PersistenceManager.executeUpdate(insert);
        }
        insert = "DELETE FROM catering.sheettasks WHERE sheet_id = '"+s.getId()+"';";
        PersistenceManager.executeUpdate(insert);

    }




}
