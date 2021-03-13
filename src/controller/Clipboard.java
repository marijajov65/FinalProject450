package controller;

import controller.shapes.Group;
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

    public void addToClipBoard(IShape shape) throws CloneNotSupportedException {
        IShape newShape = (IShape)(shape).clone();
        if(newShape instanceof Group){
            Group group = (Group)newShape;
            for(IShape sh: group.getChildren()){
                sh.setStart(sh.getStart().getX()+50, sh.getStart().getY()+50);
                sh.setEnd(sh.getEnd().getX()+50,sh.getEnd().getY()+50);
            }
            newShape = group;
        }
        newShape.setStart(newShape.getStart().getX()+50, newShape.getStart().getY()+50);
        newShape.setEnd(newShape.getEnd().getX()+50,newShape.getEnd().getY()+50);
        /*
        if(newShape instanceof Group){
            ((Group)newShape).moveChildren();
        }

         */
        copiedItems.add(newShape);
    }

    public void clearClipboard(){
        copiedItems.clear();
    }
    public ArrayList<IShape> getCopiedItems(){
        return copiedItems;
    }


}
