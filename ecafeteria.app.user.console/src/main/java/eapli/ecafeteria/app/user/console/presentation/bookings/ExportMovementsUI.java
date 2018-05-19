package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.export.ExportMovementsController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.booking.export.ExportFormats;
import eapli.framework.util.Console;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public class ExportMovementsUI extends CafeteriaUserBaseUI {

    @Override
    protected boolean doShow() {

        ExportMovementsController controller = new ExportMovementsController();

        System.out.println("»»» Export movements «««");
        System.out.println("» What format would you like to export to?");

        ExportFormats[] formats = ExportFormats.values();
        for (int i = 1; i <= formats.length; i++) {
            System.out.println(i + ". " + formats[i - 1].name());
        }

        int option = 0;

        do {
            option = Console.readInteger("» Format Number: ");
        } while (option < 1 && option > formats.length);

        System.out.println("»»» Please insert the period to search for movements: ");
        Calendar startDate = Console.readCalendar("» Start Date (dd-mm-yyyy)");
        Calendar endDate = Console.readCalendar("» End Date (dd-mm-yyyy)");

        try {
            if (controller.exportMovements(formats[option - 1], startDate, endDate)) {
                System.out.println("»»» Movements were exported successfully.");
                return true;
            } else {
                System.out.println("»»» Could not export movements!");
                return false;
            }

        } catch (IOException ex) {
            System.out.println("»»» Could not export movements! " + ex.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return super.headline();
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}
