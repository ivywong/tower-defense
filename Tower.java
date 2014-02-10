import javax.imageio.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.util.*;

public class Tower extends Entity{
    //Instance Variables

    protected int range, rate, cost, damage, reloadTimer; //gets later?
    protected boolean selected;
    protected Enemy target;

    public Tower(int x, int y){
	try {
	    pic = ImageIO.read(new File("images/tower.png")); //read in image
	} catch (IOException e){
	    System.out.println("ERROR LOADING IMAGE");
	}
	name = "Generic Tower";
        coor = new Coordinate(x,y);
	range = 150;
	cost = 100;
	rate = 5;
	damage = 10;
	reloadTimer = 0;
	isDead = false;
	selected = false;
    }

    public void setReloadTimer(int reloadTimer){
	this.reloadTimer = reloadTimer;
    }

    public int getReloadTimer(){
	return reloadTimer;
    }

    public int getCost(){
	return cost;
    }

    public int getRange(){
	return range;
    }
    
    public int getDamage(){
	return damage;
    }
    
    public int getRate(){
	return rate;
    }
    public void setRange(int range){
	this.range = range;
    }

    public boolean isSelected(){
	return selected;
    }

    public void setSelected(boolean selected){
	this.selected = selected;
    }

    public void target(ArrayList<Enemy> enemies){
	double[] distances = new double[enemies.size()];
	//fix null pointer exception when no enemies present
	for (int i = 0; i < distances.length; i++){
	    distances[i] = distance(enemies.get(i));
	}
	//interim: first valid target
	//later closest etc

	for (int i = 0; i < distances.length; i++){
	    Enemy current = enemies.get(i);
	    if (distances[i] < range && current.isDead == false){
		target = current;
		return;
	    }
	}
	target = null; //no targets in range
    }

    public void attack(Enemy enemy){
	if (enemy.isDead == false){
	    enemy.health-=damage;
	    reloadTimer = 50;
	    System.out.println("Hit for " + damage + "damage!");
	}
    }

    public void die(){
	isDead = true;
	//add selling stuff here
    }


}
