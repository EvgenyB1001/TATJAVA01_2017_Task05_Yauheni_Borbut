package com.epam.task05.view;

import com.epam.task05.controller.MenuController;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class MenuPerformer {

    public void displayMenu(String command, String filePath, String parserName) {
        MenuController controller = new MenuController();
        String response = controller.executeRequest(command, filePath, parserName);
        System.out.print(response);
    }
}
