/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 *
 * @author Rafael Teixeira <1160911@isep.ipp.pt>
 */
@Entity
public class NutritionalProfile implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @JoinTable
    @OneToOne
    private int maxCalDish;
    @OneToOne
    private int maxSaltDish;
    @OneToOne
    private CafeteriaUser user;
    @OneToOne
    private int maxSaltWeek;
    @OneToOne
    private int maxCalWeek;

    public NutritionalProfile(CafeteriaUser user) {
        this.user = user;

    }
    


    protected NutritionalProfile() {
        //FOR ORM
    }

    public int maxCalWeek() {
        return maxCalWeek;
    }

    public int maxCalDish() {
        return maxCalDish;
    }

    public int maxSaltDish() {
        return maxSaltDish;
    }

    public int maxSaltWeek() {
        return maxSaltWeek;
    }

    public int changeMaxCalDish(int newMaxCal) {

        return this.maxCalDish = newMaxCal;
    }

    public int changeMaxCalWeek(int newMaxCalWeek) {

        return this.maxCalWeek = newMaxCalWeek;
    }

    public int changeMaxSaltWeek(int newMaxSaltWeek) {

        return this.maxSaltWeek = newMaxSaltWeek;
    }

    public int changeMaxSaltDish(int newMaxSalt) {

        return this.maxSaltWeek = newMaxSalt;
    }

    public CafeteriaUser user() {
        return user;
    }

}
