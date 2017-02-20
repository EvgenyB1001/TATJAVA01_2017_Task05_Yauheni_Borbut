package com.epam.task05.dao.factory;

import com.epam.task05.dao.impl.DAOMenuXML;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class DAOFactory {

    private static DAOFactory instance;

    private final DAOMenuXML dao = new DAOMenuXML();

    private DAOFactory() {}

    /**
     * Singleton implementation
     */
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public DAOMenuXML getDAOMenu() {
        return dao;
    }
}
