package eapli.base.app.common.console.presentation.menuselect;

import java.awt.*;
import eapli.base.common.Cor;

import javax.swing.*;

public class ColorPickerWidget {
    private static final String DEFAULT_MESSAGE  = "Chose a color";
    public static Cor chooseCor(){
        Color color = null;
        while(color==null){
            color = JColorChooser.showDialog(null,DEFAULT_MESSAGE,Color.BLACK);
        }
        return new Cor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Cor chooseCor(final String message){
        Color color = null;
        while(color==null){
            color = JColorChooser.showDialog(null,message,Color.BLACK);
        }
        return new Cor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Color chooseColor(){
        Color color = null;
        while(color==null){
            color = JColorChooser.showDialog(null,DEFAULT_MESSAGE,Color.BLACK);
        }
        return color;
    }

    public static Color chooseColor(final String message){
        Color color = null;
        while(color==null){
            color = JColorChooser.showDialog(null,message,Color.BLACK);
        }
        return color;
    }

}
