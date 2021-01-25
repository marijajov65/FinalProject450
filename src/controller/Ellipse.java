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

public class Ellipse implements IShape{
    private Point start;
    private Point end;
    private int width;
    private int height;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private PaintCanvasBase canvas;

    public Ellipse(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType){
        this.start = start;
        this.end = end;
        this.width = width;
        this.height = height;
        this.shadingType = shadingType;
        this.shapeType = shapeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }
    @Override
    public void draw(PaintCanvasBase b) {
        canvas = b;
        Graphics2D graphics2d = b.getGraphics2D();

        ColorMaker cm = new ColorMaker();
        Color color = cm.getColor(primaryColor);
        graphics2d.setColor(color);

        if(start.getX()<end.getX() && start.getY()>end.getY()){
            graphics2d.fillOval(start.getX(), end.getY(), width, height);
        }else if(start.getX()<end.getX() && start.getY()<end.getY()){
            graphics2d.fillOval(start.getX(), start.getY(), width, height);
        }else if(start.getX()>end.getX() && start.getY()<end.getY()){
            graphics2d.fillOval(end.getX(), start.getY(), width, height);
        }else{
            graphics2d.fillOval(end.getX(), end.getY(), width, height);

        }
    }
    public String toString(){
        return "Ellipse";
    }

    @Override
    public void undo() {
        canvas.getGraphics2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        ShapeList.pop();
    }

    @Override
    public void redo() {
        canvas.getGraphics2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        ShapeList.push(this);
    }
}
