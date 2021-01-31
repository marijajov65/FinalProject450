package controller;

import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseListener extends MouseAdapter {
    private Point start;
    private Point end;
    private ApplicationState appState;
    private PaintCanvasBase canvas;


    public MouseListener(ApplicationState appState, PaintCanvasBase paintCanvas) {
        this.appState = appState;
        this.canvas = paintCanvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        end = new Point(e.getX(), e.getY());

        //get current state
        ShapeColor primaryColor = appState.getActivePrimaryColor();
        ShapeType shapeType = appState.getActiveShapeType();
        ShapeColor secondaryColor = appState.getActiveSecondaryColor();
        ShapeShadingType shadingType = appState.getActiveShapeShadingType();
        MouseMode mouseMode = appState.getActiveMouseMode(); //draw,select,move

        //Create abstract factory and add the shape
        AbstractFactory shapeFactory = FactoryProducer.getFactory(shadingType);
        IShape shape = shapeFactory.getShape(start, end, Math.abs(start.getX()-end.getX()), Math.abs(start.getY()-end.getY()), shapeType, primaryColor, secondaryColor, shadingType, canvas);
        shape.draw(canvas);
        ShapeList.push(shape);
        CommandHistory.add(shape);
/*
        //create the shape
        IShape sb = new ShapeBuilder()
                .setPrimaryColor(primaryColor)
                .setSecondaryColor(secondaryColor)
                .setShadingType(shadingType)
                .setMouseMode(mouseMode)
                .setType(shapeType)
                .setStart(start)
                .setEnd(end)
                .setHeight(Math.abs(end.getY() - start.getY()))
                .setWidth(Math.abs(end.getX() - start.getX()))
                .setCanvas(canvas)
                .buildShape();

        //recognizes which shape it is and invokes that draw method
        if (e.getSource() instanceof PaintCanvasBase) {
            sb.draw((PaintCanvasBase) e.getSource());
            ShapeList.push(sb);
            CommandHistory.add(sb);
        }

 */
    }
}
