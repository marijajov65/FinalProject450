package controller;

import controller.shapes.Shape;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class MoveCommand implements ICommand, IUndoable {
    private int x;
    private int y;
    private ArrayList<IShape> selected;

    public MoveCommand(int x, int y,ArrayList<IShape> selected ){
        this.x = x;
        this.y = y;
        this.selected = selected;
    }

    @Override
    public void run() {
        CommandHistory.move();
    }

    @Override
    public void undo() {
        //System.out.println("move undo");
        for(IShape shape: selected){
            Shape s = (Shape)shape;
            int startXCoo = s.getStart().getX();
            int startYCoo = s.getStart().getY();
            int endXCoo = s.getEnd().getX();
            int endYCoo = s.getEnd().getY();
            Point p  = new Point(x,y);
            s.setStart(startXCoo-p.getX(),startYCoo-p.getY());
            s.setEnd(endXCoo-p.getX(),endYCoo - p.getY());
            ShapeList.getList().add(shape);
        }

        ShapeDrawer sd = new ShapeDrawer();
        //sd.render(previousState,MoveOffset.getCanvas());

        sd.render(ShapeList.getList(), MoveOffset.getCanvas());
    }


    @Override
    public void redo() {
        System.out.println("move redo");

    }
}