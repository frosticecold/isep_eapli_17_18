package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.actions.*;
import eapli.framework.persistence.*;
import java.util.*;

public class BatchesBootstrapper implements Action {
    @Override
    public boolean execute() {

        BatchRepository batchRepository = PersistenceContext.repositories().batch();
        MaterialRepository materialRepository = PersistenceContext.repositories().materials();

        Material m1 = materialRepository.findByAcronym("eggs").get();
        Material m2 = materialRepository.findByAcronym("oil").get();
        Material m3 = materialRepository.findByAcronym("so").get();

        Batch b1 = new Batch("5601252106554", new GregorianCalendar(2019, 5, 1), m2);
        Batch b2 = new Batch("5601252106554", new GregorianCalendar(2019, 11, 1), m2);
        Batch b3 = new Batch("5600826203064", new GregorianCalendar(2018, 11, 24), m1);
        Batch b4 = new Batch("5600826203064", new GregorianCalendar(2019, 8, 15), m1);
        Batch b5 = new Batch("8412600024485", new GregorianCalendar(2018, 4, 18), m3);
        Batch b6 = new Batch("8412600024485", new GregorianCalendar(2018, 5, 5), m3);

        try {
            batchRepository.save(b1);
            batchRepository.save(b2);
            batchRepository.save(b3);
            batchRepository.save(b4);
            batchRepository.save(b5);
            batchRepository.save(b6);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return true;
    }
}