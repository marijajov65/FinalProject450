package model.interfaces;

import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseEvent;

public interface IShape extends IUndoable{
    public void draw(PaintCanvasBase base);
    public void outline();
    public void deselect();
}

