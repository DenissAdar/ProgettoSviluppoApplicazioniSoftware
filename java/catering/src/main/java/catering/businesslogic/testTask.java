package catering.businesslogic;

import catering.businesslogic.menu.MenuManager;
import catering.businesslogic.recipe.RecipeManager;
import catering.businesslogic.task.SheetManager;
import catering.businesslogic.user.UserManager;

public class testTask {
    private static testTask singleInstance;

    public static testTask getInstance(){
        if(singleInstance == null){
            singleInstance = new testTask();
        }
        return singleInstance;
    }
    private SheetManager sheetMgr;
    private MenuManager menuMgr;
    private UserManager userMgr;
    private RecipeManager recipeMgr;


    private testTask(){
        sheetMgr = new SheetManager();
        menuMgr = new MenuManager();
        userMgr = new UserManager();
        recipeMgr = new RecipeManager();
    }


    public SheetManager getSheetManager() {
        return sheetMgr;
    }

    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }
}
