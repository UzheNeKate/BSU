package fx.disk.view;

import fx.disk.compositions.Disk;
import fx.disk.compositions.xml.XMLValidator;
import fx.disk.compositions.xml.XMLValidatorException;
import fx.disk.compositions.xml.parsers.ParserException;
import fx.disk.controller.Controller;
import fx.disk.controller.ParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class Main {

    private static final Logger logger = (Logger) LogManager.getLogger();
    private static final String path = "src/main/java/fx/disk/compositions/xml/";

    public static void main(String[] args){

        try {
            var result = XMLValidator.validate(path+"disk.xml", path+"compositions.xsd");
            System.out.println("Validation result:" + result);
        } catch (XMLValidatorException e) {
            logger.warn(e);
        }

        Disk disk = null;
        logger.info("Try to parse xml");
        try {
            disk = Controller.parse(path+"disk.xml", ParserType.SAX);
        } catch (ParserException e) {
            logger.warn(e);
            //e.printStackTrace();
        }
        disk.forEach(System.out::println);
    }
}
