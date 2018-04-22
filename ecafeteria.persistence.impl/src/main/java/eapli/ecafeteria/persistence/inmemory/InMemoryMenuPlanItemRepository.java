/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.persistence.MenuPlanItemRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author pedro
 */
public class InMemoryMenuPlanItemRepository extends InMemoryRepositoryWithLongPK<MenuPlanItem> implements MenuPlanItemRepository {
    
}
