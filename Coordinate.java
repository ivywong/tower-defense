public class Coordinate{ 
    private int x; 
    private int y; 
    public void setX(int value){ 
    x = value; 
    }
    public Coordinate(int xin, int yin){
	x = xin;
	y = yin;
    }
    public void setY(int value){ 
    y = value; 
    } 
    public int getX(){ 
    return x; 
    } 
    public int getY(){ 
    return y; 
    } 
    public void setXY(int xvalue, int yvalue){ 
    x = xvalue; 
    y = yvalue; 
    } 
    public String toString(){ 
	return "(" + x + "," + y + ")"; 
    }

    public double distance(){
	return Math.sqrt(x*x + y*y);
    } 
    
    public double distance(Coordinate other){
	return Math.sqrt((Math.pow(x-other.getX(),2)) + Math.pow((y-other.getY()),2.0)); //squared

    }
} 
