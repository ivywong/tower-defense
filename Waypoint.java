public class Waypoint{
    protected Coordinate coor;
    protected int sequence; //
    protected static int currentSeq;

    public Waypoint(){
	this(600,300, 0);
    }
    public Waypoint(int x, int y, int seq){
	this(new Coordinate(x, y), seq);
    }
   

    public Waypoint(Coordinate coor, int seq){
	this.coor = coor;
	sequence = seq;
    }
    public double getX(){
	return coor.getX();
    }

    public double getY(){
	return coor.getY();
    }

}
