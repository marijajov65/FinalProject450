package controller;

import model.interfaces.IShape;

public class ShapeFactory extends AbstractFactory{
    @Override
    IShape getShape(String shapeType) {
        if(shapeType.equalsIgnoreCase("Rectangle")){
            return null;
                    //new Rectangle();
        }else if(shapeType.equalsIgnoreCase("Ellipse")){
            return null;
                    //new Ellipse();
        }
        return null;
    }
}
