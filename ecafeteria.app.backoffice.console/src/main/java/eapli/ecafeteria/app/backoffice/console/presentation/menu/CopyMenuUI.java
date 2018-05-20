/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menus.CopyMenuController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class CopyMenuUI extends AbstractUI {

    private CopyMenuController controller = new CopyMenuController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        Iterable<Menu> menu = controller.getAvailableMenus();
        String output = "";
        for (Menu m : menu) {
            output += "\n" + m.toString() + controller.showStartAndFinishDates(m) + "\n";
        }
        System.out.println(output);
        return true;
    }

    @Override
    public String headline() {
        return "Copy Menu";
    }
}
