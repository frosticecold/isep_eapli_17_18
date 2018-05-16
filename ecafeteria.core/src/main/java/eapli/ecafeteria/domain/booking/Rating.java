/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Booking booking;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    
    private String reply;
    @OneToOne
    private CafeteriaUser user;

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
    public Rating(CafeteriaUser user, Booking booking, int rating, String comment, Calendar date) {
        if (booking == null || rating < 1 || rating > 5 || comment == null) {
            throw new IllegalArgumentException();
        }
        this.user = user;
        this.rating = rating;
        this.booking = booking;
        this.comment = comment;
        this.date = date;
        this.reply = "No reply yet.";
    }

    /**
     * Constructor
     *
     * @param booking
     * @param rating
     * @param comment
     */
    public Rating(Booking booking, int rating, String comment) {
        if (booking == null || rating < 1 || rating > 5 || comment == null) {
            throw new IllegalArgumentException();
        }
        this.rating = rating;
        this.booking = booking;
        this.comment = comment;
        this.reply = "No reply yet.";
    }

    @Override
    public boolean equals(Object obj
    ) {
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
        return this.id.equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.id().hashCode();
    }

    @Override
    public boolean sameAs(Object other
    ) {
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

    public void addReply(String reply) {
        this.reply = reply;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public boolean is(Long otherId) {
        return this.id.equals(id);
    }

    /**
     * Returns the cafeteria user associated with the rating
     *
     * @return
     */
    public CafeteriaUser user() {
        return this.user;
    }

    @Override
    public String toString() {      
        

        StringBuilder str = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date.getTime());
        
        str.append("Date: ").append(formattedDate);
        str.append("\nDish: ").append(booking.getMeal().dish().id())
                .append("\nType: " + booking.getMeal().mealtype());
        str.append("\nRating: ");
        for (int i = 0; i < this.rating; i++) {
            str.append("*");
        }
        str.append("\n\tComment: ").append(this.comment).append("\n\tReply:   ").append(this.reply);

        return str.toString();
    }
}
