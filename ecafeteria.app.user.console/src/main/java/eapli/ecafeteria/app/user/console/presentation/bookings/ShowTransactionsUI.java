/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.ShowTransactionsController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.dto.TransactionDTO;
import eapli.framework.presentation.console.ListWidget;
import java.util.List;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class ShowTransactionsUI extends CafeteriaUserBaseUI {

    private final ShowTransactionsController controller = new ShowTransactionsController();
    
    @Override
    protected boolean doShow() {
        ListWidget<List<TransactionDTO>> listWidget 
                = new ListWidget("Show transactions", controller.showTransactions());
        listWidget.show();
        return true;
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
