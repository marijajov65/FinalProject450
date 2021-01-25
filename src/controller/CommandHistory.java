package controller;

import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.Stack;

class CommandHistory {
    private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
    private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

    public static void add(IUndoable cmd) {
        undoStack.push(cmd);
        //redoStack.clear();
    }

    public static boolean undo(PaintCanvasBase base, ApplicationState appState) {

        boolean result = !undoStack.empty();
        if (result) {
            IUndoable c = undoStack.pop();
            redoStack.push(c);
            c.undo();
        }
        return result;
    }

    public static boolean redo(PaintCanvasBase base, ApplicationState appState) {
        boolean result = !redoStack.empty();
        if (result) {
            IUndoable c = redoStack.pop();
            undoStack.push(c);
            c.redo();
        }
        return result;
    }

    public static boolean createShape(PaintCanvasBase canvas, ApplicationState appState) {
        ShapeColor primaryColor = appState.getActivePrimaryColor();
        ShapeType type = appState.getActiveShapeType();
        ShapeColor secondaryColor = appState.getActiveSecondaryColor();
        ShapeShadingType shadingType = appState.getActiveShapeShadingType();
        MouseMode mouseMode = appState.getActiveMouseMode();
/*
        IShape sb = new ShapeBuilder()
                .setPrimaryColor(primaryColor)
                .setSecondaryColor(secondaryColor)
                .setShadingType(shadingType)
                .setMouseMode(mouseMode)
                .setType(type)
                .setStart(start)
                .setEnd(end)
                .setHeight(Math.abs(end.getY() - start.getY()))
                .setWidth(Math.abs(end.getX() - start.getX()))
                .setCanvas(canvas)
                .buildShape();

 */
return true;
    }
}

