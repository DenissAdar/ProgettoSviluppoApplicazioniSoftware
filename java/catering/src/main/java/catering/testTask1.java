package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.Preparation;
import catering.businesslogic.task.SheetEventReceiver;
import catering.businesslogic.task.SheetManager;
import catering.businesslogic.user.User;

import java.util.ArrayList;


public class testTask1 {
    public static void main(String[] args) throws UseCaseLogicException {

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            SheetManager SheetMgr = new SheetManager();
            SheetMgr.createSummarySheet(5);
            SheetMgr.definePreparation("Carbonara" , 5);
            SheetMgr.definePreparation("Amatriciana" , 5);

            //SheetMgr.chooseSheetFile(5);
             System.out.println(SheetMgr.chooseSheetFile(5).toString());


             SheetMgr.defineTask(5, "Cena",SheetMgr.chooseSheetFile(5).getPreparations(),3,60 );
            System.out.println(SheetMgr.chooseSheetFile(5).toString());

            //TODO: NON POSSO FARLO FINCHè NON RISOLVO ERRORI PERSISTENZA
           /* testTask.getInstance().getUserManager().fakeLogin("Marco");
            User u = testTask.getInstance().getUserManager().getCurrentUser();
            u.toString();
            SheetManager SheetMgr = new SheetManager();
            SummarySheet s = testTask.getInstance().getSheetManager().createSummarySheet(1);
            System.out.println(s.toString());*/


    }
}
