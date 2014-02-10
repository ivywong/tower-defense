import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.util.*;

public class Entity{
    protected Image pic;
    protected String name;
    protected Coordinate coor;
    protected double heading; //0-360 N clockwise or std cartesian?
    protected double speed;
    protected double turnRate;
    protected boolean isDead;
    protected static ArrayList<Tower> towers = new ArrayList<Tower>(); //my soul will burn for doing this
    protected static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    protected static ArrayList<SpawnPoint> spawns = new ArrayList<SpawnPoint>(); //D:
    protected static ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

    public Entity(){
	name = "name";
	coor = new Coordinate(0,0);
	isDead = false;
    }

    public void move(){ //basic moving function
	double dx = Math.cos(Math.toRadians(heading)) * speed;
	double dy = Math.sin(Math.toRadians(heading)) * speed;
	coor.setXY(coor.getX() + dx, coor.getY() - dy);
    }

    public void turn(double x, double y){ //rename this into align
	double dangle = Math.toDegrees(Math.atan((getY() - y) /  (x - getX())));
	if (((getX() - x) > 0) && ((getY() - y) < 0)){ //should be quad 2
	    dangle-= 180;
	}
	else if (((getX() - x) > 0) && ((getY() - y) > 0)){ //quad 3?
	    dangle += 180;
	}
	// System.out.println("dx:" + (x-getX()));
	// System.out.println("dy:" + (y-getY()));	
	// System.out.println("ratio:" + (y-getY())/(x-getX()));
	//System.out.println(-5%3); //in java % of a negative number returns a negative. :(
	//double dy = Math.toDegrees(Math.asin(x-getX(), y-getY()));
	heading = (((heading % 360) + 360) % 360);
	//      	dangle = (((dangle % 360) + 360) % 360);
		// dangle = (dangle + 180) % 360;
		// System.out.println(getX() - x);
		// System.out.println(getY() - y);
	
	double tarHeading; //this mess is explained in the readme.
	if (heading > 180){
	    tarHeading = heading - 360;
	}
	else{
	    tarHeading = heading;
	}
	tarHeading = (((tarHeading % 360) + 360) % 360);


	System.out.println("angle with waypoint:" + dangle);
	System.out.println("current angle:" + tarHeading);
	//System.out.println(heading);
	//	System.out.println("difference:" + (tarHeading - dangle));
	
	double cw = Math.abs(tarHeading + dangle); //angle of cw turn
	double ccw = Math.abs(tarHeading - dangle); //angle of ccw turn
	System.out.println("degree for cw:" + cw);
	System.out.println("degree for ccw:" +ccw);
        if (ccw > cw){ 
	    heading+=turnRate; 
	}
	else{
	    heading-=turnRate;
	}
				   
    }


    public static void killDead(){ //kills entities that aren't supposed to be alive
	for (int i = 0; i < enemies.size(); i++){
	    if (enemies.get(i).isDead == true){ //use accessors later
		enemies.remove(i);
	    }
	}
	for (int i = 0; i < towers.size(); i++){
	    if (towers.get(i).isDead == true){ //use accessors later
		towers.remove(i);
	    }
	}
    }

    public static void oob(){ //sets out of bound entties to dead
	for (int i = 0; i < enemies.size(); i++){ //shouldnt just apply to enemies
	    Enemy current = enemies.get(i);
	    if ((current.getX() < 0) || (current.getY() < 0) || (current.getY() > 600)){
		current.isDead = true;
		GUI.lives--;
		//	GUI.gold += 50;
	    }
	}
    }

    public static void enemyStuff(){
	for (int i = 0; i < enemies.size(); i++){
	    Enemy current = enemies.get(i);
	    if (current.health <= 0){
		GUI.gold+=50;
		current.isDead = true;
	    }
	    current.move();
	     current.alignWaypoint(current.findWaypoint());
	    // current.heading+=0; //WHEEEEEEEEEEEEEEEEEEE?
	    

	}
    }
    


    public static void towerStuff(){
	for (int i = 0; i < towers.size(); i++){
	    towers.get(i).target(enemies);
	    //System.out.println("a");
	    // System.out.println(towers.get(i).target);
	}
	for (int i = 0; i < towers.size(); i++){
	    Tower current = towers.get(i); //neatens things
	    // System.out.println(current.reloadTimer);
	    if (current.reloadTimer <= 0 && current.target != null){ //only try attacking if you can actually attack
		current.attack(current.target);
	    }
	    else{
		if (current.reloadTimer > 0){
		    current.reloadTimer--;
		}
	    }
	}
    }

    public void die(){
	isDead = true;
	GUI.gold+=10;
    }

    public Coordinate getCoor(){
	return coor;
    }

    public double distance(Entity other){
	return coor.distance(other.getCoor());
    }

    public Image getImage(){
	return pic;
    }

    public String getName(){
	return name;
    }

    

    public double getX(){
	return coor.getX();
    }

    public double getY(){
	return coor.getY();
    }

    public void setXY(double x, double y){
        coor.setXY(x,y);
    }

}
