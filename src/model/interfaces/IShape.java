package model.interfaces;

import controller.Point;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseEvent;

public interface IShape extends IUndoable{
    public void move(int x,int y);
    public void draw(PaintCanvasBase base);
    public boolean equals(IShape shape);
    public void outline();
    public void deselect();
    public Point getStart();
    public Point getEnd();
    public void setStart(int x,int y);
    public void setEnd(int x,int y);
    public int getWidth();
    public int getHeight();
    public void ungroup();
    public Object clone() throws CloneNotSupportedException;
    }


