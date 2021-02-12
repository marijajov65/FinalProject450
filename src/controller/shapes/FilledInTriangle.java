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

    @Override
    public void outline() {

        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        Graphics2D graphics2d = this.getPaintCanvasBase().getGraphics2D();
        ColorMaker cm = ColorMaker.getColorMaker();
        Color color = cm.getColor(ShapeColor.RED);
        graphics2d.setColor(color);
        graphics2d.setStroke(dashed);

        if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()>super.getEnd().getY()){
            int[] xs = new int[]{super.getEnd().getX()+15,super.getStart().getX()-5,super.getStart().getX()-5};
            int[] ys = new int[]{super.getEnd().getY()-5, super.getEnd().getY()-5,super.getStart().getY()+10};
            graphics2d.drawPolygon(xs, ys,3);
            //normal
        }else if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            int[] xs = new int[]{super.getStart().getX()-5,super.getStart().getX()-5,super.getEnd().getX()+15};
            int[] ys = new int[]{super.getStart().getY()-10, super.getEnd().getY()+5,super.getEnd().getY()+5};
            graphics2d.drawPolygon(xs, ys,3);
        }else if(super.getStart().getX()>super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            int[] xs = new int[]{super.getEnd().getX()-15,super.getStart().getX()+5,super.getStart().getX()+5};
            int[] ys = new int[]{super.getEnd().getY()+5, super.getEnd().getY()+5,super.getStart().getY()-10};
            graphics2d.drawPolygon(xs, ys,3);
        }else{//reversed

            int[] xs = new int[]{super.getEnd().getX()-15,super.getStart().getX()+5,super.getStart().getX()+5};
            int[] ys = new int[]{super.getEnd().getY()-5, super.getEnd().getY()-5,super.getStart().getY()+10};
            graphics2d.drawPolygon(xs, ys,3);

        }


    }
}
