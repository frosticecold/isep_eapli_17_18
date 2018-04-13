/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import java.util.List;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ListUnpublishedMenus {
    
    private List<Menu> criticalMenus;
    private List<Menu> notCriticalMenus;
    
    public void add(Menu menu) {
        // comparar data e adicionar ás listas
    }
    
    public List<Menu> criticalMenus() {
        return criticalMenus;
    }
    
    public List<Menu> notCriticalMenus() {
        return notCriticalMenus;
    }
}
