import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.util.*;

public class RectTower extends Tower {
    
    public RectTower(int x, int y){
	super(x,y);
	try {
	    pic = ImageIO.read(new File("images/recttower.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Basic Rect. Tower";
	range = 200;
	cost = 150;
	rate = 7;
	damage = 8;
    }

}
