package catering.persistence;


import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SheetEventReceiver;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.task.Task;

public class TaskPersistence implements SheetEventReceiver {
    @Override
    public void updateSummarySheetCreated(SummarySheet newSheet) {
            SummarySheet.saveNewSheet(newSheet);
    }

    @Override
    public void updateSheetRestored(SummarySheet curSheet) {

    }

    @Override
    public void updatePreparationAdded(SummarySheet curSheet, Preparation prep) {

    }

    @Override
    public void updatePreparationRemoved(SummarySheet curSheet, Preparation prep) {

    }

    @Override
    public void updateRecipeAdded(SummarySheet curSheet, Recipe rec) {

    }

    @Override
    public void updateRecipeRemoved(SummarySheet curSheet, Recipe rec) {

    }

    @Override
    public void updateTaskOrderChanged(SummarySheet curSheet) {

    }

    @Override
    public void updateTaskAdded(SummarySheet curSheet, Task tsk) {

    }

    @Override
    public void updateTaskRemoved(SummarySheet curSheet, Task tsk) {

    }
}
