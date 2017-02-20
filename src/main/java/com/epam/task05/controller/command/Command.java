package com.epam.task05.controller.command;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public interface Command {

    String execute(String file, String filePath);
}
