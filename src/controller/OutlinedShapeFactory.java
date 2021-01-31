package controller;

import controller.shapes.OutlinedEllipse;
import controller.shapes.OutlinedRectangle;
import controller.shapes.OutlinedTriangle;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class OutlinedShapeFactory extends AbstractFactory{

    @Override
    IShape getShape(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas) {

        switch(shapeType){
            case RECTANGLE:
                return new OutlinedRectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
            case ELLIPSE:
                return new OutlinedEllipse(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType,canvas);
            case TRIANGLE:
                return new OutlinedTriangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType,canvas);
        }
        //default
        return new OutlinedRectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }
}
