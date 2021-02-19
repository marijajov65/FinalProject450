package controller.shapes;


import controller.ShapeList;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

public class OutlinedShape extends Shape implements IShape {

    private IShape shape;
    public OutlinedShape(IShape shape){
        super(((Shape)shape).getStart(),((Shape)shape).getEnd(),((Shape)shape).getWidth(),((Shape)shape).getHeight(),((Shape)shape).getShapeType(),((Shape)shape).getPrimaryColor(),((Shape)shape).getSecondaryColor(),((Shape)shape).getShadingType(),((Shape)shape).getPaintCanvasBase());
        this.shape = shape;
    }

    @Override
    public void draw(PaintCanvasBase base) {
        shape.draw(ShapeList.getCanvas());
        outline();
    }

    @Override
    public void outline() {
        shape.outline();
    }

    @Override
    public void deselect() {
        shape.deselect();
    }

    @Override
    public void undo() {

        shape.undo();
    }

    @Override
    public void redo() {
        shape.redo();
    }
}
