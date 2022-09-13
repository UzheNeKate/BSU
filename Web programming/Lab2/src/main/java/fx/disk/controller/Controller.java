package fx.disk.controller;

import fx.disk.compositions.Disk;
import fx.disk.compositions.xml.parsers.DOMParser;
import fx.disk.compositions.xml.parsers.ParserException;
import fx.disk.compositions.xml.parsers.SAXParser;
import fx.disk.compositions.xml.parsers.StAXParser;

public class Controller {
    /**
     * @param filePath path to the file to read
     * @param parserType type of the parser to parse with
     * @return parsing disk
     * @throws ParserException
     */
    public static Disk parse(String filePath, ParserType parserType) throws ParserException {
        Disk disk;
        switch (parserType){
            case DOM -> disk = (new DOMParser()).parse(filePath);
            case SAX -> disk = (new SAXParser()).parse(filePath);
            case StAX -> disk = (new StAXParser()).parse(filePath);
            default -> throw new ParserException("Something wrong while parsing");
        }
        return disk;
    }
}
