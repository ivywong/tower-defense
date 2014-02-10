import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class GUI extends JFrame implements ActionListener, MouseListener, KeyListener {
    private Container background, ui;
    private JPanel buttons, tshop;
    protected MyMap map;
    private JButton exitButton, upgradeButton, spawnButton;
    private String[] uiButtons, uiButtonsAC, towerShopButtons, towerShopImages;
    private String currentShop;
    private JLabel player, info;
    private int reloadTimer;
    private boolean spawning, waypointing; //spawn monster mode
    protected static int gold, lives;

    public GUI(){
	gold = 2000;
	lives = 150;

	background = this.getContentPane();
	spawning = false;
	waypointing = false;
	ui = new Container();
	map = new MyMap();
	buttons = new JPanel();
	tshop = new JPanel();
	currentShop = "";
	uiButtons = new String[] { "Enable Spawn Mode", "Enable Waypoint Mode", "Exit" };
	uiButtonsAC = new String[] { "Spawn Mode", "Waypoint Mode", "Exit" }; //no upgrade func yet
	towerShopButtons = new String[] { "Basic Circle", "Basic Rect.", "Basic Triangle" }; 
	towerShopImages = new String[] { "images/circtower.png", "images/recttower.png", "images/tritower.png" };
	setup();

	//map.addWaypoint(386,147);
	map.addWaypoint(30,361);
	//map.addSpawn(559,149);
	map.addSpawn(384,361);
	
    }

    public void setup(){
	//Set up main window
	setTitle("Tower Defense");
	setSize(750,600); //set initial size
	setLocationRelativeTo(null); //center window
	setDefaultCloseOperation(EXIT_ON_CLOSE);
 
	//set up background
	background.setBackground(Color.WHITE);
	background.setLayout(new FlowLayout());
	background.addKeyListener(this);
	background.setFocusable(true);

	//set up ui panel
	ui.setLayout(new GridLayout(5,1));
	//ui.addKeyListener(this);
	ui.setFocusable(false);

	//create map
	map.addMouseListener(this);
	map.setFocusable(true);

	//Set up buttons
	
	buttons.setLayout(new GridLayout(5,1));

	for(int i = 0; i < uiButtons.length; i++){

	    JButton button = new JButton(uiButtons[i]);
	    button.setActionCommand(uiButtonsAC[i]);
	    button.addActionListener(this);
	    buttons.add(button);
	}

	//Set up shop
	tshop.setLayout(new GridLayout(2,3));
	JLabel t = new JLabel("Towers");
	t.setHorizontalAlignment(SwingConstants.CENTER);
	tshop.add(new JLabel(""));
	tshop.add(t);
	tshop.add(new JLabel(""));

	for(int i = 0; i < towerShopButtons.length; i++){
	    try {
		Image img = ImageIO.read(new File(towerShopImages[i]));
		ImageIcon icon = new ImageIcon(img);
		JButton button = new JButton(icon);
		button.setActionCommand(towerShopButtons[i]);
		button.addActionListener(this);
		tshop.add(button);
	    } catch (IOException e){
		e.printStackTrace();
	    }
	}

	//Set up label
	info = new JLabel("<html>INFO BOX OF DOOM<br><br><br><br></html>");    
	player = new JLabel(String.format("<html>Player Info<br><br>Gold: %d<br>Lives: %d<br></html>",
					  gold,lives));
	background.add(map);
	ui.add(tshop);
	ui.add(player);
	ui.add(info);
	ui.add(buttons);
	background.add(ui);
	
	pack(); //fit window to content size
	setLocationRelativeTo(null); //center window
	//map.requestFocusInWindow(); //focus on map on start
    }

    public void displayInfo(){
	String str = "";
	str = String.format("<html>Player Info<br><br>Gold: %d<br>Lives: %d<br></html>",gold,lives);
	player.setText(str);
    }

    public void displayInfo(Tower current){
	String str = "";
	str += String.format("<html><b>%s<b><br><br>Range: %d<br>Attack Rate: %d<br>Damage: %d<br>",
			     current.getName(),current.getRange(),current.getRate(),current.getDamage());
	info.setText(str);
    }


    //ActionListener

    public void actionPerformed(ActionEvent e){
	String action = e.getActionCommand(); 
	//System.out.println(action);
	if(action.equals("Exit")){
	    setVisible(false);
	    System.exit(0);
	} //else if(action.equals("Sell")){
	// System.out.println("Sorry! The upgrade feature is not implemented yet.");
	 else if(action.equals("Spawn Mode") && !waypointing){
	    spawning = !spawning;
	    if(spawning){
		System.out.println("Spawn mode activated.");
		JButton b = (JButton) e.getSource();
		b.setText("Disable Spawn Mode");
		info.setText("<html>Click on map to spawn monsters.<br>Make sure to spawn waypoints first <br>or there will be errors.</html>");
	    } else {
		System.out.println("Spawn mode deactivated.");
		JButton b = (JButton) e.getSource();
		b.setText("Enable Spawn Mode");
		info.setText(currentShop + " Tower selected.");
	    }
	} else if(action.equals("Waypoint Mode") && !spawning){
	    waypointing = !waypointing;
	    if(waypointing){
		System.out.println("Waypoint mode activated.");
		JButton b = (JButton) e.getSource();
		b.setText("Disable Waypoint Mode");
		info.setText("<html>Click on map to spawn waypoints.</html>");
	    } else {
		System.out.println("Waypoint mode deactivated.");
		JButton b = (JButton) e.getSource();
		b.setText("Enable Waypoint Mode");
		info.setText(currentShop + " Tower selected.");
	    }
	} else if(!(spawning || waypointing)){
	    if(action.equals("Basic Circle")){
		currentShop = "Basic Circle";
		System.out.println("Circle tower selected.");
		info.setText("<html>Circle tower selected. <br><br>Click on the map to place the tower.</html>");
	    } else if(action.equals("Basic Rect.")){
		currentShop = "Basic Rect.";
		System.out.println("Rect tower selected.");
		info.setText("<html>Rect tower selected. <br><br>Click on the map to place the tower.</html>");
	    } else if(action.equals("Basic Triangle")){
		currentShop = "Basic Triangle";
		System.out.println("Tri tower selected.");
		info.setText("<html>Tri tower selected. <br><br>Click on the map to place the tower.</html>");
	    } 
	}
	else {
	    info.setText("<html><br><br><br></html>");
	}
    }

    //KeyListener
    
    public void keyReleased(KeyEvent e){

    }
    
    public void keyPressed(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){
	System.out.println("Key char: " + e.getKeyChar());
	if(e.getKeyChar() == ' '){
	    spawning = !spawning;
	    if(spawning){
		System.out.println("Spawn mode activated.");
	    } else {
		System.out.println("Spawn mode deactivated.");
	    }
	}
    }

    //MouseListener

    public void mouseClicked(MouseEvent e){
	System.out.println("You clicked on the map.");
	System.out.println("x-cor: " + e.getX() + "\ny-cor: " + e.getY());
	if(!(spawning || waypointing) && currentShop != ""){
	    Tower current = map.selectTower(e.getX(), e.getY());
	    if(current == null){
		if(!map.addTower(e.getX(),e.getY(),currentShop,gold)){
		    info.setText("Unable to place tower.");
		} else {
		    gold-=150;
		    info.setText("Tower placed.");
		}
		//map.addWaypoint(e.getX(), e.getY());
	    } else {
		//map.displayInfo(current);
		displayInfo(current);
		System.out.println("You selected " + current.getName());
	    }
	} else if(spawning){
	    //map.addEnemy(e.getX(),e.getY());
	    map.addSpawn(e.getX(),e.getY());
	} else if(waypointing){
	    map.addWaypoint(e.getX(),e.getY());
	}
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
