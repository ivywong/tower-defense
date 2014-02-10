import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;


public class Enemy extends Entity{
    protected int health, waypoint;
    
    public Enemy(int x, int y){
        try {
	    pic = ImageIO.read(new File("images/slime.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Enemy";
	coor = new Coordinate(x,y);
	speed = 5.5;
	health = 20;
	heading = 180; //currently all enemies point west (FIX CURRENTLY IS DELTA HEADING NOT ABSOLUTE)
	isDead = false;
	turnRate = 3;
	waypoint = 0; //goto first waypoint
    }

    public void alignWaypoint(Waypoint w){
	turn(w.getX(), w.getY());
    }

    public Waypoint findWaypoint(){
	for (int i = 0; i < waypoints.size(); i++){
	    if (Entity.waypoints.get(i).sequence == waypoint){
		//System.out.println("current:" + waypoint);
		//System.out.println("found" + Entity.waypoints.get(i).sequence);
		return Entity.waypoints.get(i);
	    }
	}
	return null;
    }


	public Coordinate getCoor(){
	return coor;
    }

    public void die(){
	isDead = true;
	GUI.gold+=10;
	//add bounties and things here
    }

}
