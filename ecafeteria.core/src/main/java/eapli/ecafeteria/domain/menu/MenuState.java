/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
/**
 * Menu state has two possible states
 * <p>
 * * Unpublished, Published
 *
 */
public enum MenuState implements Serializable{
    UNPUBLISHED, PUBLISHED;
    
    MenuState(){}
}


