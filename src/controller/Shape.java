package controller;

import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

public abstract class Shape implements IUndoable {
    private PaintCanvasBase canvas;

    public abstract void draw(PaintCanvasBase canvasBase);

    @Override
    public void undo() {
        canvas.getGraphics2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        ShapeList.pop();
    }

    @Override
    public void redo() {
        canvas.getGraphics2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        ShapeList.push((IShape)this);
    }
}
