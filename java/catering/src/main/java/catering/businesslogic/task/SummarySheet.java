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

    public ArrayList<Preparation> getPreparations() {
        return preparations;
    }

    public int getId() {
        return id;
    }

    //Ripristino del Foglio Riepilogativo su cui si richiama (1.b.1)
    public void restoreSheet() {
        this.tasks.clear();
        this.recipes.clear();
        this.preparations.clear();
    }

    // TODO Riguardare
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
    public Task addTask(String title, ArrayList<Preparation> preparations, int portions, int time) {
        Task tsk = new Task(title,preparations,portions, time);
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
    //----------------------------------------------
   //TODO Farlo bene bene
    public String toString(){
        String prep="";
        String tas = "";
        String rec ="";
        for(Preparation p:preparations){
            prep = prep + " - " +p.getName();
        }
        for(Task t: tasks){
            tas = tas + t.getTitle();
        }
        for(Recipe r: recipes){
            rec = rec + " - " + r.getName();
        }
        return "Id del Foglio Riepilogativo"+this.getId()
                + "\n Numero di Preparazioni: "+ this.preparations.size()
                + "\n Elenco Preparazioni: " + prep
                + "\n Numero di Ricette: "+ this.recipes.size()
                + "\n Elenco delle Ricette:  " + rec
                + "\n Numero dei Compiti: " + this.tasks.size()
                + "\n Elenco dei Compiti: "+ tas;
    }


    //Methods for Persistence--------------------------------------------------------

    public static SummarySheet loadSheet(int id){
        SummarySheet sheet = new SummarySheet(0);
        String sheetQuery = "SELECT * FROM summarysheet WHERE id="+id;
        PersistenceManager.executeQuery(sheetQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
               sheet.id= rs.getInt("id");
            }
        });
        String prepQuery = "SELECT * FROM sheetpreparations WHERE sheetpreparations.sheet_id="+sheet.id;
        PersistenceManager.executeQuery(prepQuery,new ResultHandler(){
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int prep_id = rs.getInt("id");
                String prep_name = rs.getString("preparation_name");
                sheet.addPreparation(prep_name);
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
        if (result[0] > 0) { // menu effettivamente inserito
            // salva tasks, preparations e recipes nel SumSheet
            //TODO featuresToDB(m); NB: Chiamalo diversamente questo è il nome del Menu

            /* salva le sezioni
            if (m.sections.size() > 0) {
                Section.saveAllNewSections(m.id, m.sections);
            }

            // salva le voci libere
            if (m.freeItems.size() > 0) {
                MenuItem.saveAllNewItems(m.id, 0, m.freeItems);
            }
            loadedMenus.put(m.id, m);*/
            //todo Da Capire come fare e cosa mettere come parametri nei metodi!!!
            if(sh.preparations.size()>0){Preparation.saveAllNewPreparations(sh.id,sh.preparations);}
            if(sh.recipes.size()>0){Recipe.saveAllNewRecipes(sh.id,sh.recipes);}
            if(sh.tasks.size()>0){Task.saveAllNewTasks(sh.id, sh.tasks);}
        }
    }

}
