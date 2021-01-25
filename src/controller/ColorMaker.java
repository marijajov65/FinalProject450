package controller;

import model.ShapeColor;
import java.awt.*;

public class ColorMaker {
    public Color getColor(ShapeColor color){
        switch(color){
            case BLUE:
                return new Color(1, 101, 196);
            case GREEN:
                return new Color(15, 213, 15);
            case YELLOW:
                return new Color(217, 208, 40);
            case RED:
                return new Color(220, 11, 11);
            case CYAN:
                return new Color(96, 241, 201);
            case DARK_GRAY:
                return new Color(45, 43, 43);
            case GRAY:
                return new Color(111, 116, 115);
            case LIGHT_GRAY:
                return new Color(161, 167, 167);
            case MAGENTA:
                return new Color(184, 19, 138);
            case ORANGE:
                return new Color(217, 115, 23);
            case PINK:
                return new Color(223, 154, 238);
            case WHITE:
                return new Color(255, 255, 255);

                default:
                    return new Color(1,1,1);
        }

    }
}
