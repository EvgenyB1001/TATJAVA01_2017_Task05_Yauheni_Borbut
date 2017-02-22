package com.epam.task05.dao.parser.dom;

import com.epam.task05.bean.Dish;
import com.epam.task05.bean.MenuTags;
import com.epam.task05.exception.MenuException;
import com.epam.task05.dao.parser.dom.exception.DOMException;
import com.epam.task05.dao.parser.Parser;
import com.epam.task05.dao.parser.exception.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class provides actions to parse XML file with technology of DOM parsers
 * and create list of dishes according to them category
 */
public class DOMParser implements Parser {

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
    private ArrayList<Dish> dishes;

    private static final String DOM_EXCEPTION_TEXT = "No element with such tag";
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

    private final String ID_ATTRIBUTE = "id";

    /**
     * Method gets path to XML file parses it and return list of dishes according to them category
     *
     * @param xmlPath path to XML file
     * @return bean result list of dishes
     * @throws ParserException if there are exceptions during parsing
     */
    @Override
    public HashMap<String, ArrayList<Dish>> parseXML(String xmlPath) throws ParserException {
        try {
            com.sun.org.apache.xerces.internal.parsers.DOMParser parser = new com.sun.org.apache.xerces.internal.parsers.DOMParser();
            parser.parse(xmlPath);
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            for (String category : category) {
                Element categoryElement = getSingleElement(root, category);
                dishes = new ArrayList<>();
                NodeList categoryDishes = categoryElement.getElementsByTagName(MenuTags.DISH);
                for (int i = 0; i < categoryDishes.getLength(); i++) {
                    dish = new Dish();
                    Element dishElement = (Element) categoryDishes.item(i);
                    dish.setId(Integer.parseInt(dishElement.getAttribute(ID_ATTRIBUTE)));
                    dish.setName(getSingleElement(dishElement, MenuTags.NAME).getTextContent().trim());
                    dish.setPhotoURL(getSingleElement(dishElement, MenuTags.PHOTO).getTextContent().trim());
                    setPortion(dish, dishElement);
                    setDescriptionAndPrice(dish, dishElement);
                    dishes.add(dish);
                }
                menu.put(category, dishes);
            }
        } catch (SAXException | DOMException | IOException | MenuException e) {
            throw new ParserException(e);
        }

        return menu;
    }

    /**
     * Method return single element from list of nodes
     *
     * @param root    root element to list of nodes
     * @param tagName tag to create node list
     * @return requiredElement single element from node list
     * @throws DOMException if there are no element with current tag in XML file
     */
    private Element getSingleElement(Element root, String tagName) throws DOMException {
        NodeList list = root.getElementsByTagName(tagName);
        if (list == null) {
            throw new DOMException(DOM_EXCEPTION_TEXT);
        }

        Element requiredElement = (Element) list.item(0);
        return requiredElement;
    }

    private void setPortion(Dish dish, Element root) throws MenuException, DOMException {

        Element portionCount = getSingleElement(root, MenuTags.PORTION_COUNT);

        if (portionCount != null) {
            try {
                int portionCountValue = Integer.parseInt(portionCount.getTextContent().trim());
                dish.setPortionCount(portionCountValue);
            } catch (NumberFormatException e) {
                throw new MenuException(e);
            }

        } else {
            NodeList portionMassAttributes = root.getElementsByTagName(MenuTags.PORTION_MASS);
            for (int i = 0; i < portionMassAttributes.getLength(); i++) {
                Element portionMass = (Element) portionMassAttributes.item(i);
                try {
                    int portionMassValue = Integer.parseInt(portionMass.getTextContent().trim());
                    dish.addPortionMass(portionMassValue);
                } catch (NumberFormatException e) {
                    throw new MenuException(e);
                }
            }
        }
    }

    /**
     * Method set description and price to current object of dish, got as argument
     *
     * @param root root element to create node list
     * @param dish object of dish to set description and price
     * @throws DOMException if there errors in reading XML file
     */
    private void setDescriptionAndPrice(Dish dish, Element root) throws MenuException, DOMException {
        NodeList descriptionList = root.getElementsByTagName(MenuTags.DESCRIPTION);
        for (int i = 0; i < descriptionList.getLength(); i++) {
            Element descriptionElement = (Element) descriptionList.item(i);
            String mealDescription = getSingleElement(descriptionElement,
                    MenuTags.MEAL_DESCRIPTION).getTextContent().trim();
            String mealPrice = getSingleElement(descriptionElement,
                    MenuTags.MEAL_PRICE).getTextContent().trim();
            try {
                dish.addDescription(mealDescription, Double.parseDouble(mealPrice));
            } catch (NumberFormatException e) {
                throw new MenuException(e);
            }
        }
    }
}
