package controller;

import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class RedoCommand implements ICommand {
    @Override
    public void run(PaintCanvasBase canvas, ApplicationState appState) {
        CommandHistory.redo(canvas, appState);
    }
}

