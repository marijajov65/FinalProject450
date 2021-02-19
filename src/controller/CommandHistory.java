package controller;

import controller.shapes.FilledInRectangle;
import controller.shapes.OutlinedShape;
import controller.shapes.Shape;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

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
            Shape s = (Shape)shape;
            int sX = Math.min(s.getStart().getX(), s.getEnd().getX());
            int sY = Math.min(s.getStart().getY(), s.getEnd().getY());
            int bX = Math.min(boundingBox.getStart().getX(), boundingBox.getEnd().getX());
            int bY = Math.min(boundingBox.getStart().getY(), boundingBox.getEnd().getY());
            if(sX < bX + boundingBox.getWidth() &&
                    sX + s.getWidth() > bX &&
                    sY < bY + boundingBox.getHeight() &&
                    sY + s.getHeight() > bY){
                    //collision
                ShapeListSelected.push(shape);
                ShapeListSelected.outline();
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
            mc.getList().add(newShape);
        }

        ShapeDrawer sd = new ShapeDrawer();
        int x = MoveOffset.getxOffset();
        int y = MoveOffset.getyOffset();
        ArrayList<IShape> toRemove = new ArrayList<>();
        ArrayList<IShape> toAdd = new ArrayList<>();
        for(IShape shape: ShapeList.getList()){
            if(ShapeListSelected.getList().contains(shape)){
                mc.getUndoSelectedList().add(shape);
                ShapeListSelected.getList().remove(shape);
                Shape s = (Shape)shape;
                Point p  = new Point(x,y);

                IShape newShape = null;
                try {
                    newShape = (IShape)(s).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                Shape ns = (Shape)newShape;
                ns.setStart(p.getX()+ns.getStart().getX(),p.getY()+ns.getStart().getY());
                ns.setEnd(p.getX()+ns.getEnd().getX(),p.getY()+ns.getEnd().getY());
                toAdd.add((IShape) ns);
                mc.getRedoSelectedList().add((IShape) ns);
                toRemove.add(shape);
                ShapeListSelected.getList().add((IShape) ns);
            }
        }
        ShapeList.getList().removeAll(toRemove);
        ShapeList.getList().addAll(toAdd);

        for(IShape shape: ShapeList.getList()){
            IShape newShape = null;
            try {
                newShape = (IShape)(((Shape)shape).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            mc.getRedoList().add(newShape);
        }
        add(mc);
        sd.render(ShapeList.getList(),MoveOffset.getCanvas());
        ShapeListSelected.outline();


        return true;
    }

    public static boolean copy(){
        CopyCommand cc = new CopyCommand();
        Clipboard.clearClipboard();
        for(IShape shape:ShapeListSelected.getList()) {
            Clipboard.addToClipBoard(shape);
            //cc.addToRedoClipboard(shape);
        }
        //add(cc);
        return true;
    }

    public static boolean paste(){
        PasteCommand pc = new PasteCommand();
        for(IShape shape: ShapeList.getList()){
            pc.addToUndoListPaste(shape);
        }
        ArrayList<IShape> copiedItems = Clipboard.getCopiedItems();
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
                if(((Shape)shape).equals((Shape)s)){
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



}