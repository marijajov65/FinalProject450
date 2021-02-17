package controller.shapes;

import controller.ColorMaker;
import controller.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class FilledInEllipse extends Shape implements IShape {

    public FilledInEllipse(controller.Point start, Point end, int width, int height, ShapeType shapeType, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, PaintCanvasBase canvas){
        super(start, end,width,height, shapeType, primaryColor, secondaryColor, shadingType, canvas);
    }
    @Override
    public void draw(PaintCanvasBase b) {
        Graphics2D graphics2d = b.getGraphics2D();
        ColorMaker cm = ColorMaker.getColorMaker();
        Color color = cm.getColor(super.getPrimaryColor());
        graphics2d.setColor(color);
        graphics2d.setStroke(new BasicStroke(5));
        if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()>super.getEnd().getY()){
            graphics2d.fillOval(super.getStart().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());
        }else if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            graphics2d.fillOval(super.getStart().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
        }else if(super.getStart().getX()>super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){
            graphics2d.fillOval(super.getEnd().getX(), super.getStart().getY(), super.getWidth(), super.getHeight());
        }else{
            graphics2d.fillOval(super.getEnd().getX(), super.getEnd().getY(), super.getWidth(), super.getHeight());

        }
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

            graphics2d.drawOval(super.getStart().getX()-5, super.getEnd().getY()-5, super.getWidth()+10, super.getHeight()+10);

        }else if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){

            graphics2d.drawOval(super.getStart().getX()-5, super.getStart().getY()-5, super.getWidth()+10, super.getHeight()+10);
        }else if(super.getStart().getX()>super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){

            graphics2d.drawOval(super.getEnd().getX()-5, super.getStart().getY()-5, super.getWidth()+10, super.getHeight()+10);
        }else{

            graphics2d.drawOval(super.getEnd().getX()-5, super.getEnd().getY()-5, super.getWidth()+10, super.getHeight()+10);

        }
    }

    @Override
    public void deselect() {
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        Graphics2D graphics2d = this.getPaintCanvasBase().getGraphics2D();
        ColorMaker cm = ColorMaker.getColorMaker();
        Color color = cm.getColor(ShapeColor.WHITE);
        graphics2d.setColor(color);
        graphics2d.setStroke(dashed);

        if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()>super.getEnd().getY()){

            graphics2d.drawOval(super.getStart().getX()-5, super.getEnd().getY()-5, super.getWidth()+10, super.getHeight()+10);

        }else if(super.getStart().getX()<super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){

            graphics2d.drawOval(super.getStart().getX()-5, super.getStart().getY()-5, super.getWidth()+10, super.getHeight()+10);
        }else if(super.getStart().getX()>super.getEnd().getX() && super.getStart().getY()<super.getEnd().getY()){

            graphics2d.drawOval(super.getEnd().getX()-5, super.getStart().getY()-5, super.getWidth()+10, super.getHeight()+10);
        }else{

            graphics2d.drawOval(super.getEnd().getX()-5, super.getEnd().getY()-5, super.getWidth()+10, super.getHeight()+10);

        }
    }

}
