package eapli.ecafeteria.domain.booking.export;

import java.io.IOException;
import java.util.Calendar;

/**
 * Strategy impementation for export
 *
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public interface ExportStrategy {

    /**
     * Exports user expenses in a given time
     * @author Rui Almeida <1160818>
     * @param startDate start date of the period to search for movements
     * @param endDate end date of the period to search for movements
     * @return true if export was successful, false if not
     * @throws java.io.IOException
     */
    public boolean exportMovements(Calendar startDate, Calendar endDate) throws IOException;

}
