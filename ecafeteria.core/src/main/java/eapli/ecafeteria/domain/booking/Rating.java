/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
@Entity
public class Rating implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int rating;
    private String comment;
    private Booking booking;

    protected Rating() {
        //for ORM 
    }

    /**
     * Constructor
     *
     * @param booking
     * @param rating
     * @param comment
     */
    public Rating(Booking booking, int rating, String comment) {
        if (booking == null || rating < 0 || rating > 5 || comment == null) {
            throw new IllegalArgumentException();
        }
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        return this.id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.id().hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Rating)) {
            return false;
        }
        final Rating otherRating = (Rating) other;
        if (this == otherRating) {
            return true;
        }
        if (this.rating != otherRating.rating) {
            return false;
        }
        if (!this.comment.equals(otherRating.comment)) {
            return false;
        }
        if (!this.booking.equals(otherRating.booking)) {
            return false;
        }
        return true;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public boolean is(Long otherId) {
        return this.id.equals(id);
    }

}
