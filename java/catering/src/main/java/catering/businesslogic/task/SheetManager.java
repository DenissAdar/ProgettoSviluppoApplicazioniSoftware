package catering.businesslogic.task;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.user.User;

import java.util.ArrayList;

public class SheetManager {
    private ArrayList<SheetEventReceiver> eventReceivers;
    private SummarySheet curSheet;
    private ArrayList<SummarySheet> sheets;
    private User user;


    public SheetManager() {
        eventReceivers = new ArrayList<>();
        sheets = new ArrayList<>();
        curSheet = null;
    }

    public User getUser() {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        return u;
    }

    // Operazione 1
    public SummarySheet chooseSheetFile(int id) throws UseCaseLogicException {
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();

        SummarySheet curSheet = new SummarySheet();
        curSheet =  SummarySheet.loadSheet(id);
        sheets.add(curSheet);
        return curSheet;
    }

    // Operazione 1.a.1
    public SummarySheet createSummarySheet(int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();

        SummarySheet newSheet = new SummarySheet(sheetId);
        sheets.add(newSheet);
        this.setCurrentSheet(newSheet);
        notifySheetAdded(newSheet);

        return newSheet;
    }

    //Pacchetto di Funzioni di controllo Condizioni Iniziali
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
    //---------------------------------------------------------------


    // Operazione 1.b.1
    public SummarySheet restoreSummarySheet(int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(!this.isPresent(sheetId)) {
            throw new UseCaseLogicException();


        }
        curSheet = getSheet(sheetId);


        notifySheetRestored(curSheet);
        curSheet.restoreSheet();
        return curSheet;

    }

    // Operazione 2 addPreparation
    public Preparation definePreparation(String name, int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(!this.isPresent(sheetId)){
            throw new UseCaseLogicException();
        }

        curSheet = getSheet(sheetId);
        Preparation prep = curSheet.addPreparation(name);


        notifyPreparationAdded(curSheet, prep);
        return prep;
    }

    // Operazione 2.a.1 DeletePreparation
    public void deletePreparation(Preparation prep, int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(!this.isPresent(sheetId)){
            throw new UseCaseLogicException();
        }
        curSheet = getSheet(sheetId);
        curSheet.removePreparation(prep);

        notifyPreparationRemoved(curSheet, prep);

    }

    // Operazione 3  addRecipe
    public Recipe defineRecipe(String name, int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            Recipe recipe = curSheet.addRecipe(name);

            notifyRecipeAdded(curSheet, recipe);
            return recipe;
        }
        throw new UseCaseLogicException();
    }
    // Operazione 3.a.1   deleteRecipe
    public void deleteRecipe(Recipe recipe, int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId)){
            curSheet = getSheet(sheetId);
            curSheet.removeRecipe(recipe);

            notifyRecipeRemoved(curSheet, recipe);
        }
        else throw new UseCaseLogicException();
    }

    // Operazione 4 changeTaskOrder
    public void changeTaskOrder(Task task, int position,int sheetId) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(!this.isPresent(sheetId)){
            throw new UseCaseLogicException();
        }
        curSheet = getSheet(sheetId);
        if(curSheet.getTaskPosition(task)>=0) {
            if (position < 0 || position >= curSheet.getTaskCount()) throw new IllegalArgumentException();
            else {
                this.curSheet.changeTaskOrder(task, position);
                notifyTaskOrderChanged(curSheet);
            }
        }
        else throw new UseCaseLogicException();
    }

    // Operazione 5   assignTask
    public Task defineTask(int sheetId, String title, ArrayList<Preparation> preparations, int portions, int time, String cook) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(!this.isPresent(sheetId)) {
            throw new UseCaseLogicException();
        }
        curSheet = getSheet(sheetId);
        Task tsk = curSheet.addTask(title, preparations, portions,  time, cook);


        notifyTaskAdded(curSheet, tsk);
        return tsk;
    }

    // Operazione 5.a.1   removeTask
    public void deleteTask(int sheetId, Task tsk) throws UseCaseLogicException{
        user = getUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(!this.isPresent(sheetId)){
            throw new UseCaseLogicException();
        }
        curSheet = getSheet(sheetId);
        curSheet.removeTask(tsk);

        notifyTaskRemoved(curSheet, tsk);

    }

    public void setCurrentSheet(SummarySheet s) {
        this.curSheet = s;
    }

    public SummarySheet getCurrentSheet() {
        return this.curSheet;
    }



    //Notify------------------------------------------


    private void notifySheetAdded(SummarySheet newSheet){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(newSheet);
        }
    }
    private void notifySheetRestored(SummarySheet curSheet){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateSheetRestored(curSheet);
        }
    }
    private void notifyPreparationAdded(SummarySheet curSheet, Preparation prep){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updatePreparationAdded(curSheet, prep);
        }
    }
    private void notifyPreparationRemoved(SummarySheet curSheet, Preparation prep){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updatePreparationRemoved(curSheet, prep);
        }
    }
    private void notifyRecipeAdded(SummarySheet curSheet, Recipe rec){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateRecipeAdded(curSheet, rec);
        }
    }
    private void notifyRecipeRemoved(SummarySheet curSheet, Recipe rec){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateRecipeRemoved(curSheet, rec);
        }
    }
    private void notifyTaskOrderChanged(SummarySheet curSheet){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateTaskOrderChanged(curSheet);
        }
    }
    private void notifyTaskAdded(SummarySheet curSheet, Task tsk){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateTaskAdded(curSheet, tsk);
        }
    }
    private void notifyTaskRemoved(SummarySheet curSheet, Task tsk){
        for (SheetEventReceiver er : this.eventReceivers) {
            er.updateTaskRemoved(curSheet, tsk);
        }
    }




    public void addEventReceiver(SheetEventReceiver rec) {
        this.eventReceivers.add(rec);
    }
}
