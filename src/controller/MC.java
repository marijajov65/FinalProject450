package controller;

import controller.shapes.Shape;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.ArrayList;

public class MC implements IUndoable {
    private ArrayList<IShape> list = new ArrayList<IShape>();
    private ArrayList<IShape> redoList = new ArrayList<IShape>();


    public ArrayList<IShape> getList(){
        return list;
    }
    public ArrayList<IShape> getRedoList(){
        return redoList;
    }

    @Override
    public void undo() {
        System.out.println("move undo");
        ShapeDrawer sd = new ShapeDrawer();
        ShapeList.getList().clear();
        for(IShape shape: list){
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            ShapeList.getList().add(newShape);
        }



        sd.render(list,MoveOffset.getCanvas());
    }

    @Override
    public void redo() {
        System.out.println("move redo");

        ShapeList.getList().clear();
        for(IShape shape: redoList){
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            ShapeList.getList().add(newShape);
        }

        ShapeDrawer sd = new ShapeDrawer();
        sd.render(redoList,MoveOffset.getCanvas());
    }
}
