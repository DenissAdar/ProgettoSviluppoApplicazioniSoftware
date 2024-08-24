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
    //TODO: SALVARE BENE la preparazione in catering.preparation e catering.sheetpreparations e anche catering.taskpreparation
    public static void saveAllNewPreparations(int id, ArrayList<Preparation> preparations) {
        String Insert = "INSERT INTO catering.preparation (name) VALUES (?);";
        int[] result = PersistenceManager.executeBatchUpdate(Insert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setString(1, PersistenceManager.escapeString(preparations.get(batchCount).name));
            }
            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    //sh.id = rs.getInt(1);
                }
            }
        });
        Insert = "INSERT INTO catering.sheetpreparation (sheet_id, preparation_name) VALUES (?,?);";
        int[] result1 = PersistenceManager.executeBatchUpdate(Insert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1,id);
                ps.setString(2, PersistenceManager.escapeString(preparations.get(batchCount).name));
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {

            }

        });
    }
}
