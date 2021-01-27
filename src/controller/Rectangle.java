package controller;


import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Rectangle implements IShape,IUndoable {
    private Point start;
    private Point end;
    private int width;
    private int height;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private PaintCanvasBase canvas;

    public Rectangle(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas){
        this.start = start;
        this.end = end;
        this.width = width;
        this.height = height;
        this.shadingType = shadingType;
        this.shapeType = shapeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.canvas = canvas;

    }
    @Override
    public void draw(PaintCanvasBase b) {
        Graphics2D graphics2d = b.getGraphics2D();
        ColorMaker cm = new ColorMaker();
        Color color = cm.getColor(primaryColor);
        graphics2d.setColor(color);
        graphics2d.setStroke(new BasicStroke(5));
        if(start.getX()<end.getX() && start.getY()>end.getY()){
            graphics2d.fillRect(start.getX(), end.getY(), width, height);
        }else if(start.getX()<end.getX() && start.getY()<end.getY()){
            graphics2d.fillRect(start.getX(), start.getY(), width, height);
        }else if(start.getX()>end.getX() && start.getY()<end.getY()){
            graphics2d.fillRect(end.getX(), start.getY(), width, height);
        }else{
            graphics2d.fillRect(end.getX(), end.getY(), width, height);
        }
    }

    public String toString(){
        return "Rectangle";
    }

    @Override
    public void undo() {
        ShapeList.pop();
    }

    @Override
    public void redo() {
        ShapeList.push(this);
    }
}
