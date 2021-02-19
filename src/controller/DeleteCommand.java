package controller;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand implements ICommand, IUndoable {
    ArrayList<IShape> toDelete = new ArrayList<IShape>();

    public ArrayList<IShape> getToDeleteList(){
        return toDelete;
    }

    @Override
    public void run() throws IOException {
        CommandHistory.delete();
    }

    @Override
    public void undo() {
        ShapeList.getList().addAll(toDelete);
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
        for(IShape shape:toDelete){
            ShapeListSelected.getList().add(shape);
            shape.outline();
        }
    }

    @Override
    public void redo() {
        System.out.println(ShapeList.getList().size());
        ShapeList.getList().removeAll(toDelete);
        ShapeListSelected.getList().removeAll(toDelete);
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
        System.out.println(ShapeList.getList().size());

    }
}
