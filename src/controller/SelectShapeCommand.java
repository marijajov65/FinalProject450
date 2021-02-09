package controller;

import model.interfaces.ICommand;

public class SelectShapeCommand implements ICommand {
    @Override
    public void run(){
        CommandHistory.select();
    }
}