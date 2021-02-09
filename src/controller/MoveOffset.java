package controller;

import view.interfaces.PaintCanvasBase;

public class MoveOffset {
    private static int xOffset;
    private static int yOffset;
    private static PaintCanvasBase canvas;

    public static PaintCanvasBase getCanvas(){
        return canvas;
    }
    public static void setxOffset(int x){
        xOffset = x;
    }
    public static void setyOffset(int y){
        yOffset = y;
    }
    public static int getxOffset(){
        return xOffset;
    }
    public static int getyOffset(){
        return yOffset;
    }
    public static void setCanvas(PaintCanvasBase base) {
        canvas = base;
    }
}
