package controller;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeDrawer {
    public void render(ArrayList<IShape> list, PaintCanvasBase canvas){
        Graphics2D graphics2d = canvas.getGraphics2D();
        graphics2d.setColor(new Color(255,255,255));
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(IShape shape: list){
            shape.draw(canvas);
        }
    }

    public void drawSelected(ArrayList<IShape> list, PaintCanvasBase canvas){
        Graphics2D graphics2d = canvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        for(IShape shape: list){
            shape.draw(canvas);
        }
    }
}
