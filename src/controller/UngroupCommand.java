package controller;

import model.interfaces.ICommand;

import java.io.IOException;

public class UngroupCommand implements ICommand {
    @Override
    public void run() throws IOException {
        CommandHistory.ungroup();
    }
}
