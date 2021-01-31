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

public class Rectangle extends Shape implements IShape {

    public Rectangle(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas){
        super(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }
    @Override
    public void draw(PaintCanvasBase b) {
        Graphics2D graphics2d = b.getGraphics2D();
        ColorMaker cm = new ColorMaker();
        Color color = cm.getColor(super.getPrimaryColor());
        graphics2d.setColor(color);
        graphics2d.setStroke(new BasicStroke(5));
        if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()>super.getEnd().getY()){
            graphics2d.fillRect(super.getStart().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());
        }else if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            graphics2d.fillRect(super.getStart().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
        }else if(super.getStart().getX()>super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            graphics2d.fillRect(super.getEnd().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
        }else{
            graphics2d.fillRect(super.getEnd().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());
        }
    }

    public String toString(){
        return "Rectangle";
    }
}
