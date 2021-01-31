package controller;
import controller.AbstractFactory;
import controller.Point;
import controller.shapes.FilledInAndOutlinedEllipse;
import controller.shapes.FilledInAndOutlinedRectangle;
import controller.shapes.FilledInAndOutlinedTriangle;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class FilledInAndOutlinedShapeFactory extends AbstractFactory {

    @Override
    IShape getShape(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas) {
        switch(shapeType){
            case RECTANGLE:
                return new FilledInAndOutlinedRectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
            case ELLIPSE:
                return new FilledInAndOutlinedEllipse(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType,canvas);
            case TRIANGLE:
                return new FilledInAndOutlinedTriangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType,canvas);
        }
        //default
        return new FilledInAndOutlinedRectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }
}

