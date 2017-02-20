package com.epam.task05.service.impl;

import com.epam.task05.bean.Dish;
import com.epam.task05.dao.DAOMenu;
import com.epam.task05.dao.exception.DAOException;
import com.epam.task05.dao.factory.DAOFactory;
import com.epam.task05.service.MenuService;
import com.epam.task05.service.exception.MenuServiceException;
import com.epam.task05.service.exception.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class MenuServiceImpl implements MenuService {

    private static final String INIT_ERROR_TEXT = "Arguments are not initialized";
    private static final String VALIDATION_NAME_ERROR_TEXT = "Invalid name of dish";
    private static final String VALIDATION_PRICE_ERROR_TEXT = "Invalid price of dish";

    @Override
    public HashMap<String, ArrayList<Dish>> readFile(String filePath, String parser) throws MenuServiceException {
        HashMap<String, ArrayList<Dish>> menu;
        try {
            validateArguments(filePath, parser);
            DAOFactory factory = DAOFactory.getInstance();
            DAOMenu daoMenu = factory.getDAOMenu();
            menu = daoMenu.read(filePath, parser);
            for (Map.Entry<String, ArrayList<Dish>> category : menu.entrySet()) {
                for (Dish dish : category.getValue()) {
                    validateDish(dish);
                }
            }
        } catch (DAOException | ValidationException e) {
            throw new MenuServiceException(e);
        }

        return menu;
    }

    private void validateArguments(String... arguments) throws ValidationException {
        for (String argument : arguments) {
            if (argument == null || argument.isEmpty()) {
                throw new ValidationException(INIT_ERROR_TEXT);
            }
        }
    }

    private void validateDish(Dish dish) throws ValidationException {
        if (dish.getName() == null || dish.getName().isEmpty()) {
            throw new ValidationException(VALIDATION_NAME_ERROR_TEXT);
        }

        HashMap<String, Double> desription = dish.getDescription();
        for (Map.Entry<String, Double> tempDescription : desription.entrySet()) {
            double price = tempDescription.getValue();
            if (price <= 0) {
                throw new ValidationException(VALIDATION_PRICE_ERROR_TEXT);
            }
        }
    }
}
