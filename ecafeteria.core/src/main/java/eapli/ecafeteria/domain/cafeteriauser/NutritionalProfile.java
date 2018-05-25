/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import java.io.Serializable;
import javax.persistence.Column;
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

    @JoinTable(name = "NUTRITIONAL_PROFILE")
    @Column
    private String maxCalDish;
    @Column
    private String maxSaltDish;
    @Column
    private String maxSaltWeek;
    @Column
    private String maxCalWeek;
    @OneToOne
    private CafeteriaUser user;

    public NutritionalProfile(CafeteriaUser user) {
        this.user = user;

    }
    


    protected NutritionalProfile() {
        //FOR ORM
    }

    public String maxCalWeek() {
        return maxCalWeek;
    }

    public String maxCalDish() {
        return maxCalDish;
    }

    public String maxSaltDish() {
        return maxSaltDish;
    }

    public String maxSaltWeek() {
        return maxSaltWeek;
    }

    public String changeMaxCalDish(String newMaxCal) {

        return this.maxCalDish = newMaxCal;
    }

    public String changeMaxCalWeek(String newMaxCalWeek) {

        return this.maxCalWeek = newMaxCalWeek;
    }

    public String changeMaxSaltWeek(String newMaxSaltWeek) {

        return this.maxSaltWeek = newMaxSaltWeek;
    }

    public String changeMaxSaltDish(String newMaxSalt) {

        return this.maxSaltDish = newMaxSalt;
    }

    public CafeteriaUser user() {
        return user;
    }

}
