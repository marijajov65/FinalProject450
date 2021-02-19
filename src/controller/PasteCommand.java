package controller;

import controller.shapes.Shape;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.io.IOException;
import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {
    private ArrayList<IShape> undoListPaste = new ArrayList<>();
    private ArrayList<IShape> redoListPaste = new ArrayList<>();
    private ArrayList<IShape> selected = new ArrayList<>();

    public void addToSelectedPaste(IShape shape){
        selected.add(shape);
    }
    public void addToRedoListPaste(IShape shape){
        redoListPaste.add(shape);
    }

    public void addToUndoListPaste(IShape shape){
        undoListPaste.add(shape);
    }

    public void pasteFromClipboard(){
        ShapeDrawer sd = new ShapeDrawer();
        sd.drawSelected(redoListPaste,ShapeList.getCanvas());
    }

    @Override
    public void run() throws IOException {
        CommandHistory.paste();
    }

    @Override
    public void undo() {
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(undoListPaste,ShapeList.getCanvas());
        ShapeList.getList().clear();
        ShapeList.getList().addAll(undoListPaste);
        for(IShape shape: selected){
            shape.outline();
        }
    }

    @Override
    public void redo() {
        ArrayList<IShape> toAdd = new ArrayList<>();
        for(IShape shape:redoListPaste){
            if(!ShapeList.getList().contains(shape)){
                toAdd.add(shape);
            }
        }
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(redoListPaste,ShapeList.getCanvas());
        ShapeList.getList().addAll(toAdd);
        for(IShape shape: selected){
            shape.outline();
        }
    }
}
