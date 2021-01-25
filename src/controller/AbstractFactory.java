package controller;

import model.interfaces.IShape;

public abstract class AbstractFactory {
    abstract IShape getShape(String shapeType);
}
