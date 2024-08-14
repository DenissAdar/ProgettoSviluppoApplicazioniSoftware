package catering.businesslogic.task;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.user.User;
import catering.businesslogic.user.UserManager;

import java.util.ArrayList;

public class SheetManager {
    private ArrayList<SheetEventReceiver> eventReceivers;
    private SummarySheet curSheet;
    private ArrayList<SummarySheet> sheets;

    // Operazione 1.a.1
    public SummarySheet createSummarySheet(int sheetId) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        SummarySheet newSheet = new SummarySheet(sheetId);
        sheets.add(newSheet);
        return newSheet;
    }

    // Operazione 1
    public SummarySheet chooseSheetFile(int sheetId) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef())
            throw new UseCaseLogicException();
        if(this.isPresent(sheetId))
        {
            return getSheet(sheetId);
        }

        throw new UseCaseLogicException();
    }

    public boolean isPresent(int sheetId){

        for (SummarySheet s: sheets) {
            if(s.getId() == sheetId)
                return true;

        }
        return false;
    }

    public SummarySheet getSheet(int sheetId){

        for (SummarySheet s: sheets) {
            if(s.getId() == sheetId)
                return s;
        }
    return null;
    }
}
