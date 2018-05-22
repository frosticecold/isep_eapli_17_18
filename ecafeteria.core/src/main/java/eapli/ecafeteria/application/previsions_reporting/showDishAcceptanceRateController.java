package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class showDishAcceptanceRateController implements Controller {


    public showDishAcceptanceRateController() {

    }

    /**
     * Prepare a list of all Ratings and calculate their acceptance rate
     *
     * @return
     */
    public String prepareInformation() {

        String msg = "=============================================== RATINGS =======================================\n";

        Iterable<Rating> ratings = PersistenceContext.repositories().rating().findAll();

        double acceptanceRate = PrevisionsService.dishAcceptanceRate();

        msg += ratings.toString() + "\n";

        msg += "\n\n Dish Acceptance Rate (Rating Greater or equal to 3) : " + acceptanceRate + "% \n";

        msg += "=================================================================================================================\n";

        return msg;
    }
}
