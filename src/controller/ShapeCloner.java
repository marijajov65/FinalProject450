package controller;

import controller.shapes.Shape;
import model.interfaces.IShape;

public class ShapeCloner{

    private IShape shape;

    public ShapeCloner(IShape shape){









        this.shape = shape;
    }
    public IShape getShape(){
        return this.shape;
    }
}
