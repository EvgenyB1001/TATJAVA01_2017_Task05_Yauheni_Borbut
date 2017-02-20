package com.epam.task05.dao.parser;

import com.epam.task05.dao.exception.DAOException;
import com.epam.task05.dao.parser.dom.DOMParser;
import com.epam.task05.dao.parser.sax.SAXParser;
import com.epam.task05.dao.parser.stax.StAXParser;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class ParserFactory {

    private static ParserFactory instance;

    private static final String SAX_PARSER_NAME = "SAX";
    private static final String STAX_PARSER_NAME = "StAX";
    private static final String DOM_PARSER_NAME = "DOM";

    private static final String NO_SUCH_PARSER_TEXT = "No such parser available";

    private ParserFactory() {}

    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }

        return instance;
    }

    public Parser getParser(String parserName) throws DAOException {
        if (parserName.equals(SAX_PARSER_NAME)) {
            return new SAXParser();
        } else if (parserName.equals(STAX_PARSER_NAME)) {
            return new StAXParser();
        } else if (parserName.equals(DOM_PARSER_NAME)) {
            return new DOMParser();
        }

        throw new DAOException(NO_SUCH_PARSER_TEXT);
    }
}
