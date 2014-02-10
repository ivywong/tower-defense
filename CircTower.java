import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.util.*;

public class CircTower extends Tower {
    
    public CircTower(int x, int y){
	super(x,y);
	try {
	    pic = ImageIO.read(new File("images/circtower.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Basic Circle Tower";
	range = 150;
	cost = 150;
	rate = 7;
	damage = 8;
    }

}
