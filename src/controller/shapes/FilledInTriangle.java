package controller.shapes;

import controller.ColorMaker;
import controller.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class FilledInTriangle extends Shape implements IShape {
    public FilledInTriangle(controller.Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas) {
        super(start, end, width, height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }

    @Override
    public void draw(PaintCanvasBase b) {
        Graphics2D graphics2d = b.getGraphics2D();
        ColorMaker cm = ColorMaker.getColorMaker();
        Color color = cm.getColor(super.getPrimaryColor());
        graphics2d.setColor(color);
        graphics2d.setStroke(new BasicStroke(5));
        int[] xs = new int[]{super.getStart().getX(),super.getStart().getX(),super.getEnd().getX()};
        int[] ys = new int[]{super.getStart().getY(), super.getEnd().getY(),super.getEnd().getY()};
        graphics2d.fillPolygon(xs,ys,3);
    }
}
