package catering;

import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.testTask;
import catering.businesslogic.user.User;

public class testTask1 {
    public static void main(String[] args) throws UseCaseLogicException {

            //TODO: NON POSSO FARLO FINCHè NON RISOLVO ERRORI PERSISTENZA
            testTask.getInstance().getUserManager().fakeLogin("Marco");
            User u = testTask.getInstance().getUserManager().getCurrentUser();
            u.toString();
           // SummarySheet s = testTask.getInstance().getSheetManager().createSummarySheet(1);
            //System.out.println(s.toString());


    }
}
