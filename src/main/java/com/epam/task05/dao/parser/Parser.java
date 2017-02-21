package com.epam.task05.dao.parser;

import com.epam.task05.bean.Dish;
import com.epam.task05.dao.exception.DAOException;
import com.epam.task05.dao.parser.exception.ParserException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yauheni_Borbut on 2/15/2017.
 */
public interface Parser {

    HashMap<String, ArrayList<Dish>> parseXML(String xmlPath) throws ParserException;
}
