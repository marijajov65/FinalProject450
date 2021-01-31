package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class FilledInShapeFactory extends AbstractFactory{

    @Override
    IShape getShape(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas) {
        switch(shapeType){
            case RECTANGLE:
                return new FilledInRectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
            case ELLIPSE:
                return new FilledInEllipse(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType,canvas);
            case TRIANGLE:
                return new FilledInTriangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType,canvas);
        }
        //default
        return new FilledInRectangle(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }
    }

