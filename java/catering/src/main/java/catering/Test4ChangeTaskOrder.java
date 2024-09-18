package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.task.Task;

import java.util.ArrayList;

public class Test4ChangeTaskOrder {
    public static void main(String[] args) throws UseCaseLogicException {
        ArrayList<Preparation> ap1 = new ArrayList<>(),ap2 = new ArrayList<>();

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 4: Change Task Order\n\n");
        System.out.println("Foglio Riepilogativo Nuovo:");
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(15);
        System.out.println(s.toString());

        Preparation p = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Besciamella");
        ap1.add(p);
        p = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Ragu Classico");
        ap1.add(p);

        Preparation t = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Trita Carne");
        ap2.add(t);
        t = CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Aggiungi Mollica");
        ap2.add(t);


        CatERing.getInstance().getSheetManager().defineRecipe("Lasagne",s.getId());
        CatERing.getInstance().getSheetManager().defineRecipe("Polpette",s.getId());
        Task t1 = CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Preparare le Lasagne",ap1,4,120 ,"Mario");
        Task t2 = CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Preparare le Polpette",ap2,2,45 ,"Giuseppe");
        System.out.println("Foglio Riepilogativo con aggiunti i Task:");
        System.out.println(s.toString());



        CatERing.getInstance().getSheetManager().changeTaskOrder(t1,1,s.getId());
        CatERing.getInstance().getSheetManager().changeTaskOrder(t2,0,s.getId());
        System.out.println("Foglio Riepilogativo con i Task scambiati d'ordine:");
        System.out.println(s.toString());
    }
}
