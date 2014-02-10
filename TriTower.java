import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.util.*;

public class TriTower extends Tower {
    
    public TriTower(int x, int y){
	super(x,y);
	try {
	    pic = ImageIO.read(new File("images/tritower.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Basic Triangle Tower";
	range = 175;
	cost = 200;
	rate = 15;
	damage = 6;
    }

}
