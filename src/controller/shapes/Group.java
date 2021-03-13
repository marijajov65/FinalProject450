package controller.shapes;

import controller.ColorMaker;
import controller.Point;
import controller.ShapeDrawer;
import controller.ShapeList;
import model.ShapeColor;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class Group implements IShape,Cloneable, IUndoable {
    private final ArrayList<IShape> children = new ArrayList<>();
    private controller.Point start;
    private controller.Point end;
    private int width;
    private int height;

    public void setStart(int x,int y){
        Point p = new Point(x,y);
        start = p;
    }

    public void setEnd(int x,int y){
        Point p = new Point(x,y);
        end = p;
    }

    public void setWidth(int w){
        width = w;
    }

    public void setHeight(int h){
        height = h;
    }


    public void addChild(IShape shape){
        children.add(shape);
    }

    @Override
    public void draw(PaintCanvasBase base) {
        for(IShape shape: children){
            shape.draw(ShapeList.getCanvas());
        }
    }

    @Override
    public boolean equals(IShape shape) {
        if(!(shape instanceof Group))return false;
        Group group = (Group)shape;
        if(group.children.size()!=this.children.size())return false;
        int counter = 0;
        for(IShape s: group.children){
            for(IShape sh: this.children){
                if(sh.equals(s)){
                    counter++;
                }
            }
        }
        if(counter==group.children.size()){
            return true;
        }else{
            return false;
        }

    }


    public Object clone()throws CloneNotSupportedException{
        Group group = new Group();
        for(IShape child:children){
            Object clone = child.clone();
            group.addChild((IShape)clone);
        }
        group.setStart(this.getStart().getX(),this.getStart().getY());
        group.setEnd(this.getEnd().getX(),this.getEnd().getY());
        group.setWidth(this.getWidth());
        group.setHeight(this.getHeight());
        return group;
    }

    public ArrayList<IShape> getChildren(){
        return children;
    }

    @Override
    public void outline() {
        Graphics2D graphics2d = ShapeList.getCanvas().getGraphics2D();
        ShapeDrawer sd = new ShapeDrawer();
        //sd.render(ShapeList.getList(),ShapeList.getCanvas());
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        ColorMaker cm = ColorMaker.getColorMaker();
        Color color = cm.getColor(ShapeColor.RED);
        graphics2d.setColor(color);
        graphics2d.setStroke(dashed);
        graphics2d.drawRect(start.getX() -10, start.getY()-10, Math.abs(start.getX()-end.getX()) +20 , Math.abs(start.getY()-end.getY())+20);
    }

    public void moveChildren(){
        for(IShape shape: children){
            shape.setStart(shape.getStart().getX()+50,shape.getStart().getY()+50);
            shape.setEnd(shape.getEnd().getX()+50,shape.getEnd().getY()+50);
        }
    }

    public void ungroup(){
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
        for(IShape shape: children){
            ShapeList.getList().add(shape);
        }
        ShapeList.getList().remove(this);

    }

    @Override
    public void deselect() {

    }

    @Override
    public Point getStart() {
        return start;
    }

    @Override
    public Point getEnd() {
        return end;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    @Override
    public void undo() {
        ShapeDrawer sd = new ShapeDrawer();
        ShapeList.getList().remove(this);
        for(IShape shape: children){
            ShapeList.getList().add(shape);
        }
        sd.render(ShapeList.getList(),ShapeList.getCanvas());
    }

    @Override
    public void redo() {
        for(IShape shape: children){
            ShapeList.getList().remove(shape);
        }
        ShapeList.push(this);
    }

    public void move(int x, int y){
        this.setStart(x + this.getStart().getX(), y + this.getStart().getY());
        this.setEnd(x+ this.getEnd().getX(), y + this.getEnd().getY());
        for(IShape s: this.children){
            s.move(x,y);
        }
    }
}
