package controller;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class ShapeDrawer {
    public void render(ArrayList<IShape> list, PaintCanvasBase canvas){
        for(IShape shape: list){
            shape.draw(canvas);
        }
    }
}
