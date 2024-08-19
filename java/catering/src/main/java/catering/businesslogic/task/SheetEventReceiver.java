package catering.businesslogic.task;

import catering.businesslogic.recipe.Recipe;

public interface SheetEventReceiver {
    void updateSummarySheetCreated(SummarySheet newSheet);


    void updateSheetRestored(SummarySheet curSheet);

    void updatePreparationAdded(SummarySheet curSheet, Preparation prep);

    void updatePreparationRemoved(SummarySheet curSheet, Preparation prep);

    void updateRecipeAdded(SummarySheet curSheet, Recipe rec);

    void updateRecipeRemoved(SummarySheet curSheet, Recipe rec);

    void updateTaskOrderChanged(SummarySheet curSheet);

    void updateTaskAdded(SummarySheet curSheet, Task tsk);

    void updateTaskRemoved(SummarySheet curSheet, Task tsk);
}
