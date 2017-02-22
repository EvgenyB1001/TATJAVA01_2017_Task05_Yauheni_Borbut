package com.epam.task05.bean;

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
     */
    public void setPhotoURL(String photoURL){
        this.photoURL = photoURL;
    }

    /**
     * Method sets name of current dish
     *
     * @param name name of current dish
     */
    public void setName(String name) {
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
     */
    public void addDescription(String mealDescription, double argPrice) {
        double price;
        price = argPrice;

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
