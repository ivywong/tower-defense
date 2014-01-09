import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;

public class Enemy extends Entity{
    public Enemy(int x, int y){
	name = "Enemy";
	coor = new Coordinate(x,y);
	//speed = 5;
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

    public Coordinate getCoor(){
	return coor;
    }
}
