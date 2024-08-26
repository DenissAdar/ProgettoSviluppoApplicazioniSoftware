package catering.businesslogic.task;

import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Preparation {
    private String name;
    private boolean terminated;

    public Preparation(String name) {
        this.name = name;
        terminated = false;
    }




    public String getName() {
        return name;
    }

    //Persistenza


    public static void savePreparation(int sheetId, String prep_name){
        String insert = "INSERT INTO catering.preparation (name) VALUES ('"+prep_name+"');";
        PersistenceManager.executeUpdate(insert);
        insert = "INSERT INTO catering.sheetpreparations (sheet_id, preparation_name) VALUES ("+sheetId+",'"+prep_name+"');";
        PersistenceManager.executeUpdate(insert);
    }
    public static void removePreparation(int id, String name) {
        String insert = "DELETE FROM catering.preparation WHERE name = '"+name+"';";
        PersistenceManager.executeUpdate(insert);
        insert = "DELETE FROM catering.sheetpreparations WHERE preparation_name = '"+name+"' AND sheet_id = "+id+";";
        PersistenceManager.executeUpdate(insert);
    }

}
