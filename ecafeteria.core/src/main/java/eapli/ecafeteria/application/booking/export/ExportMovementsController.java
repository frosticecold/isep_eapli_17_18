package eapli.ecafeteria.application.booking.export;

import eapli.ecafeteria.domain.booking.export.ExportFactory;
import eapli.ecafeteria.domain.booking.export.ExportFormats;
import eapli.ecafeteria.domain.booking.export.ExportStrategy;
import eapli.framework.application.Controller;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public class ExportMovementsController implements Controller {
    
    public ExportMovementsController() {}
    
    /**
     * Exports the movements to the specified format
     * @param format format to export the movements to
     * @param startDate start date to search for the movements
     * @param endDate end date to search for the movents
     * @return true if export was successfull, false if not
     * @throws java.io.IOException
     */
    public boolean exportMovements(ExportFormats format, Calendar startDate, Calendar endDate) throws IOException {
        ExportStrategy export = ExportFactory.instance().exportStrategy(format);
        if (export.exportMovements(startDate, endDate)) return true;
        else return false;
    }
    
}
