package com.epam.task05.dao.impl;

import com.epam.task05.bean.Dish;
import com.epam.task05.dao.DAOMenu;
import com.epam.task05.dao.exception.DAOException;
import com.epam.task05.dao.parser.Parser;
import com.epam.task05.dao.parser.ParserFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class DAOMenuXML implements DAOMenu{

    @Override
    public HashMap<String, ArrayList<Dish>> read(String filePath, String parserName) throws DAOException {
        ParserFactory factory = ParserFactory.getInstance();
        Parser parser = factory.getParser(parserName);
        HashMap<String, ArrayList<Dish>> menu = parser.parseXML(filePath);
        return menu;
    }
}
