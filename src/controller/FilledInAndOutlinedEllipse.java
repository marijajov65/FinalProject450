package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class FilledInAndOutlinedEllipse extends Shape implements IShape {

    public FilledInAndOutlinedEllipse(Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas){
        super(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }
    @Override
    public void draw(PaintCanvasBase b) {
        Graphics2D graphics2d = b.getGraphics2D();
        ColorMaker cm = new ColorMaker();
        Color primary = cm.getColor(super.getPrimaryColor());
        Color secondary = cm.getColor(super.getSecondaryColor());
        graphics2d.setColor(primary);
        graphics2d.setStroke(new BasicStroke(5));
        if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()>super.getEnd().getY()){
            graphics2d.fillOval(super.getStart().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());
            graphics2d.setColor(secondary);
            graphics2d.drawOval(super.getStart().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());

        }else if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            graphics2d.fillOval(super.getStart().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
            graphics2d.setColor(secondary);
            graphics2d.drawOval(super.getStart().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
        }else if(super.getStart().getX()>super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            graphics2d.fillOval(super.getEnd().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
            graphics2d.setColor(secondary);
            graphics2d.drawOval(super.getEnd().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
        }else{
            graphics2d.fillOval(super.getEnd().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());
            graphics2d.setColor(secondary);
            graphics2d.drawOval(super.getEnd().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());
        }
    }

}
