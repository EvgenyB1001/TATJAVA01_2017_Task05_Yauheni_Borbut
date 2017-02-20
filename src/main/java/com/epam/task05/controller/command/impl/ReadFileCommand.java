package com.epam.task05.controller.command.impl;

import com.epam.task05.bean.Dish;
import com.epam.task05.bean.MenuNames;
import com.epam.task05.controller.command.Command;
import com.epam.task05.service.exception.MenuServiceException;
import com.epam.task05.service.factory.MenuServiceFactory;
import com.epam.task05.service.impl.MenuServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class ReadFileCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String UNPREDICTABLE_ERROR_TEXT = "Unpredictable error";
    private static final String FAIL_RESPONSE_MESSAGE = "Some errors while parsing XML file";

    private static final String NAME_TEXT = "Название: ";
    private static final String PORTION_TEXT = "Порция(грамм): ";
    private static final String DESCRIPTION_TEXT = "Описание: ";
    private static final String PRICE_TEXT = "Цена(руб): ";
    private static final String PORTION_COUNT_TEXT = " шт";

    private static final String CATEGORY_SEPARATOR = "--------------------------";
    private static final String PARAMETER_SEPARATOR = " | ";
    private static final String PORTION_MASS_SEPARATOR = "/";

    private static final String NEXT_LINE_SEPARATOR = "\n";


    @Override
    public String execute(String filePath, String parser) {
        MenuServiceFactory factory = MenuServiceFactory.getInstance();
        MenuServiceImpl menuService = factory.getMenuService();
        String response = null;
        StringBuilder builder = new StringBuilder();
        try {
            HashMap<String, ArrayList<Dish>> menu = menuService.readFile(filePath, parser);
            for (Map.Entry<String, ArrayList<Dish>> entry : menu.entrySet()) {
                builder.append(createResponse(MenuNames.CATEGORY_TRANSLATE.get(entry.getKey()), entry));
            }

            response = builder.toString();

        } catch (MenuServiceException e) {
            logger.error(e);
            response = FAIL_RESPONSE_MESSAGE;
        } catch (Exception e) {
            logger.error(UNPREDICTABLE_ERROR_TEXT, e);
            response = FAIL_RESPONSE_MESSAGE;
        }

        return response;
    }

    /**
     * Method creates another line with dishes of current category
     *
     * @param type       type of category
     * @param dishesList list of dishes of current category
     */
    private String createResponse(String type, Map.Entry<String, ArrayList<Dish>> dishesList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CATEGORY_SEPARATOR).append(NEXT_LINE_SEPARATOR).append(type).append(NEXT_LINE_SEPARATOR);

        for (Dish dish : dishesList.getValue()) {
            stringBuilder.append(NAME_TEXT).append(dish.getName()).append(PARAMETER_SEPARATOR).append(PORTION_TEXT);

            if (dish.getPortionCount() != 0) {
                stringBuilder.append(dish.getPortionCount()).append(PORTION_COUNT_TEXT).append(PARAMETER_SEPARATOR);
            } else if (dish.getPortionMass().size() != 0) {
                for (int mass : dish.getPortionMass()) {
                    stringBuilder.append(mass).append(PORTION_MASS_SEPARATOR);
                }
                stringBuilder.append(PARAMETER_SEPARATOR);
            }

            HashMap<String, Double> description = dish.getDescription();

            for (Map.Entry<String, Double> descript : description.entrySet()) {
                stringBuilder.append(DESCRIPTION_TEXT).append(descript.getKey()).append(PARAMETER_SEPARATOR)
                        .append(PRICE_TEXT).append(descript.getValue()).append(PARAMETER_SEPARATOR);
            }
            stringBuilder.append(NEXT_LINE_SEPARATOR);
        }
        stringBuilder.append(NEXT_LINE_SEPARATOR);
        return stringBuilder.toString();
    }
}
