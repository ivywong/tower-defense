import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyMap extends JPanel {
    protected static ArrayList<Tower> towers;
    protected static ArrayList<Enemy> enemies;
    private final int[][] tilemap;

    public ArrayList<Enemy> getEnemies(){
	return enemies;
    }

    public MyMap(){
	setPreferredSize(new Dimension(600, 600));
	towers = new ArrayList<Tower>();
	tilemap = new int[][] { {0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0} };
    }

    @Override
    protected void paintComponent(Graphics g2){
	super.paintComponent(g2);
	Graphics2D g = (Graphics2D) g2;
	g.clearRect(0, 0, getWidth(), getHeight());
	Rectangle rect = new Rectangle(55,55);
	for(int i = 0; i < tilemap.length; i++){
	    for(int j = 0; j < tilemap[i].length; j++){
		if(tilemap[i][j] == 1){
		    g.setColor(Color.YELLOW);
		} else {
		    g.setColor(Color.GREEN);
		}
		rect.setLocation(i*60,j*60);
		g.fill(rect);
	    }
	}
	for(int i = 0; i < towers.size(); i++){
	    Tower current = towers.get(i);
	    Image pic = current.getImage();
	    //offset image so it is centered
	    g.drawImage(pic,current.getX()-pic.getWidth(null)/2,current.getY()-pic.getHeight(null)/2,null);
	}
    }

    public void addTower(int x, int y){
	//first check if there is room for a tower, then add tower
	//TODO: if(tileEmpty() or something)
	towers.add(new Tower(x,y));
	System.out.println("Tower added.");
    }
}
