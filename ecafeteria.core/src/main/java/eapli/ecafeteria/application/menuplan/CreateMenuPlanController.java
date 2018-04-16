/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menuplan;

import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;


public class CreateMenuPlanController implements Controller {
    
     private final ListMenuService svc = new ListMenuService();
     
     private final MenuPlanRepository mpr=PersistenceContext.repositories().menuPlan();
     
     
     
     
     //metodo que vai buscar menus da semana atual
     
     //metodo que vai a cada refeicao preencher com o numero de pratos
     
     //metodo que poe isso na base de dados
}
