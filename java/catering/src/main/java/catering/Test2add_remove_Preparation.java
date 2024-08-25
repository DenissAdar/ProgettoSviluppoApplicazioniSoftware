package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SummarySheet;

import java.util.ArrayList;

public class Test2add_remove_Preparation {
    public static void main(String[] args) throws UseCaseLogicException {



        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 2 e 2.a.1: add/Remove Preparation");
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(13);
        System.out.println(s.toString());

        Preparation t,g,cs,tp;
        t = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Separazione Tuorli");
        g = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Guanciale");
        cs= CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Cottura Spaghetti");
        tp= CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Tuorli+Pecorino");

        System.out.println("Foglio Riepilogativo con aggiunte Preparazioni:");
        System.out.println(s.toString());

        CatERing.getInstance().getSheetManager().getCurrentSheet().removePreparation(t);
        CatERing.getInstance().getSheetManager().getCurrentSheet().removePreparation(g);
        CatERing.getInstance().getSheetManager().getCurrentSheet().removePreparation(cs);
        CatERing.getInstance().getSheetManager().getCurrentSheet().removePreparation(tp);
        System.out.println("Foglio Riepilogativo con Preparazioni rimosse:");
        System.out.println(s.toString());



    }
}
