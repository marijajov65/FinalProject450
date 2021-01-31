package controller.shapes;

import controller.Point;
import controller.ShapeList;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public abstract class Shape implements IUndoable {
    private controller.Point start;
    private controller.Point end;
    private int width;
    private int height;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private PaintCanvasBase canvas;

    public Shape(controller.Point start, controller.Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas) {
        this.start = start;
        this.end = end;
        this.width = width;
        this.height = height;
        this.shadingType = shadingType;
        this.shapeType = shapeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.canvas = canvas;
    }

    public controller.Point getStart(){
        return start;
    }
    public Point getEnd(){
        return end;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public ShapeType getShapeType(){
        return shapeType;
    }
    public ShapeColor getPrimaryColor(){
        return primaryColor;
    }
    public ShapeColor getSecondaryColor(){
        return secondaryColor;
    }
    public ShapeShadingType getShadingType(){
        return shadingType;
    }
    public PaintCanvasBase getPaintCanvasBase(){
        return canvas;
    }

    @Override
    public void undo() {
        ShapeList.pop();
    }

    @Override
    public void redo() {
        ShapeList.push((IShape)this);
    }
}
