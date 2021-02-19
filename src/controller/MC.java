package controller;

import controller.shapes.Shape;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MC implements IUndoable {
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

    @Override
    public void undo() {
        ShapeDrawer sd = new ShapeDrawer();
        ShapeList.getList().clear();
        for(IShape shape: list){
            /*
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

             */
            ShapeList.getList().add(shape);
            shape.outline();
        }
        sd.render(list,MoveOffset.getCanvas());
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
            /*
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

             */
            ShapeList.getList().add(shape);
        }
        ShapeListSelected.getList().clear();
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(redoList,MoveOffset.getCanvas());
        //ShapeListSelected.outline();
        for(IShape shape:redoSelected){
            ShapeListSelected.getList().add(shape);
            shape.outline();
        }
    }
}
