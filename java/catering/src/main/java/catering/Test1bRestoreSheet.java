package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;

public class Test1bRestoreSheet {
    public static void main(String[] args) throws UseCaseLogicException {
       /* ArrayList<Preparation> ap = new ArrayList<>();

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 1.b.1: Restore Sheet\n\n");
        System.out.println("Foglio Riepilogativo Creato in Precedenza:");
        SummarySheet s = CatERing.getInstance().getSheetManager().getFakeSheet(11);
        System.out.println(s.toString());

        Preparation p = CatERing.getInstance().getSheetManager().definePreparation("Pesto",s.getId());
        ap.add(p);
        p = CatERing.getInstance().getSheetManager().definePreparation("Uova Sode",s.getId());
        ap.add(p);

        CatERing.getInstance().getSheetManager().defineRecipe("Pasta al Pesto",s.getId());
        CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Preparare 4 Porzioni di Pasta",ap,4,120 );
        System.out.println("Foglio Riepilogativo con aggiunte Informazioni:"); */



        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        SummarySheet s = CatERing.getInstance().getSheetManager().chooseSheetFile(11);
        System.out.println(s.toString());

        s = CatERing.getInstance().getSheetManager().restoreSummarySheet(11);
        System.out.println("Foglio Riepilogativo Ripulito:");
        System.out.println(s.toString());

    }
}
