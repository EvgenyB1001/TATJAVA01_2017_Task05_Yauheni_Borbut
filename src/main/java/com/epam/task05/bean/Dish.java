package com.epam.task05.bean;

import com.epam.task05.bean.exception.MenuException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class contains info about definite dish
 */
public class Dish implements Serializable {

    public static final long serialVersionUID = 1L;

    private long id;
    private String photoURL;
    private String name;
    private ArrayList<Integer> portionMass = new ArrayList<>();
    private int portionCount;

    /**
     * List of descriptions of current dish
     */
    private HashMap<String, Double> description = new HashMap<>();

    private static final String INIT_EXCEPTION_TEXT = "Argument is not initialized";
    private static final String INVALID_NUMBER_FORMAT_TEXT = "Number format of price is not valid";

    public Dish() {
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method sets URL of image of current dish
     *
     * @param photoURL URL to image
     * @throws MenuException if <code>photoURL</code> isn't initialized
     */
    public void setPhotoURL(String photoURL) throws MenuException {
        if (photoURL == null) {
            throw new MenuException(INIT_EXCEPTION_TEXT);
        }

        this.photoURL = photoURL;
    }

    /**
     * Method sets name of current dish
     *
     * @param name name of current dish
     * @throws MenuException if <code>name</code> isn't initialized
     */
    public void setName(String name) throws MenuException {
        if (name == null) {
            throw new MenuException(INIT_EXCEPTION_TEXT);
        }

        this.name = name;
    }

    /**
     * Method add mass of current ingredient of dish
     *
     * @param portion mass of current ingredient
     */
    public void addPortionMass(int portion) {
        this.portionMass.add(portion);
    }

    public void setPortionCount(int count) {
        this.portionCount = count;
    }

    /**
     * Method sets description and price of current dish
     *
     * @param mealDescription description of current dish
     * @param argPrice           price of current dish
     * @throws MenuException if <code>price</code> or <code>mealDescription</code> isn't initialized
     */
    public void addDescription(String mealDescription, String argPrice) throws MenuException {
        if (mealDescription == null || argPrice == null) {
            throw new MenuException(INIT_EXCEPTION_TEXT);
        }

        double price;

        try {
            price = Double.parseDouble(argPrice);
        } catch (NumberFormatException e) {
            throw new MenuException(INVALID_NUMBER_FORMAT_TEXT);
        }

        description.put(mealDescription, price);
    }

    public long getId() {
        return id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getPortionMass() {
        return portionMass;
    }

    public int getPortionCount() {
        return portionCount;
    }

    public HashMap<String, Double> getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", photoURL='" + photoURL + '\'' +
                ", name='" + name + '\'' +
                ", portionMass=" + portionMass +
                ", portionCount=" + portionCount +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (portionCount != dish.portionCount) return false;
        if (photoURL != null ? !photoURL.equals(dish.photoURL) : dish.photoURL != null) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (portionMass != null ? !portionMass.equals(dish.portionMass) : dish.portionMass != null) return false;
        return description != null ? description.equals(dish.description) : dish.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (photoURL != null ? photoURL.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (portionMass != null ? portionMass.hashCode() : 0);
        result = 31 * result + portionCount;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
