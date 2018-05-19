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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *  Exports a file to XML
 * 
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public class ExportToXML implements ExportStrategy {
    
    /**
     * XML Header
     */
    private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    /**
     * Exports users expenses in a given time to an XML file
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
        Path exportPath = Paths.get(baseFolder + "/exports/xml/" 
                                  + user.mecanographicNumber().toString() 
                                  + "_" + formattedStartDate
                                  + "_" + formattedEndDate
                                  + "_movements"
                                  + ".xml");
        
        File exportFile = new File(exportPath.toUri());
        
        /**
         * Try to create the file in memory
         */
        exportFile.createNewFile();
        
        /**
         * Create the file writer, using fileoutputstream and bufferedwriter on this case.
         */
        FileOutputStream outputStream = new FileOutputStream(exportFile);
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(outputStream));
        
        String userDTO  = "";
        String dateDTO  = "";
        String moneyDTO = "";
        String typeDTO  = "";
        
        String xmlTagOpen    = "<userTransactionBetweenPeriod>";
        String xmlTagClose   = "</userTransactionBetweenPeriod>";
        
        String txsTagOpen     = "<transactions>";
        String txsTagClose    = "</transactions>";
        
        String txTagOpen     = "<transaction>";
        String txTagClose    = "</transaction>";
        
        String userTagOpen   = "<user>";
        String userTagClose  = "</user>";
        
        String dateTagOpen   = "<date>";
        String dateTagClose  = "</date>";
        
        String moneyTagOpen  = "<money>";
        String moneyTagClose = "</money>";
        
        String typeTagOpen   = "<type>";
        String typeTagClose  = "</type>";
        
        String newLine       = "\r\n";
        String tab           = "\t";
        
        /**
         * Start writing of XML file
         */
        write.append(XML_VERSION + newLine);
        write.append(xmlTagOpen + newLine);
        write.append(tab + txsTagOpen + newLine);
        
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
             * Write the variables to the file in xml format
             */
            write.append(tab + tab + txTagOpen + newLine);
            write.append(tab + tab + tab + userTagOpen  + userDTO  + userTagClose  + newLine);
            write.append(tab + tab + tab + dateTagOpen  + dateDTO  + dateTagClose  + newLine);
            write.append(tab + tab + tab + moneyTagOpen + moneyDTO + moneyTagClose + newLine);
            write.append(tab + tab + tab + typeTagOpen  + typeDTO  + typeTagClose  + newLine);
            write.append(tab + tab + txTagClose + newLine);
        }
        
        write.append(tab + txsTagClose + newLine);
        
        /**
         * Close file
         */
        write.append(xmlTagClose);
        write.close();
        
        return true;
    }
    
}
