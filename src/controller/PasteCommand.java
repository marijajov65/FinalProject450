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

    public void addToRedoListPaste(IShape shape){
        redoListPaste.add(shape);
    }

    public void addToUndoListPaste(IShape shape){
        /*
        IShape newShape = null;
        try {
            newShape = (IShape)((Shape)shape).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

         */
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
        ArrayList<IShape> toRemove = new ArrayList<>();
        ShapeList.getList().clear();
        ShapeList.getList().addAll(undoListPaste);
    }

    @Override
    public void redo() {
        System.out.println("hello");
        ArrayList<IShape> toAdd = new ArrayList<>();
        for(IShape shape:redoListPaste){
            if(!ShapeList.getList().contains(shape)){
                toAdd.add(shape);
            }
        }
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(redoListPaste,ShapeList.getCanvas());
        ShapeList.getList().addAll(toAdd);
    }
}
