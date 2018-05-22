/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

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
        List<String> deactivation_list = new ArrayList<>();
        for (CSVRecord csvrecord : list) {
            deactivation_list.add(csvrecord.get(0));
            //save
        }
        //commit
    }
}
