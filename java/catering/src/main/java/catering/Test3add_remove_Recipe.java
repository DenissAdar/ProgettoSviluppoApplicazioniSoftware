package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SummarySheet;

public class Test3add_remove_Recipe {
    public static void main(String[] args) throws UseCaseLogicException {


        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 3 e 3.a.1: add/Remove Recipe");
        SummarySheet s = CatERing.getInstance().getSheetManager().chooseSheetFile(16);
        System.out.println(s.toString());

        Recipe c,cp,l,m;
        c = CatERing.getInstance().getSheetManager().defineRecipe("Carbonara",s.getId());
        c = CatERing.getInstance().getSheetManager().defineRecipe("Carbonara",s.getId());

        cp = CatERing.getInstance().getSheetManager().defineRecipe("Cacio e Pepe",s.getId());
        l = CatERing.getInstance().getSheetManager().defineRecipe("Lasagne",s.getId());
        m = CatERing.getInstance().getSheetManager().defineRecipe("Milanese",s.getId());

        System.out.println("Foglio Riepilogativo con aggiunte Ricette:");
        System.out.println(s.toString());

       /* CatERing.getInstance().getSheetManager().deleteRecipe(c,s.getId());
        CatERing.getInstance().getSheetManager().deleteRecipe(cp,s.getId());
        CatERing.getInstance().getSheetManager().deleteRecipe(l,s.getId());
        CatERing.getInstance().getSheetManager().deleteRecipe(m,s.getId());
        System.out.println("Foglio Riepilogativo con Ricette rimosse:");
        System.out.println(s.toString());*/
    }
}
