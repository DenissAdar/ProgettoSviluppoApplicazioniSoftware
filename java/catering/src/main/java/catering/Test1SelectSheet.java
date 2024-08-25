package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;

public class Test1SelectSheet {
    public static void main(String[] args) throws UseCaseLogicException {


        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 1: Select Sheet\n\n");
        CatERing.getInstance().getSheetManager().createSummarySheet(10);
        SummarySheet s = CatERing.getInstance().getSheetManager().getSheet(10); // gestisci il caricamento dal db(senza dover creare)
        System.out.println(s.toString());
    }
}
