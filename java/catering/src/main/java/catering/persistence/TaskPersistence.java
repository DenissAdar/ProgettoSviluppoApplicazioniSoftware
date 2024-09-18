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
        SummarySheet.deleteSheet(curSheet);
    }

    @Override
    public void updatePreparationAdded(SummarySheet curSheet, Preparation prep) {
            Preparation.savePreparation(curSheet.getId(),prep.getName());
    }

    @Override
    public void updatePreparationRemoved(SummarySheet curSheet, Preparation prep) {
            Preparation.removePreparation(curSheet.getId(),prep.getName());
    }

    @Override
    public void updateRecipeAdded(SummarySheet curSheet, Recipe rec) {
            Recipe.saveRecipe(curSheet.getId(), rec.getName());
    }

    @Override
    public void updateRecipeRemoved(SummarySheet curSheet, Recipe rec) {
        Recipe.removeRecipe(curSheet.getId(), rec.getName());
    }

    @Override
    public void updateTaskOrderChanged(SummarySheet curSheet) {

    }

    @Override
    public void updateTaskAdded(SummarySheet curSheet, Task tsk) {
            Task.saveTask(curSheet.getId(),tsk.getTitle(),tsk.getPreparations(),tsk.getPortions(),tsk.getTime(),tsk.getCook());
    }

    @Override
    public void updateTaskRemoved(SummarySheet curSheet, Task tsk) {
        Task.removeTask(curSheet.getId(),tsk.getTitle(),tsk.getPreparations(),tsk.getPortions(),tsk.getTime(),tsk.getCook());
    }
}
