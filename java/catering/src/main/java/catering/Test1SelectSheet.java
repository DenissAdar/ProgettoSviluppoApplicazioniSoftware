package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;

public class Test1SelectSheet {
    public static void main(String[] args) throws UseCaseLogicException {


        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 1: Select Sheet\n\n");


        SummarySheet s = CatERing.getInstance().getSheetManager().chooseSheetFile(11);
        System.out.println(s.toString());
    }
}
