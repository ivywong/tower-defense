import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.util.*;

public class Tower extends Entity{
    //Instance Variables

    private int range, speed, cost;

    public Tower(){
	try {
	    pic = ImageIO.read(new File("images/tower.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Generic Tower";
        coor = new Coordinate(0,0);
	range = 5;
	cost = 100;
	speed = 5;
    }

    public Tower(int x, int y){
	try {
	    pic = ImageIO.read(new File("images/tower.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Generic Tower";
        coor = new Coordinate(x,y);
	range = 250;
	cost = 100;
	speed = 5;
    }

    public Enemy target(ArrayList<Enemy> enemies){
	double[] distances = new double[enemies.size()];
	//fix null pointer exception when no enemies present
	for (int i = 0; i < distances.length; i++){
	    distances[i] = distance(enemies.get(i));
	}
	//interim: first valid target
	//later closest etc

	for (int i = 0; i < distances.length; i++){
	    if (distances[i] < range){
		return enemies.get(i);
	    }
	}
	return null; //no targets in range
    }



}
