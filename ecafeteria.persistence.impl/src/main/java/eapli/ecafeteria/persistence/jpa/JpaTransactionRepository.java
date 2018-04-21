/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.persistence.TransactionRepository;
import javax.persistence.Query;

/**
 *
 * @author MarioDias
 */
public class JpaTransactionRepository extends CafeteriaJpaRepositoryBase<Transaction, Long> implements TransactionRepository {

    @Override
    public Iterable<Transaction> findAllTransactionsByMecanographicNumberAndType(MecanographicNumber number, String transactionType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Transaction> findAllActive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Balance getBalanceOfUser(MecanographicNumber user) {
        Query q = entityManager().
                createQuery("SELECT currentBalance FROM CafeteriaUser"
                        + " WHERE mecanographicNumber=:user", Balance.class);

        q.setParameter("user", user);
        return (Balance) q.getSingleResult();
    }

}
