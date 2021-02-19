package controller;

import controller.shapes.FilledInRectangle;
import controller.shapes.Shape;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class ShapeListSelected {

    //ShapeList collection class
    private static final ArrayList<IShape> selectedList = new ArrayList<>();
    private static PaintCanvasBase canvas;
    private static FilledInRectangle boundingBox;

    public ShapeListSelected(PaintCanvasBase canvas){
        this.canvas = canvas;
    }

    public static void push(IShape shape) {
        if (shape == null) throw new IllegalArgumentException();
        selectedList.add(shape);
    }


    public static void outline()  {
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
        for(IShape shape:selectedList){
            shape.outline();
        }
    }


    public static void clearAll() {
        for(IShape shape:selectedList){
            shape.deselect();
        }
        selectedList.clear();
    }

    public static ArrayList<IShape> getList(){
        return selectedList;
    }

    public static void setBoundingBox(FilledInRectangle box){
        boundingBox = box;
    }

    public static FilledInRectangle getBoundingBox(){
        return boundingBox;
    }


}
