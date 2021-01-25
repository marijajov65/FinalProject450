package controller;

import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class ShapeBuilder {
    private Point start;
    private Point end;
    private int width;
    private int height;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private MouseMode mouseMode;
    private PaintCanvasBase canvas;

    public ShapeBuilder setStart(Point start){
        this.start = start;
        return this;
    }
    public ShapeBuilder setEnd(Point end){
        this.end = end;
        return this;
    }
    public ShapeBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public ShapeBuilder setHeight(int height){
        this.height = height;
        return this;
    }

    public ShapeBuilder setPrimaryColor(ShapeColor primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public ShapeBuilder setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }
    public ShapeBuilder setShadingType(ShapeShadingType shadingType) {
        this.shadingType = shadingType;
        return this;
    }
    public ShapeBuilder setMouseMode(MouseMode mouseMode) {
        this.mouseMode = mouseMode;
        return this;
    }
    public ShapeBuilder setType(ShapeType shapeType) {
        this.shapeType = shapeType;
        return this;
    }
    public ShapeBuilder setCanvas(PaintCanvasBase canvas) {
        this.canvas = canvas;
        return this;
    }

    public IShape buildShape(){
        switch(shapeType){
            case RECTANGLE:
                return new Rectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
            case ELLIPSE:
                return new Ellipse(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType);
        }
        throw new IllegalArgumentException();
    }

}
