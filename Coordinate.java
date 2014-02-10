public class Coordinate{ 
    private double x; 
    private double y; 
    public void setX(int value){ 
	x = value; 
    }
    public Coordinate(double xin, double yin){
	x = xin;
	y = yin;
    }
    public void setY(double value){ 
	y = value; 
    } 
    public double getX(){ 
	return x; 
    } 
    public double getY(){ 
	return y; 
    } 
    public void setXY(double xvalue, double yvalue){ 
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
