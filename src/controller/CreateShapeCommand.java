package controller;

import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import java.io.IOException;

public class CreateShapeCommand implements ICommand {
    @Override
    public void run(PaintCanvasBase canvas, ApplicationState appState) throws IOException {
        CommandHistory.createShape(canvas,appState);
    }
}
