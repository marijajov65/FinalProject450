package main;

import controller.*;
import model.ShapeType;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.EventName;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        ShapeList sl = new ShapeList(paintCanvas);
        MouseListener ml = new MouseListener(appState, paintCanvas);
        paintCanvas.addMouseListener(ml);
        MouseListenerCommands mlc = new MouseListenerCommands();
        IJPaintController controller = new JPaintController(uiModule, appState);
        guiWindow.getButton(EventName.UNDO).addMouseListener(mlc);
        guiWindow.getButton(EventName.REDO).addMouseListener(mlc);
        guiWindow.getButton(EventName.COPY).addMouseListener(mlc);
        guiWindow.getButton(EventName.PASTE).addMouseListener(mlc);
        guiWindow.getButton(EventName.DELETE).addMouseListener(mlc);
        guiWindow.getButton(EventName.GROUP).addMouseListener(mlc);
        guiWindow.getButton(EventName.UNGROUP).addMouseListener(mlc);
        controller.setup();


        // For example purposes only; remove all lines below from your final project.

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
