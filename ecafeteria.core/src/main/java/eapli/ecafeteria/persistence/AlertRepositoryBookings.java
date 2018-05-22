package eapli.ecafeteria.persistence;

import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;


public interface AlertRepositoryBookings extends DataRepository<AlertBookingDTO, Long>{
    
    public List<AlertBookingDTO> getNOBookings();
    
}
