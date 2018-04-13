/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ListUnpublishedMenus {

    private List<Menu> criticalMenus;
    private List<Menu> notCriticalMenus;

    private ListUnpublishedMenus() {
    }

    public ListUnpublishedMenus(Iterable<Menu> resultOfDB) {
        criticalMenus = new LinkedList<>();
        notCriticalMenus = new LinkedList<>();
        addAll(resultOfDB);

    }

    private void addAll(Iterable<Menu> resultOfDB) {
        for (Menu m : resultOfDB) {
            add(m);
        }
    }

    public void add(Menu menu) {
        if (menu.isCritical()) {
            criticalMenus.add(menu);
        } else {
            notCriticalMenus.add(menu);
        }
    }

    public List<Menu> criticalMenus() {
        return criticalMenus;
    }

    public List<Menu> notCriticalMenus() {
        return notCriticalMenus;
    }
}
