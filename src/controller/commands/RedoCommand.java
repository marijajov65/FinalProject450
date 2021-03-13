package controller.commands;

import controller.commands.CommandHistory;
import model.interfaces.ICommand;

public class RedoCommand implements ICommand {
    @Override
    public void run() {
        CommandHistory.redo();
    }
}

