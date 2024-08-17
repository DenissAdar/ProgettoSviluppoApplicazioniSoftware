package catering.businesslogic.task;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.user.User;
import catering.businesslogic.user.UserManager;

import java.util.ArrayList;

public class SheetManager {
    private ArrayList<SheetEventReceiver> eventReceivers;
    private SummarySheet curSheet;
    private ArrayList<SummarySheet> sheets;
    private User user = CatERing.getInstance().getUserManager().getCurrentUser();

    // Operazione 1
    public SummarySheet chooseSheetFile(int sheetId) throws UseCaseLogicException {
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId))
            return getSheet(sheetId);
        throw new UseCaseLogicException();
    }

    // Operazione 1.a.1
    public SummarySheet createSummarySheet(int sheetId) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        SummarySheet newSheet = new SummarySheet(sheetId);
        sheets.add(newSheet);
        return newSheet;
    }

    // sheetMgr.isPresent(curSheet)
    public boolean isPresent(int sheetId){

        for (SummarySheet s: sheets) {
            if(s.getId() == sheetId)
                return true;

        }
        return false;
    }

    public SummarySheet getSheet(int sheetId){

        for (SummarySheet s: sheets) {
            if(s.getId() == sheetId)
                return s;
        }
    return null;
    }

    // Operazione 1.b.1
    public SummarySheet restoreSummarySheet(int sheetId) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)) {
            curSheet = getSheet(sheetId);
            curSheet.restoreSheet();
            return curSheet;

        }
        throw new UseCaseLogicException();

    }

    // Operazione 2
    public void definePreparation(String name, int sheetId) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            Preparation prep = curSheet.addPreparation(name);
            // TODO notify
        }
        throw new UseCaseLogicException();
    }

    // Operazione 2.a.1
    public void deletePreparation(Preparation prep, int sheetId) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            curSheet.removePreparation(prep);
            // TODO notify
        }
        throw new UseCaseLogicException();
    }

    // Operazione 3
    public void defineRecipe(String name, int sheetId) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            Recipe recipe = curSheet.addRecipe(name);
            // TODO notify
        }
        throw new UseCaseLogicException();
    }
    // Operazione 3.a.1
    public void deleteRecipe(Recipe recipe, int sheetId) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            curSheet.removeRecipe(recipe);
            // TODO notify
        }
        throw new UseCaseLogicException();
    }

    // Operazione 4

    // Operazione 5
    public void defineTask(int sheetId, String title, ArrayList<Preparation> preparations, int portions, User cook, int time) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)) {
            curSheet = getSheet(sheetId);
            Task tsk = curSheet.addTask(title, preparations, portions, cook, time);
            // TODO notify
        }
    }

    // Operazione 5.a.1
    public void deleteTask(int sheetId, Task tsk) throws UseCaseLogicException{
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            curSheet.removeTask(tsk);
            // TODO notify
        }
        throw new UseCaseLogicException();
    }

    // TODO aggiungere modify task
}
