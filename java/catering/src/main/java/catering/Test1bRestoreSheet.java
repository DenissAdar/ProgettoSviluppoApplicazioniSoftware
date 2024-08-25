package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SummarySheet;

import java.util.ArrayList;

public class Test1bRestoreSheet {
    public static void main(String[] args) throws UseCaseLogicException {
        ArrayList<Preparation> ap = new ArrayList<>();

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 1.b.1: Restore Sheet\n\n");
        System.out.println("Foglio Riepilogativo Nuovo:");
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(12);
        System.out.println(s.toString());

        Preparation p = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Besciamella");
        ap.add(p);
        p = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Ragu Classico");
        ap.add(p);

        CatERing.getInstance().getSheetManager().getCurrentSheet().addRecipe("Lasagne");
        CatERing.getInstance().getSheetManager().getCurrentSheet().addTask("Preparare le Lasagne",ap,4,120 );
        System.out.println("Foglio Riepilogativo con aggiunte Informazioni:");
        System.out.println(s.toString());

        s = CatERing.getInstance().getSheetManager().restoreSummarySheet(12);
        System.out.println("Foglio Riepilogativo Ripulito:");
        System.out.println(s.toString());

    }
}
