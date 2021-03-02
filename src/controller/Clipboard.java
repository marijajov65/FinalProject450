package controller;

import controller.shapes.Shape;
import model.interfaces.IShape;

import java.util.ArrayList;

public class Clipboard {
    private static ArrayList<IShape> copiedItems = new ArrayList<IShape>();
    private static Clipboard instance = new Clipboard();
    private Clipboard(){ }
    public static Clipboard getInstance(){
        return instance;
    }

    public void addToClipBoard(IShape shape){
        Shape newShape = null;
        try {
            newShape = (Shape)((Shape)shape).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        newShape.setStart(newShape.getStart().getX()+50,newShape.getStart().getY()+50);
        newShape.setEnd(newShape.getEnd().getX()+50,newShape.getEnd().getY()+50);
        copiedItems.add((IShape) newShape);
    }

    public void clearClipboard(){
        copiedItems.clear();
    }
    public ArrayList<IShape> getCopiedItems(){
        return new ArrayList<>(copiedItems);
    }


}
