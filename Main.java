
public class Main {


    public static void main(String[] args){

	GUI gui = new GUI();
	gui.setVisible(true);
	//game loop goes here
	while(true){
	    try{
		Entity.enemyStuff();
		Entity.towerStuff();
		Entity.oob();
		Entity.killDead();
		gui.displayInfo();
		gui.repaint(); //update all components in gui
		gui.map.spawn();
		if (gui.lives < 0){
		    gui.lives = 0;
		}
		int deadness = 5 / gui.lives; //totally the best way to throw an exception

	    }
	    catch (ArithmeticException e){
		break;
	    }

	    
	    try {
		Thread.sleep(25); //wait 25 milliseconds before updating again (
	    } catch (InterruptedException e){
		e.printStackTrace();
	    }
	}
	System.out.println("Game over!");

    }


}
