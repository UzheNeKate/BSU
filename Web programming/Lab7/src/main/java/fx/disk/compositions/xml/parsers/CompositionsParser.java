package fx.disk.compositions.xml.parsers;

import fx.disk.compositions.Disk;

/**
 * Parsing interface
 */

public interface CompositionsParser {
    Disk parse(String fileName) throws ParserException;
}
