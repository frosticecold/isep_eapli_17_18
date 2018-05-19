package eapli.ecafeteria.domain.booking.export;

/**
 *
 * Factory that instantiates the ExportStrategy 
 * using singleton principle
 *
 * @author Rui Almeida <1160818@isep.ipp.pt>
 */
public class ExportFactory {
    

    private static ExportFactory factory = null;
    
    private ExportFactory(){};
    
    public static ExportFactory instance() {
        if (factory == null) factory = new ExportFactory();
        return factory;
    }

    /**
     * Based on the specified format - available formats can be seen in
     * ExportFormats class - instanciates the export strategy for the export.
     * @author Rui Almeida <1160818>
     * @param format - format to export
     * @return
     */
    public ExportStrategy exportStrategy(ExportFormats format) {
        if (format.equals(ExportFormats.CSV)) return new ExportToCSV();
        if (format.equals(ExportFormats.XML)) return new ExportToXML();
        else return null;
    }

}
