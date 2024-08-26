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
        t = CatERing.getInstance().getSheetManager().definePreparation("Separazione Tuorli",s.getId());
        g = CatERing.getInstance().getSheetManager().definePreparation("Guanciale",s.getId());
        cs= CatERing.getInstance().getSheetManager().definePreparation("Cottura Spaghetti",s.getId());
        tp= CatERing.getInstance().getSheetManager().definePreparation("Tuorli+Pecorino",s.getId());

        System.out.println("Foglio Riepilogativo con aggiunte Preparazioni:");
        System.out.println(s.toString());

        CatERing.getInstance().getSheetManager().deletePreparation(t,s.getId());
        CatERing.getInstance().getSheetManager().deletePreparation(g,s.getId());
        CatERing.getInstance().getSheetManager().deletePreparation(cs,s.getId());
        CatERing.getInstance().getSheetManager().deletePreparation(tp,s.getId());
        System.out.println("Foglio Riepilogativo con Preparazioni rimosse:");
        System.out.println(s.toString());



    }
}
