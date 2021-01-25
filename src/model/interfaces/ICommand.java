package model.interfaces;


import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.io.IOException;

public interface ICommand {
    void run(PaintCanvasBase canvas, ApplicationState appState) throws IOException;
}
