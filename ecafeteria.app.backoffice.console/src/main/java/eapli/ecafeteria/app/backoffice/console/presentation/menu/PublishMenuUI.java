/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.PublishMenuController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class PublishMenuUI extends AbstractUI {

    private PublishMenuController controller = new PublishMenuController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        boolean publish = true;
        List<String> list = new ArrayList<>();
        list.add("Publish Critical Menus");
        list.add("Publish Not Critical Menus");
        SelectWidget<String> widget = new SelectWidget<>("Showing Options", list);
        do {
            try {
                widget.show();
                int option = widget.selectedOption();
                switch (option) {
                    case 1:
                        publishCriticalMenus();
                        break;
                    case 2:
                        publishNotCriticalMenus();
                        break;
                }
                publish = Console.readBoolean("Keep publishing?");
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(PublishMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (publish);
        return true;
    }

    @Override
    public String headline() {
        return "Publish Menus";
    }

    private void publishCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        System.out.println(controller.publishCriticalMenus());
    }

    private void publishNotCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        System.out.println(controller.publishNotCriticalMenus());
    }

}
