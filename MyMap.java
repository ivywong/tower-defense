import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class MyMap extends JPanel {

    // 1-19-14: add range drawing thing

    private final int[][] tilemap;
    //    protected int currentSeq;

    // public ArrayList<Enemy> getEnemies(){ //move this somehwere elseeeeeeeee
    // protected ArrayList<SpawnPoint> spawns = new ArrayList<SpawnPoint>(); //D:
    // protected ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
    // 	return enemies;
    // }

    public MyMap(){
	setPreferredSize(new Dimension(600, 600));
	tilemap = new int[][] { {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    }

    @Override
    protected void paintComponent(Graphics g2){
	super.paintComponent(g2);
	Graphics2D g = (Graphics2D) g2;
	g.clearRect(0, 0, getWidth(), getHeight());
	Rectangle rect = new Rectangle(30,30); //Edit for grid/nogrid, 60 max
	for(int i = 0; i < tilemap.length; i++){
	    for(int j = 0; j < tilemap[i].length; j++){
		if(tilemap[i][j] == 1){
		    g.setColor(Color.YELLOW);
		} else {
		    g.setColor(Color.GREEN);
		}
		rect.setLocation(i*30,j*30);
		g.fill(rect);
	    }
	}
	for(int i = 0; i < Entity.towers.size(); i++){
	    Tower current = Entity.towers.get(i);
	    Image pic = current.getImage();
	    //offset image so it is centered
	    int currentX = (int)current.getX();
	    int currentY = (int)current.getY();
	    if(current.isSelected()){
		int range = current.getRange();
		Ellipse2D rangeCirc = new Ellipse2D.Double(currentX-range/2, currentY-range/2, (double)range,(double)range);
		g.setColor(Color.RED);
		//g.fill(rangeCirc);
		g.draw(rangeCirc);
	    }
	    g.drawImage(pic,currentX-pic.getWidth(null)/2,currentY-pic.getHeight(null)/2,null);
	}
	for(int i = 0; i < Entity.enemies.size(); i++){
	    Enemy current = Entity.enemies.get(i); 
	    Image pic = current.getImage();
	    //offset image so it is centered
	    g.drawImage(pic,(int)current.getX()-pic.getWidth(null)/2,(int)current.getY()-pic.getHeight(null)/2,null);
	}
    }

    public Tower selectTower(int x, int y){
	int halfRange = 25; //half of hitbox
	Tower selected = null;
	for(Tower tower : Entity.towers){
	    if((x > tower.getX() - halfRange && x < tower.getX() + halfRange) &&
	       (y > tower.getY() - halfRange && y < tower.getY() + halfRange)){
		tower.setSelected(true);
		selected = tower;
	    }
	}
	for(Tower tower : Entity.towers){
	    if(tower != selected){
		tower.setSelected(false);
	    }
	}
	return selected;
    }

    public boolean tileValid(int x, int y){
	int tilePix = 30;
	if(tilemap[x/tilePix][y/tilePix]==1){
	    return false;
	}
	return true;
    }


    public boolean addTower(int x, int y, String str,int gold){
	//first check if there is room for a tower, then add tower
	//TODO: if(tileEmpty() or something)
	if(tileValid(x,y) && gold-150>=0){
	    if(str.equals("default")){
		Entity.towers.add(new Tower(x,y));
		System.out.println("Tower added.");
	    } else if(str.equals("Basic Circle")){
		Entity.towers.add(new CircTower(x,y));
		System.out.println("Circle tower added.");
	    } else if(str.equals("Basic Rect.")){
		Entity.towers.add(new RectTower(x,y));
		System.out.println("Rect. tower added.");
	    } else if(str.equals("Basic Triangle")){
		Entity.towers.add(new TriTower(x,y));
		System.out.println("Triangle tower added.");
	    }
	    return true;
	}
	return false;
    }

    public void addEnemy(int x, int y){
	Entity.enemies.add(new Enemy(x,y));
	System.out.println("Monster spawned.");
    }

    public void addSpawn(int x, int y){
	Entity.spawns.add(new SpawnPoint(x, y));
	System.out.println("Spawnpoint spawned.");
    }

    public void spawn(SpawnPoint s){
	if (s.spawnBudget > 0){
	    if (s.spawnDelay <= 0){ //accessorsssss
		addEnemy((int) s.coor.getX(), (int) s.coor.getY()); //accessor mehtods required
		s.spawnBudget--; //decrementor function later for dif fmonsters
		s.spawnDelay = 10; //use maxSpawnDelay variable later to store actual cd for each
	    }
	    else{
		s.spawnDelay--; //mutatorsss
	    }
	}
    }


    public void addWaypoint(int x, int y){
	Entity.waypoints.add(new Waypoint(x, y, Waypoint.currentSeq)); //auto deals with # of waypoint
	Waypoint.currentSeq++;
	System.out.println("Waypoint spawned.");
    }
    public void spawn(){
	for (int i = 0; i < Entity.spawns.size(); i++){
	    spawn(Entity.spawns.get(i));
	}
    }
}
