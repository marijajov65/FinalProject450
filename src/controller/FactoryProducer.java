package controller;

import model.ShapeShadingType;

public class FactoryProducer {
    public static AbstractFactory getFactory(ShapeShadingType sst){
        switch(sst){
            case FILLED_IN:
                return new FilledInShapeFactory();
            case OUTLINE_AND_FILLED_IN:
                return new FilledInAndOutlinedShapeFactory();
            case OUTLINE:
                return new OutlinedShapeFactory();
        }
        throw new IllegalArgumentException();
    }
}
