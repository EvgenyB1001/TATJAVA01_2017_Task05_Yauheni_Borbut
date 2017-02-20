package com.epam.task05.service.factory;

import com.epam.task05.service.impl.MenuServiceImpl;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class MenuServiceFactory {

    private static MenuServiceFactory instance;

    private final MenuServiceImpl dao = new MenuServiceImpl();

    private MenuServiceFactory() {}

    /**
     * Singleton implementation
     */
    public static MenuServiceFactory getInstance() {
        if (instance == null) {
            instance = new MenuServiceFactory();
        }
        return instance;
    }

    public MenuServiceImpl getMenuService() {
        return dao;
    }
}
