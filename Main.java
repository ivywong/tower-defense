
public class Main {


    public static void main(String[] args){

	GUI gui = new GUI();
	gui.setVisible(true);
	//game loop goes here
	while(true){
	    //gui.towerStuff();
	    gui.repaint(); //update all components in gui
	    
	    try {
		Thread.sleep(25); //wait 25 milliseconds before updating again (
	    } catch (InterruptedException e){
		e.printStackTrace();
	    }
	}

    }


}
