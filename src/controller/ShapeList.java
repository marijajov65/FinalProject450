package controller;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ShapeList {

    //ShapeList collection class
    private static final ArrayList<IShape> list = new ArrayList<>();
    private static PaintCanvasBase canvas;

    public static PaintCanvasBase getCanvas(){
        return canvas;
    }

    public ShapeList(PaintCanvasBase canvas){
        this.canvas = canvas;
    }

    public static void push(IShape shape) {
        if (shape == null) {
            throw new IllegalArgumentException();
        }
        list.add(shape);
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(list,canvas);
    }

    public static void pop() {
        if (list.isEmpty()) {
            return;
        }

        for(IShape shape: ShapeList.getList()){
            System.out.println(shape);
        }
        System.out.println(list.size());
        list.remove(list.size()-1);
        ShapeDrawer sd = new ShapeDrawer();
        sd.render(list,canvas);
        System.out.println(list.size());
        }

        public static ArrayList<IShape> getList(){
        return list;
        }

}
