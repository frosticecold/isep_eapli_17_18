package eapli.ecafeteria.domain.booking.export;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.dto.TransactionDTO;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.TransactionRepository;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *  Exports a file to CSV
 * 
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public class ExportToCSV implements ExportStrategy {

    /**
     * Exports users expenses in a given time to an CSV file
     * @param startDate start date of the period to search for movements
     * @param endDate end date of the period to search for movements
     * @author Rui Almeida <1160818>
     * @return true if file was exported successfully, false if not
     */
    @Override
    public boolean exportMovements(Calendar startDate, Calendar endDate) throws IOException {
  /**
         * Gather the Repository Factory
         */
        RepositoryFactory factory = PersistenceContext.repositories();
        
        /**
         * Instantiate the transaction repository
         */
        TransactionRepository transactionRepository = factory.movementTransactions();
        
        /**
         * Instantiate user repository 
         */
        CafeteriaUserRepository userRepository = factory.cafeteriaUsers();
        
        /**
         * Gather current authenticated user
         */
        CafeteriaUser user = userRepository.findByUsername(AuthorizationService.session().authenticatedUser().id()).get();
        
        /**
         * Format calendar to a string in yyyy-mm-dd format
         */
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = dateFormat.format(startDate.getTime());
        String formattedEndDate = dateFormat.format(endDate.getTime());
        
        List<Transaction> movements = transactionRepository.findUserTransactionsBetweenPeriod(user, formattedStartDate, formattedEndDate);

        if (movements.isEmpty()) return false;
           
        
        /**
         * Create a file in project exports/xml folder with the name userNumber_startDate_endDate_movements.xml
         */
        
        Path baseFolder = Paths.get(System.getProperty("user.dir")).getParent();
        Path exportPath = Paths.get(baseFolder + "/exports/csv/" 
                                  + user.mecanographicNumber().toString() 
                                  + "_" + formattedStartDate
                                  + "_" + formattedEndDate
                                  + "_movements"
                                  + ".csv");
        
        File exportFile = new File(exportPath.toUri());
        
        /**
         * Try to create the file in memory
         */
        exportFile.createNewFile();
        
        /**
         * Create the file writer, using fileoutputstream and bufferedwriter on this case.
         */
        FileOutputStream outputStream = new FileOutputStream(exportFile);
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        
        String userDTO  = "";
        String dateDTO  = "";
        String moneyDTO = "";
        String typeDTO  = "";
        
        String newLine       = "\r\n";
        String comma         = ";";
        
        /**
         * Start writing of CSV file
         */
        for (Transaction transaction : movements) { 
            
            /**
            * Get the transaction DTO (variables)
            */
            TransactionDTO transactionDTO = transaction.toDTO();
            
            userDTO  = transactionDTO.cafeteriaUser.id().toString();
            dateDTO  = dateFormat.format(transactionDTO.date.getTime());
            moneyDTO = transactionDTO.money.toString().split(" ")[0];
            typeDTO  = transactionDTO.transactionType.toString();
            
            /**
             * Write the variables to the file in csv format
             */
            write.append(userDTO + comma + dateDTO + comma +  moneyDTO + comma + typeDTO + newLine);
        }
        
        /**
         * Close file
         */
        write.close();
        
        return true;
    }

}
