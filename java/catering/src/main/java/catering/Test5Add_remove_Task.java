package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.task.Task;

import java.util.ArrayList;

public class Test5Add_remove_Task {
    public static void main(String[] args) throws UseCaseLogicException {
        ArrayList<Preparation> ap1 = new ArrayList<>(),ap2 = new ArrayList<>();

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 5 e 5.b.1: Add/Remove Task \n\n");
        System.out.println("Foglio Riepilogativo Nuovo:");
        SummarySheet s = CatERing.getInstance().getSheetManager().chooseSheetFile(11);
        System.out.println(s.toString());

        Preparation p = CatERing.getInstance().getSheetManager().definePreparation("Ketchup",s.getId());
        ap1.add(p);
        p = CatERing.getInstance().getSheetManager().definePreparation("Maionese",s.getId());
        ap1.add(p);

        Preparation t = CatERing.getInstance().getSheetManager().definePreparation("Sbatti Uova",s.getId());
        ap2.add(t);
        t = CatERing.getInstance().getSheetManager().definePreparation("Friggi Uova",s.getId());
        ap2.add(t);


        CatERing.getInstance().getSheetManager().defineRecipe("Salse",s.getId());
        CatERing.getInstance().getSheetManager().defineRecipe("Frittata",s.getId());
        Task t1 = CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Preparare le salse",ap1,4,120 ,"Marcello");
        Task t2 = CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Preparare le uova",ap2,2,45 ,"Manuela");
        System.out.println("Foglio Riepilogativo con aggiunti i Task:");
        System.out.println(s.toString());


        System.out.println("Foglio Riepilogativo con il task t1  rimosso:");
        CatERing.getInstance().getSheetManager().deleteTask(s.getId(),t1);
        System.out.println(s.toString());
        System.out.println("Foglio Riepilogativo con il task t1 e t2 rimosso:");
        CatERing.getInstance().getSheetManager().deleteTask(s.getId(),t2);
        System.out.println(s.toString());
    }
}
