/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.ecafeteria.persistence.DeactivationReasonTypeRepository;
import eapli.framework.actions.Action;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class DeactivationReasonTypeBootstrapper implements Action {

    private final String filepath = "deactivation_reasons.csv";

    @Override
    public boolean execute() {

        try {
            File csvfile = new File(getClass().getClassLoader().getResource(filepath).getFile());
            CSVParser parser = CSVParser.parse(csvfile, Charset.defaultCharset(), CSVFormat.DEFAULT);
            List<CSVRecord> records = parser.getRecords();

            save(records);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DeactivationReasonTypeBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void save(Iterable<CSVRecord> list) {

        //open
        final TransactionalContext tx = PersistenceContext.repositories().buildTransactionalContext();
        final DeactivationReasonTypeRepository repo = PersistenceContext.repositories().deactivationReasonTypeRepository(tx);

        tx.beginTransaction();
        for (CSVRecord csvrecord : list) {
            try {
                DeactivationReasonType d = new DeactivationReasonType(csvrecord.get(0));
                repo.save(d);
            } //commit
            catch (DataConcurrencyException ex) {
                Logger.getLogger(DeactivationReasonTypeBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
                return;
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(DeactivationReasonTypeBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        tx.commit();

    }
}
