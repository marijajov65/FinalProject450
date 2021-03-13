package controller.commands;

import controller.ShapeDrawer;
import controller.ShapeList;
import controller.ShapeListSelected;
import controller.commands.CommandHistory;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.io.IOException;
import java.util.ArrayList;

public class UngroupCommand implements ICommand, IUndoable {
    private ArrayList<IShape>undoList = new ArrayList<>();
    private ArrayList<IShape>redoList = new ArrayList<>();
    @Override
    public void run() throws IOException {
        CommandHistory.ungroup();
    }

    public void addToUndoList(IShape shape){
        undoList.add(shape);
    }

    public void addToRedoList(IShape shape) {
        redoList.add(shape);
    }

    @Override
    public void undo() {
        ShapeListSelected.getList().clear();
        ShapeList.getList().clear();
        for(IShape shape: undoList){
            ShapeList.getList().add(shape);
        }
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
    }

    @Override
    public void redo() {
        ShapeListSelected.getList().clear();
        ShapeList.getList().clear();
        for(IShape shape: redoList){
            ShapeList.getList().add(shape);
        }
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
    }

}
