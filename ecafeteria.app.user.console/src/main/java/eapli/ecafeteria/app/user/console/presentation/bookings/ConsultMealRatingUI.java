/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.booking.ConsultMealRatingController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

/**
 *
 * @author pedro
 */
public class ConsultMealRatingUI extends CafeteriaUserBaseUI {

    private ConsultMealRatingController controller = new ConsultMealRatingController();

    @Override
    protected boolean doShow() {

        int i = 0, comment, meal, j = 0;
        Map<String,Rating>map=new HashMap<>();
        Calendar cal = Console.readCalendar("Insert desired day to choose a meal (DD-MM-YYYY)");

        List<Meal> lm = controller.getMealByDate(cal);

        if (!lm.isEmpty()) {

            for (Meal m : lm) {
                System.out.println("Meal:" + (j + 1) + "\n" + m.toString());
                j++;
                System.out.println("***************************************************+");
            }

            do {
                meal = Console.readInteger("Choose a meal");
            } while (meal < 0 && meal > lm.size());

            Meal m = lm.get(meal - 1);

            List<Booking> bookingList = controller.getAllBookingsFromMealThatAreServed(m);

            if (!bookingList.isEmpty()) {
                List<Rating> ratingList = new ArrayList<>();

                for (Booking b : bookingList) {
                    Rating r = controller.getRatingFromBooking(b);
                    controller.addRatingToList(r, ratingList);
                }

                Double media = controller.calculateMedia(ratingList);

                System.out.println("Meal:\n" + m.toString());
                System.out.println("\n--------------------------------------------\n");
                System.out.println("\nQuantidade de Ratings\n" + ratingList.size());
                System.out.println("\n--------------------------------------------\n");
                System.out.println("Media de Ratings\n" + media);
                System.out.println("\n--------------------------------------------\n");
                System.out.println("Comentarios referentes a meal: ");

                List<String> CommentList = new ArrayList<>();

                for (Rating r : ratingList) {

                    System.out.println("Comentario:" + (i + 1) + "\n" + controller.showComment(r));

                    System.out.println("\n-----------------------------------------\n");

                    controller.addComentToList(controller.showComment(r), CommentList);
                    
                    map.put(controller.showComment(r),r);

                    i++;
                }

                System.out.println("******************************");

                System.out.println("Quer responder a algum comentário?");

                int n;

                do {
                    System.out.println("1-YES");
                    System.out.println("2-NO");

                    n = Console.readInteger("Choose:");
                } while (n != 1 && n != 2);

                if (n == 1) {

                    System.out.println("Escolha um comentario para responder:\n");

                    do {
                        comment = Console.readInteger("Choose:");
                    } while (comment < 0 && comment > CommentList.size());

                    System.out.println("Insira uma resposta para o comentario" + CommentList.get(comment - 1) + ":");

                    String response = Console.readLine("");

                    controller.replyComment(response,map.get(CommentList.get(comment - 1))); //por num map

                    try {
                        controller.saveRating(map.get(CommentList.get(comment - 1))); //por num map
                    } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                        Logger.getLogger(ConsultMealRatingUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println("Operação realizada com sucesso!!!");
                } else {
                    System.out.println("Operação realizada com sucesso!!!");
                }

            } else {
                System.out.println("A refeicao nao tem bookings ou entao as meals ainda nao foram servidas.");
            }

        } else {
               System.out.println("Nao existem meals para essa data");
        }
            
        return true;
    }

    @Override
    public String headline() {
        return "Consult the ratings for a meal:";
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}
