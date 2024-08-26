package catering.businesslogic.recipe;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Recipe {
    private static Map<Integer, Recipe> all = new HashMap<>();

    private int id;
    private String name;

    private Recipe() {

    }

    public Recipe(String name) {
        id = 0;
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ArrayList<Recipe> loadAllRecipes() {
        String query = "SELECT * FROM Recipes";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (all.containsKey(id)) {
                    Recipe rec = all.get(id);
                    rec.name = rs.getString("name");
                } else {
                    Recipe rec = new Recipe(rs.getString("name"));
                    rec.id = id;
                    all.put(rec.id, rec);
                }
            }
        });
        ArrayList<Recipe> ret = new ArrayList<Recipe>(all.values());
        Collections.sort(ret, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return (o1.getName().compareTo(o2.getName()));
            }
        });
        return ret;
    }

    public static ArrayList<Recipe> getAllRecipes() {
        return new ArrayList<Recipe>(all.values());
    }

    public static Recipe loadRecipeById(int id) {
        if (all.containsKey(id)) return all.get(id);
        Recipe rec = new Recipe();
        String query = "SELECT * FROM Recipes WHERE id = " + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                    rec.name = rs.getString("name");
                    rec.id = id;
                    all.put(rec.id, rec);
            }
        });
        return rec;
    }

    public static void saveRecipe(int sheetId, String name){
        String insert = "INSERT INTO catering.recipes (name) VALUES ('"+name+"');";
        PersistenceManager.executeUpdate(insert);
        insert = "INSERT INTO catering.sheetrecipes (sheet_id, recipe_name) VALUES ("+sheetId+",'"+name+"');";
        PersistenceManager.executeUpdate(insert);
    }
    public static void removeRecipe(int sheetid, String name) {
        String insert = "DELETE FROM catering.recipes WHERE name = '"+name+"';";
        PersistenceManager.executeUpdate(insert);
        insert = "DELETE FROM catering.sheetrecipes WHERE recipe_name = '"+name+"' AND sheet_id = "+sheetid+";";
        PersistenceManager.executeUpdate(insert);
    }


}
