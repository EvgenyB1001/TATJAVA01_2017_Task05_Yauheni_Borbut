package com.epam.task05.controller;

import com.epam.task05.controller.command.Command;
import com.epam.task05.controller.command.impl.ReadFileCommand;
import com.epam.task05.controller.exception.CommandNotFindException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class CommandProvider {

    private final HashMap<String, Command> commands = new HashMap<>();
    private static final String READ_COMMAND_NAME = "read";
    private static final String COMMAND_NOT_FOUND_TEXT = "No such command";

    private static CommandProvider instance;

    {
        commands.put(READ_COMMAND_NAME, new ReadFileCommand());
    }

    private CommandProvider() {}

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) throws CommandNotFindException {
        for (Map.Entry<String, Command> command : commands.entrySet()) {
            if (command.getKey().equals(commandName)) {
                return command.getValue();
            }
        }

        throw new CommandNotFindException(COMMAND_NOT_FOUND_TEXT );
    }
}
