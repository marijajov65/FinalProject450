package model.interfaces;

import model.ShapeType;

public interface IShapeFactory {
    IShape createShape(ShapeType type);
}
