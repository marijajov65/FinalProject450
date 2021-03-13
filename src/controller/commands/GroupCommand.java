package controller.commands;

import controller.commands.CommandHistory;
import model.interfaces.ICommand;

import java.io.IOException;

public class GroupCommand implements ICommand {
    @Override
    public void run() throws IOException {
        CommandHistory.group();
    }
}
