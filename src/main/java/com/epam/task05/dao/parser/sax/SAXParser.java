package com.epam.task05.dao.parser.sax;

import com.epam.task05.dao.parser.Parser;
import com.epam.task05.dao.parser.exception.ParserException;
import com.epam.task05.dao.parser.sax.handler.SAXHandlerMenu;
import com.epam.task05.bean.Dish;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class provides actions to parse XML file with technology of SAX parsers
 * and create list of dishes according to them category
 */
public class SAXParser implements Parser {

    /**
     * Method gets path to XML file parses it and return list of dishes according to them category
     *
     * @param xmlPath path to XML file
     * @return bean result list of dishes
     * @throws ParserException if there are exceptions during parsing
     */
    public HashMap<String, ArrayList<Dish>> parseXML(String xmlPath) throws ParserException {

        HashMap<String, ArrayList<Dish>> menu;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXHandlerMenu handlerMenu = new SAXHandlerMenu();
            reader.setContentHandler(handlerMenu);
            reader.parse(new InputSource(xmlPath));
            menu = handlerMenu.getMenu();
        } catch (SAXException | IOException e) {
            throw new ParserException(e);
        }

        return menu;
    }
}
