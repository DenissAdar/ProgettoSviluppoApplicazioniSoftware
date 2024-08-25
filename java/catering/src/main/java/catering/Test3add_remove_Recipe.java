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
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(14);
        System.out.println(s.toString());

        Recipe c,cp,l,m;
        c = CatERing.getInstance().getSheetManager().getCurrentSheet().addRecipe("Carbonara");
        cp = CatERing.getInstance().getSheetManager().getCurrentSheet().addRecipe("Cacio e Pepe");
        l = CatERing.getInstance().getSheetManager().getCurrentSheet().addRecipe("Lasagne");
        m = CatERing.getInstance().getSheetManager().getCurrentSheet().addRecipe("Milanese");

        System.out.println("Foglio Riepilogativo con aggiunte Ricette:");
        System.out.println(s.toString());

        CatERing.getInstance().getSheetManager().getCurrentSheet().removeRecipe(c);
        CatERing.getInstance().getSheetManager().getCurrentSheet().removeRecipe(cp);
        CatERing.getInstance().getSheetManager().getCurrentSheet().removeRecipe(l);
        CatERing.getInstance().getSheetManager().getCurrentSheet().removeRecipe(m);
        System.out.println("Foglio Riepilogativo con Ricette rimosse:");
        System.out.println(s.toString());
    }
}
