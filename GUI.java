import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener, MouseListener {
    private Container background, buttons;
    private MyMap map;
    private JButton exitButton, upgradeButton;
    private JLabel info;


    public void towerStuff(){
	for (int i = 0; i < map.towers.size(); i++){
	    System.out.println(map.towers.get(i).target(map.enemies));
	}
    }

    public GUI(){
	setup();
    }

    public void setup(){
	//Set up main window
	setTitle("Tower Defense");
	setSize(750,600); //set initial size
	setLocationRelativeTo(null); //center window
	setDefaultCloseOperation(EXIT_ON_CLOSE);
 
	//set up background
	background = this.getContentPane();
	background.setBackground(Color.WHITE);
	background.setLayout(new FlowLayout());

	//create map
	map = new MyMap();
	map.addMouseListener(this);

	//Set up buttons
	buttons = new Container();
	buttons.setLayout(new GridLayout(2,1));
	exitButton = new JButton("Exit");
	upgradeButton = new JButton("Upgrade");

	//Set up label
	info = new JLabel("<html>INFO BOX OF DOOOOM<br>Testing stuff here</html>");
	
       	exitButton.addActionListener(this);
	upgradeButton.addActionListener(this);

	buttons.add(upgradeButton);
	buttons.add(exitButton);
        
	background.add(map);
	background.add(info);
	background.add(buttons);
	
	pack(); //fit window to content size
	setLocationRelativeTo(null); //center window
    }

    public void actionPerformed(ActionEvent e){
	String action = e.getActionCommand();
	if(action.equals("Exit")){
	    setVisible(false);
	    dispose();
	} else if(action.equals("Upgrade")){
	    System.out.println("Sorry! The upgrade feature is not implemented yet.");
	}
    }

    public void mouseClicked(MouseEvent e){
	System.out.println("You clicked on the map.");
	System.out.println("x-cor: " + e.getX() + "\ny-cor: " + e.getY());
	map.addTower(e.getX(),e.getY());
	
    }

    public void mouseEntered(MouseEvent e){
	//System.out.println("Mouse entered map.");
    }
    public void mouseExited(MouseEvent e){
	//System.out.println("Mouse exited map.");
    }
    
    public void mousePressed(MouseEvent e){
	//System.out.println("Mouse pressed.");
    }
    public void mouseReleased(MouseEvent e){
	//System.out.println("Mouse released.");
    }
}
