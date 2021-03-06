package controller;

import controller.shapes.*;
import controller.shapes.Shape;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

class CommandHistory {
    private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
    private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

    public static void add(IUndoable cmd) {
        undoStack.push(cmd);
        redoStack.clear();
    }

    public static boolean undo() {
        boolean result = !undoStack.empty();
        if (result) {
            IUndoable c = undoStack.pop();
            redoStack.push(c);
            c.undo();
        }
        return result;
    }

    public static boolean redo() {
        boolean result = !redoStack.empty();
        if (result) {
            IUndoable c = redoStack.pop();
            undoStack.push(c);
            c.redo();
        }
        return result;
    }

    public static boolean select(){
        ShapeListSelected.clearAll();
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(), ShapeList.getCanvas());
        FilledInRectangle boundingBox = ShapeListSelected.getBoundingBox();
        for(IShape shape:ShapeList.getList()){
            int sX = Math.min(shape.getStart().getX(), shape.getEnd().getX());
            int sY = Math.min(shape.getStart().getY(), shape.getEnd().getY());
            int bX = Math.min(boundingBox.getStart().getX(), boundingBox.getEnd().getX());
            int bY = Math.min(boundingBox.getStart().getY(), boundingBox.getEnd().getY());
            if(sX < bX + boundingBox.getWidth() &&
                    sX + shape.getWidth() > bX &&
                    sY < bY + boundingBox.getHeight() &&
                    sY + shape.getHeight() > bY){
                    //collision

                ShapeListSelected.push(shape);
                shape.outline();
            }
        }
        return true;
    }

    public static boolean move(){
        MC mc = new MC();
        for(IShape shape: ShapeList.getList()){
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            catch (ClassCastException c){
                try {
                    newShape = (IShape) (((Group) shape).clone());
                }catch(CloneNotSupportedException ee) {
                    ee.printStackTrace();
                }
            }
            mc.getList().add(newShape);
        }


        ShapeDrawer sd = new ShapeDrawer();
        int x = MoveOffset.getxOffset();
        int y = MoveOffset.getyOffset();
        ArrayList<IShape> toRemove = new ArrayList<>();
        ArrayList<IShape> toRemoveSelected = new ArrayList<>();
        ArrayList<IShape> toAdd = new ArrayList<>();

        for(IShape shape: ShapeList.getList()) {
            for (IShape sh : ShapeListSelected.getList()) {
                if (sh.equals(shape)) {
                    mc.getUndoSelectedList().add(shape);

                    IShape newShape = null;
                    try {
                        newShape = (IShape)(((Shape)shape).clone());
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    catch (ClassCastException c){
                        try {
                            newShape = (IShape) (((Group) shape).clone());
                        }catch(CloneNotSupportedException ee) {
                            ee.printStackTrace();
                        }
                    }
                    newShape.move(x,y);
                    toAdd.add(newShape);
                    mc.getRedoSelectedList().add(newShape);
                    toRemove.add(shape);
                    toRemoveSelected.add(sh);
                }
            }
        }
        ShapeListSelected.getList().removeAll(toRemoveSelected);
        ShapeListSelected.getList().addAll(toAdd);
        ShapeList.getList().removeAll(toRemove);
        ShapeList.getList().addAll(toAdd);

        for(IShape shape: ShapeList.getList()){
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            catch (ClassCastException c){
                try {
                    newShape = (IShape) (((Group) shape).clone());
                }catch(CloneNotSupportedException ee) {
                    ee.printStackTrace();
                }
            }
            mc.getRedoList().add(newShape);
        }
        add(mc);
        sd.render(ShapeList.getList(),MoveOffset.getCanvas());
        ShapeListSelected.outline();


        return true;
    }

    public static boolean copy(){
        Clipboard.getInstance().clearClipboard();
        for(IShape shape:ShapeListSelected.getList()) {
            try {
                Clipboard.getInstance().addToClipBoard(shape);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean paste(){
        PasteCommand pc = new PasteCommand();
        for(IShape shape: ShapeList.getList()){
            pc.addToUndoListPaste(shape);
        }
        ArrayList<IShape> copiedItems = Clipboard.getInstance().getCopiedItems();
        ShapeList.getList().addAll(copiedItems);
        for(IShape shape: ShapeList.getList()){
            pc.addToRedoListPaste(shape);
        }
        pc.pasteFromClipboard();
        for(IShape shape: ShapeListSelected.getList()){
            pc.addToSelectedPaste(shape);
        }
        add(pc);
        return true;
    }

    public static boolean delete(){
        DeleteCommand dc = new DeleteCommand();
        for(IShape shape: ShapeListSelected.getList()){
            for(IShape s: ShapeList.getList()){
                if(shape.equals(s)){
                    dc.getToDeleteList().add(s);
                }
            }
        }
        ShapeList.getList().removeAll(dc.getToDeleteList());
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());

        add(dc);
        return true;
    }

    public static boolean group(){
        if(ShapeListSelected.getList().size() == 0)return false;
        Group newGroup = new Group();
        IShape first = ShapeListSelected.getList().get(0);
        //find selected shapes
        int startX = first.getStart().getX();
        int startY = first.getStart().getY();
        int endX = first.getEnd().getX();
        int endY = first.getEnd().getY();

        ArrayList<IShape> toDeleteShapeList = new ArrayList<>();
        ArrayList<IShape> toDeleteShapeListSelected = new ArrayList<>();

        for(IShape shape: ShapeListSelected.getList()){
            for(IShape s:ShapeList.getList()){
                if(s.equals(shape)){
                    toDeleteShapeList.add(s);
                }
            }
            for(IShape s:ShapeListSelected.getList()){
                if(s.equals(shape)){
                    toDeleteShapeListSelected.add(s);
                }
            }
            newGroup.addChild(shape);
            int currStartX = shape.getStart().getX();
            int currStartY = shape.getStart().getY();
            int currEndX = shape.getEnd().getX();
            int currEndY = shape.getEnd().getY();

            if(currStartX<startX){
                startX = currStartX;
            }
            if(currEndX<startX){
                startX = currEndX;
            }
            if(currEndX>endX){
                endX = currEndX;
            }
            if(currStartX>endX){
                endX = currStartX;
            }
            if(currStartY<startY){
                startY = currStartY;
            }
            if(currEndY<startY){
                startY = currEndY;
            }
            if(currEndY>endY){
                endY = currEndY;
            }
            if(currStartY>endY){
                endY = currStartY;
            }
        }
        newGroup.setStart(startX,startY);
        newGroup.setEnd(endX,endY);
        newGroup.setWidth(Math.abs(startX-endX));
        newGroup.setHeight(Math.abs(startY-endY));

        //add group

        ShapeList.getList().removeAll(toDeleteShapeList);
        ShapeListSelected.getList().removeAll(toDeleteShapeListSelected);
        ShapeList.push(newGroup);
        ShapeListSelected.push(newGroup);

        newGroup.outline();

        return true;
    }

    public static boolean ungroup(){
        for(IShape shape: ShapeListSelected.getList()){
            shape.ungroup();
        }
        ShapeListSelected.clearAll();
        return true;
    }

}