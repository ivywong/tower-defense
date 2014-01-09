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

    public Entity(){
	name = "name";
	coor = new Coordinate(0,0);
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

    

    public int getX(){
	return coor.getX();
    }

    public int getY(){
	return coor.getY();
    }

    public void setXY(int x, int y){
        coor.setXY(x,y);
    }

}
