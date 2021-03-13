package controller.commands;

import controller.MoveOffset;
import controller.ShapeDrawer;
import controller.ShapeList;
import controller.ShapeListSelected;
import controller.shapes.Shape;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MoveCommand implements IUndoable, ICommand {
    private ArrayList<IShape> list = new ArrayList<IShape>();
    private ArrayList<IShape> redoList = new ArrayList<IShape>();
    private ArrayList<IShape> undoSelected = new ArrayList<>();
    private ArrayList<IShape> redoSelected = new ArrayList<>();


    public ArrayList<IShape> getList(){
        return list;
    }
    public ArrayList<IShape> getRedoList(){
        return redoList;
    }
    public ArrayList<IShape> getUndoSelectedList(){
        return undoSelected;
    }
    public ArrayList<IShape> getRedoSelectedList(){
        return redoSelected;
    }

    /*
    public MoveCommand(int x, int y,ArrayList<IShape> selected ){
        this.x = x;
        this.y = y;
        this.selected = selected;
    }

     */

    @Override
    public void run() {
        CommandHistory.move();
    }

    @Override
    public void undo() {
        ShapeDrawer sd = new ShapeDrawer();
        ShapeList.getList().clear();
        for(IShape shape: list){
            ShapeList.getList().add(shape);
        }
        sd.render(list, MoveOffset.getCanvas());
        ShapeListSelected.getList().clear();


        for(IShape shape:undoSelected){
            ShapeListSelected.getList().add(shape);
            shape.outline();
        }


    }

    @Override
    public void redo() {
        ShapeList.getList().clear();
        for(IShape shape: redoList){
            ShapeList.getList().add(shape);
        }
        ShapeListSelected.getList().clear();
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(redoList,MoveOffset.getCanvas());

        for(IShape shape:redoSelected){
            ShapeListSelected.getList().add(shape);
            shape.outline();
        }
    }
}
