package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;


public class testTask1 {
    public static void main(String[] args) throws UseCaseLogicException {

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println("\n\nTest Create Sheet");

            SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(11);
            System.out.println(s.toString());
            CatERing.getInstance().getSheetManager().getCurrentSheet().addPreparation("Cacio");
            System.out.println("\nNuova aggiunta: "+ s.toString());


            //CatERing.getInstance().getSheetManager().getFakeSheet(1);
           /* SheetManager SheetMgr = new SheetManager();
            SheetMgr.createSummarySheet(5);
            SheetMgr.definePreparation("Carbonara" , 5);
            SheetMgr.definePreparation("Amatriciana" , 5);

            //SheetMgr.chooseSheetFile(5);
             System.out.println(SheetMgr.chooseSheetFile(5).toString());


             SheetMgr.defineTask(5, "Cena",SheetMgr.chooseSheetFile(5).getPreparations(),3,60 );
            System.out.println(SheetMgr.chooseSheetFile(5).toString());*/

            //TODO: NON POSSO FARLO FINCHè NON RISOLVO ERRORI PERSISTENZA
           /* testTask.getInstance().getUserManager().fakeLogin("Marco");
            User u = testTask.getInstance().getUserManager().getCurrentUser();
            u.toString();
            SheetManager SheetMgr = new SheetManager();
            SummarySheet s = testTask.getInstance().getSheetManager().createSummarySheet(1);
            System.out.println(s.toString());*/

        //TODO: Permettere l'interazione con il database aka persistence per riuscire a fare queste operazioni
       /* SheetManager SheetMgr = new SheetManager();
        SheetMgr.chooseSheetFile(1);
        System.out.println(SheetMgr.chooseSheetFile(1).toString());
        -------------------------------------------*/

    }
}
