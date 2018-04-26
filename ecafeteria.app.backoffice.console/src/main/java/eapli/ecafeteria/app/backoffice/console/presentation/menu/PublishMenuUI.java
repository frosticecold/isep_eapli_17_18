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
import eapli.framework.presentation.console.ListWidget;
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
        List<String> optionsList = new ArrayList<>();
        List<String> critMenuList = new ArrayList<>();
        List<String> notCritMenuList = new ArrayList<>();
        
        optionsList.add("Publish Critical Menus");
        optionsList.add("Publish Not Critical Menus");
        
        for (Menu criticalMenu : controller.criticalMenus()) {
            critMenuList.add(criticalMenu.toString());
        }
        
        for (Menu notCriticalMenu : controller.notCriticalMenus()) {
            notCritMenuList.add(notCriticalMenu.toString());
        }
        
        /**
         * Print the critical menus in a List Widget
         */
        ListWidget<String> critListMenus = new ListWidget<>("Critical Menus", critMenuList);
        /**
         * Print the not critical menus in a List Widget
         */
        ListWidget<String> notCritListMenus = new ListWidget<>("Not Critical Menus", notCritMenuList);
        /**
         * Prints the options to select
         * 1. Publish Critical Menus
         * 2. Publish Not Critical Menus
         */
        SelectWidget<String> widget = new SelectWidget<>("Showing Options", optionsList);
        
        do {
            try {
                critListMenus.show();
                notCritListMenus.show();
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
    
    /**
     * Publishes the critical menus and prints the number of published menus
     * 
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    private void publishCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        System.out.println(controller.publishCriticalMenus());
    }
    
    /**
     * Publishes the not critical menus and prints the number of published menus
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    private void publishNotCriticalMenus() throws DataConcurrencyException, DataIntegrityViolationException {
        System.out.println(controller.publishNotCriticalMenus());
    }

}
