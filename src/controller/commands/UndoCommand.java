package controller.commands;

import controller.commands.CommandHistory;
import model.interfaces.ICommand;

public class UndoCommand implements ICommand {
    @Override
    public void run(){
        CommandHistory.undo();
    }
}
