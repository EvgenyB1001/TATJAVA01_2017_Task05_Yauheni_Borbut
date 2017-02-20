package com.epam.task05.dao.parser.sax.handler;

import com.epam.task05.bean.Dish;
import com.epam.task05.bean.MenuTags;
import com.epam.task05.bean.exception.MenuException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class-handler to SAX parsers
 */
public class SAXHandlerMenu extends DefaultHandler{

    /**
     * Result list of dishes according to them category
     */
    private HashMap<String, ArrayList<Dish>> menu = new HashMap<>();

    /**
     * List of dishes of definite category
     */
    private LinkedList<Dish> dishes;

    private LinkedList<Integer> portionMass;

    private Dish dish;

    private StringBuilder stringBuilder;

    /**
     * List of categories of dishes
     */
    private ArrayList<String> category = new ArrayList<>();

    {
        category.add(MenuTags.COLD_SNACKS);
        category.add(MenuTags.HOT_SNACKS);
        category.add(MenuTags.BREAKFASTS);
        category.add(MenuTags.SALADS);
        category.add(MenuTags.SOUPS);
        category.add(MenuTags.FISH_DISHES);
        category.add(MenuTags.MEAT_DISHES);
        category.add(MenuTags.GARNISHES);
        category.add(MenuTags.GRILL_DISHES);
        category.add(MenuTags.FROM_THE_CHIEF);
        category.add(MenuTags.ATTACHMENT);
        category.add(MenuTags.DESERT);
    }

    private String description;
    private String price;

    private final String ID_ATTRIBUTE = "id";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder = new StringBuilder();
        if (qName.equals(MenuTags.DISH)) {
            dish = new Dish();
            dish.setId(Integer.parseInt(attributes.getValue(ID_ATTRIBUTE)));

        } else if (qName.equals(MenuTags.PORTION)) {
            portionMass = new LinkedList<>();

        } else if (category.contains(qName)) {
            dishes = new LinkedList<>();

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        try {
            if (qName.equals(MenuTags.MEAL_DESCRIPTION)) {
                description = stringBuilder.toString();

            } else if (qName.equals(MenuTags.MEAL_PRICE)) {
                price = stringBuilder.toString();

            } else if (qName.equals(MenuTags.DESCRIPTION)) {
                dish.addDescription(description, price);

            } else if (qName.equals(MenuTags.PHOTO)) {
                dish.setPhotoURL(stringBuilder.toString());

            } else if (qName.equals(MenuTags.NAME)) {
                dish.setName(stringBuilder.toString());

            } else if (qName.equals(MenuTags.PORTION_MASS)) {
                dish.addPortionMass(Integer.parseInt(stringBuilder.toString()));

            } else if (qName.equals(MenuTags.PORTION_COUNT)) {
                dish.setPortionCount(Integer.parseInt(stringBuilder.toString()));

            } else if (qName.equals(MenuTags.DISH)) {
                dishes.add(dish);
            }
        } catch (NumberFormatException | MenuException e) {
            throw new SAXException(e);
        }

        for (String category : category) {
            if (qName.equals(category)) {
                ArrayList<Dish> resultDishes = new ArrayList<>(dishes);
                menu.put(category, resultDishes);
            }
        }
    }

    public HashMap<String, ArrayList<Dish>> getMenu() {
        return menu;
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.print(e.getMessage());
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.print(e.getMessage());
    }
}
