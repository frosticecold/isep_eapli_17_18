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
import eapli.framework.presentation.console.SelectWidget;

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
        SelectWidget<Menu> widget = new SelectWidget<>("Select a menu:\n", controller.getAvailableMenus());
        widget.show();
        if (widget.selectedElement() != null) {
            Menu menu = widget.selectedElement();
        }
        return true;
    }

    @Override
    public String headline() {
        return "Copy Menu";
    }
}
