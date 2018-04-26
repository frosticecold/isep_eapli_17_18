/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.dishes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class AlergenEnum implements Serializable {

    @Id
    private Long id;

    public enum Alergens {
        GLUTEN {
            @Override
            public String toString() {
                return "Cereals containing gluten (wheat, rye, barley, oats, spelled, gamut or other hybridised strains) and products based thereon";
            }
        },
        CRUSTACEANS {
            @Override
            public String toString() {
                return "Crustaceans and crustacean products";
            }
        },
        EGGS {
            @Override
            public String toString() {
                return "Eggs and egg products";
            }
        },
        FISH {
            @Override
            public String toString() {
                return "Fish and fish products";
            }
        },
        PEANUTS {
            @Override
            public String toString() {
                return "Peanuts and peanut products";
            }
        },
        SOYBEAN {
            @Override
            public String toString() {
                return "Soybean and soybean products";
            }
        },
        MILK {
            @Override
            public String toString() {
                return "Milk and milk products (including lactose)";
            }
        },
        NUTS {
            @Override
            public String toString() {
                return "Nuts, in particular almonds, hazelnuts, walnuts, cashew nuts, pistachios, among others";
            }
        },
        CELERY {
            @Override
            public String toString() {
                return "Celery and celery products";
            }
        },
        MUSTARD {
            @Override
            public String toString() {
                return "Mustard and mustard products";
            }
        },
        SESAME {
            @Override
            public String toString() {
                return "Seeds of sesame and sesame seed products";
            }
        },
        SULFUR {
            @Override
            public String toString() {
                return "Sulfur dioxide and sulphites in concentrations higher than 10mg / kg or 10ml / L";
            }
        },
        LUPINE {
            @Override
            public String toString() {
                return "Lupine and lupine products";
            }
        },
        MULLUSCS {
            @Override
            public String toString() {
                return "Molluscs and mollusc products";
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}