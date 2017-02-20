package com.epam.task05.controller;


import com.epam.task05.controller.command.Command;
import com.epam.task05.controller.exception.CommandNotFindException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class outputs information, got from XML file
 */
public class MenuController {

    /**
     * Object of logger
     */
    private static final Logger logger = LogManager.getLogger();
    private static final String FAIL_RESPONSE_MESSAGE = "Some errors while parsing XML file";

    /**
     * Method gets path to file and name of parser, and send it to definite command to execute
     *
     * @param parser      name of parser to parse
     * @param filePath    path to file
     * @param commandName command's name
     */
    public String executeRequest(String commandName, String filePath, String parser) {
        CommandProvider provider = null;
        String response = null;
        try {
            provider = CommandProvider.getInstance();
            Command command = provider.getCommand(commandName);
            response = command.execute(filePath, parser);
        } catch (CommandNotFindException e) {
            logger.error(e);
            response = FAIL_RESPONSE_MESSAGE;
        }

        return response;
    }

}
