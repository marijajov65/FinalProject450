package controller;

import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class UndoCommand implements ICommand {
    @Override
    public void run(PaintCanvasBase base, ApplicationState appState){
        CommandHistory.undo(base, appState);
    }
}
