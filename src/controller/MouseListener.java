package controller;

import controller.shapes.FilledInRectangle;
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
        MouseMode mouseMode = appState.getActiveMouseMode();

        //get current state
        ShapeColor primaryColor = appState.getActivePrimaryColor();
        ShapeType shapeType = appState.getActiveShapeType();
        ShapeColor secondaryColor = appState.getActiveSecondaryColor();
        ShapeShadingType shadingType = appState.getActiveShapeShadingType();
        switch(mouseMode){
            case DRAW:
                //Create abstract factory and add the shape
                AbstractFactory shapeFactory = FactoryProducer.getFactory(shadingType);
                IShape shape = shapeFactory.getShape(start, end, Math.abs(start.getX()-end.getX()), Math.abs(start.getY()-end.getY()), shapeType, primaryColor, secondaryColor, shadingType, canvas);
                shape.draw(canvas);
                ShapeList.push(shape);
                CommandHistory.add(shape);
                break;

            case SELECT:
                FilledInRectangle boundingBox = new FilledInRectangle(start, end, Math.abs(start.getX()-end.getX()), Math.abs(start.getY()-end.getY()), shapeType, primaryColor, secondaryColor, shadingType, canvas);
                ShapeListSelected.setBoundingBox(boundingBox);
                SelectShapeCommand selectCommand = new SelectShapeCommand();
                selectCommand.run();
                break;

            case MOVE:
                MoveCommand moveCommand = new MoveCommand(end.getX()-start.getX(),end.getY()-start.getY(), ShapeListSelected.getList());
                MoveOffset.setCanvas(canvas);
                MoveOffset.setxOffset(end.getX()-start.getX());
                MoveOffset.setyOffset(end.getY()-start.getY());
                //CommandHistory.add(moveCommand);
                moveCommand.run();

                break;
        }

    }
}
