package catering;
import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;

public class Test1aCreateSheet {

    public static void main(String[] args) throws UseCaseLogicException {



        //Test di Login e di Creazione di un Menu da parte di un Utente non Chef
/*        CatERing.getInstance().getUserManager().fakeLogin("Carlin");
        System.out.println("\nTest Operazione 1.a.1: Create Sheet");
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(15);
        System.out.println(s.toString());
*/

        //Test di Login e di Creazione di un Menu da parte di un Utente Chef
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 1.a.1: Create Sheet\n\n");
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(11);
        System.out.println(s.toString());
        //------------------------------------------





    }
}
