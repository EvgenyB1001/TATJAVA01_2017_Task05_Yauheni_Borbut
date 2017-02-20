package com.epam.task05.bean;


import java.util.HashMap;

/**
 * Class contains constant lines of russian names of categories
 */
public class MenuNames {

    public static final String COLD_SNACKS = "Холодные закуски";
    public static final String HOT_SNACKS = "Горячие закуски";
    public static final String BREAKFASTS = "Завтраки";
    public static final String SALADS = "Салаты";
    public static final String SOUPS = "Супы";
    public static final String FISH_DISHES = "Рыбные блюда";
    public static final String MEAT_DISHES = "Мясные блюда";
    public static final String GARNISHES = "Гарниры";
    public static final String GRILL_DISHES = "Блюда на мангале";
    public static final String FROM_THE_CHIEF = "От шеф-повара";
    public static final String ATTACHMENT = "Приложение";
    public static final String DESERT = "Десерт";

    /**
     * List of pairs of tags and russian interpretations
     */
    public static HashMap<String, String> CATEGORY_TRANSLATE = new HashMap<>();

    static {
        CATEGORY_TRANSLATE.put(MenuTags.COLD_SNACKS, MenuNames.COLD_SNACKS);
        CATEGORY_TRANSLATE.put(MenuTags.HOT_SNACKS, MenuNames.HOT_SNACKS);
        CATEGORY_TRANSLATE.put(MenuTags.BREAKFASTS, MenuNames.BREAKFASTS);
        CATEGORY_TRANSLATE.put(MenuTags.FISH_DISHES, MenuNames.FISH_DISHES);
        CATEGORY_TRANSLATE.put(MenuTags.MEAT_DISHES, MenuNames.MEAT_DISHES);
        CATEGORY_TRANSLATE.put(MenuTags.SALADS, MenuNames.SALADS);
        CATEGORY_TRANSLATE.put(MenuTags.SOUPS, MenuNames.SOUPS);
        CATEGORY_TRANSLATE.put(MenuTags.FROM_THE_CHIEF, MenuNames.FROM_THE_CHIEF);
        CATEGORY_TRANSLATE.put(MenuTags.GARNISHES, MenuNames.GARNISHES);
        CATEGORY_TRANSLATE.put(MenuTags.GRILL_DISHES, MenuNames.GRILL_DISHES);
        CATEGORY_TRANSLATE.put(MenuTags.DESERT, MenuNames.DESERT);
        CATEGORY_TRANSLATE.put(MenuTags.ATTACHMENT, MenuNames.ATTACHMENT);
    }
}
