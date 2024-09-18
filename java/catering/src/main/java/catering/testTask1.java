package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;


public class testTask1 {
    public static void main(String[] args) throws UseCaseLogicException {

        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        SummarySheet s = CatERing.getInstance().getSheetManager().chooseSheetFile(100);
        System.out.println(s.toString()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        s = CatERing.getInstance().getSheetManager().chooseSheetFile(5);
        System.out.println(s.toString());

     /*    SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(5);
       // SummarySheet s = CatERing.getInstance().getSheetManager().getFakeSheet(100);
        System.out.println(s.toString());
        CatERing.getInstance().getSheetManager().restoreSummarySheet(s.getId());
        System.out.println(s.toString());
        Preparation p1 = CatERing.getInstance().getSheetManager().definePreparation("Crema" , s.getId());
        ArrayList<Preparation> ap = new ArrayList<>();
        ap.add(p1);
        CatERing.getInstance().getSheetManager().defineRecipe("Tonno" , s.getId());
        CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Prepara tonno" , ap,1, 3 );
        CatERing.getInstance().getSheetManager().defineTask(s.getId(), "Suicidarsi" , new ArrayList<>(),0,30);
        System.out.println(s.toString());



       //SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(100);
        SummarySheet s = CatERing.getInstance().getSheetManager().getFakeSheet(100);
        System.out.println(s.toString());


        CatERing.getInstance().getSheetManager().restoreSummarySheet(s.getId());

        System.out.println(s.toString());


        Preparation p1 = CatERing.getInstance().getSheetManager().definePreparation("Besciamella" , s.getId());
        Preparation p2 = CatERing.getInstance().getSheetManager().definePreparation("Ragu Classico" , s.getId());
        Preparation p3 = CatERing.getInstance().getSheetManager().definePreparation("Impanatura Pesce" , s.getId());
        ArrayList<Preparation> ap = new ArrayList<>();
        ap.add(p1);
        ap.add(p2);

        CatERing.getInstance().getSheetManager().defineRecipe("Lasagne di Deniss" , s.getId());
        CatERing.getInstance().getSheetManager().defineRecipe("Fritto Misto di Gaia" , s.getId());

        CatERing.getInstance().getSheetManager().defineTask(s.getId(),"Preparazione Lasagne per il Pranzo di Domenica" , ap,23, 120 );
        CatERing.getInstance().getSheetManager().defineTask(s.getId(), "Lavare la Cucina" , new ArrayList<>(),0,30);
        System.out.println(s.toString());
        */



    }
}
