package com.epam.task05.dao.parser.stax;

import com.epam.task05.bean.Dish;
import com.epam.task05.bean.MenuTags;
import com.epam.task05.exception.MenuException;
import com.epam.task05.dao.parser.Parser;
import com.epam.task05.dao.parser.exception.ParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class provides actions to parse XML file with technology of StAX parsers
 * and create list of dishes according to them category
 */
public class StAXParser implements Parser {

    XMLInputFactory factory = XMLInputFactory.newInstance();

    /**
     * List of categories of dishes
     */
    private ArrayList<String> category = new ArrayList<>();

    /**
     * Result list of dishes according to them category
     */
    private HashMap<String, ArrayList<Dish>> menu = new HashMap<>();

    /**
     * List of dishes of definite category
     */
    private LinkedList<Dish> dishes;

    private LinkedList<Dish> portionMass;

    private Dish dish;

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
    private String tagName;

    private final String ID_ATTRIBUTE = "id";

    /**
     * Method gets path to XML file parses it and return list of dishes according to them category
     *
     * @param xmlPath path to XML file
     * @return bean result list of dishes
     * @throws ParserException if there are exceptions during parsing
     */
    public HashMap<String, ArrayList<Dish>> parseXML(String xmlPath) throws ParserException {
        try {
            InputStream inputStream = new FileInputStream(xmlPath);
            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == StAXConstants.START_ELEMENT) {
                    tagName = reader.getLocalName();

                    if (tagName.equals(MenuTags.DISH)) {
                        dish = new Dish();
                        dish.setId(Integer.parseInt(reader.getAttributeValue(null, ID_ATTRIBUTE)));
                    } else if (category.contains(tagName)) {
                        dishes = new LinkedList<>();
                    } else if (tagName.equals(MenuTags.PORTION)) {
                        portionMass = new LinkedList<>();
                    }

                } else if (type == StAXConstants.CHARACTERS) {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        continue;
                    }

                    charactersAction(text);

                } else if (type == StAXConstants.END_ELEMENT) {
                    tagName = reader.getLocalName();
                    if (tagName.equals(MenuTags.DESCRIPTION)) {
                        dish.addDescription(description, Double.parseDouble(price));

                    } else if (tagName.equals(MenuTags.DISH)) {
                        dishes.add(dish);
                    }

                    for (String category : category) {
                        if (tagName.equals(category)) {
                            ArrayList<Dish> resultDishes = new ArrayList<>(dishes);
                            menu.put(category, resultDishes);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException | MenuException e) {
            throw new ParserException(e);
        }

        return menu;
    }

    private void charactersAction(String text) throws MenuException, ParserException {

        if (tagName.equals(MenuTags.MEAL_DESCRIPTION)) {
            description = text;

        } else if (tagName.equals(MenuTags.MEAL_PRICE)) {
            price = text;

        } else if (tagName.equals(MenuTags.PHOTO)) {
            dish.setPhotoURL(text);

        } else if (tagName.equals(MenuTags.NAME)) {
            dish.setName(text);

        } else if (tagName.equals(MenuTags.PORTION_COUNT)) {
            try {
                dish.setPortionCount(Integer.parseInt(text));
            } catch (NumberFormatException e) {
                throw new ParserException(e);
            }
        } else if (tagName.equals(MenuTags.PORTION_MASS)) {
            try {
                dish.addPortionMass(Integer.parseInt(text));
            } catch (NumberFormatException e) {
                throw new ParserException(e);
            }
        }
    }
}
