package com.epam.task05.main;

import com.epam.task05.view.MenuPerformer;

public class Main {

    private static final String XML_PATH = "menu.xml";
    private static final String SAX_PARSER_NAME = "SAX";
    private static final String STAX_PARSER_NAME = "StAX";
    private static final String DOM_PARSER_NAME = "DOM";

    private static final String COMMAND_NAME = "read";

    public static void main(String[] args) {
        MenuPerformer performer = new MenuPerformer();
        //performer.displayMenu(COMMAND_NAME, XML_PATH, SAX_PARSER_NAME);
        //performer.displayMenu(COMMAND_NAME, XML_PATH, STAX_PARSER_NAME);
        performer.displayMenu(COMMAND_NAME, XML_PATH, DOM_PARSER_NAME);
    }
}
