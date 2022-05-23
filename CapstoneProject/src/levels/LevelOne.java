package levels;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import core.Main;
import core.Player;
import menus.Screen;
import obstacles.Coin;
import obstacles.Door;
import obstacles.Obstacle;
import obstacles.PowerCoin;
import obstacles.Saw;
import obstacles.SpeedBoost;
import obstacles.Spike;
import obstacles.Wall;
import menus.ScreenSwitcher;
import menus.ShopMenu;


/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *Class Represents LevelOne
 */



public class LevelOne extends Screen {

	//private DrawingSurface surface;
	
	private Rectangle screenRect;
	private Door door;
	private Player user;
	private int iX;
	private int iY;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Obstacle> initial;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Spike spike4;
	private Spike spike5;
	private Coin coin;
	private Saw saw;
	private double angle;
	private PowerCoin pCoin;
	private SpeedBoost sCoin;
	public int status;
	
	/**
	 * Initializes level
	 * @param surface Drawingsurface object
	 */
	public LevelOne(DrawingSurface surface) {
		super(800, 800);
		
		this.surface = surface;
		iX = -DRAWING_WIDTH/2+60;
		iY = -DRAWING_HEIGHT/2+700;
		screenRect = new Rectangle(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Obstacle>();
		initial = new ArrayList<Obstacle>();
		obstacles.add(new Wall(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2,DRAWING_HEIGHT/16,DRAWING_HEIGHT)); 
		obstacles.add(new Wall(-DRAWING_WIDTH/2 + 52,-DRAWING_HEIGHT/2,DRAWING_WIDTH/3,DRAWING_HEIGHT-DRAWING_HEIGHT/3));
		obstacles.add(new Wall(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50));
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH/2+150,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50 ));
//		
		
		status = 0;
		
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH-50,-DRAWING_HEIGHT/2,50,DRAWING_HEIGHT ));
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH/2+50,-DRAWING_HEIGHT/2+DRAWING_HEIGHT/4+50,200,200 ));
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH/2-80,0,-DRAWING_HEIGHT/2+DRAWING_WIDTH/2+28,50 ));
		
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH/2-80,-DRAWING_HEIGHT/2,DRAWING_WIDTH/2,50 ));
		
	}
	/**
	 * Sets up all components with images
	 */
	public void setup()
	{
	//	spawnNewSaw(saw,DRAWING_WIDTH/2-100,DRAWING_HEIGHT-75,25,25);
		spawnNewPlayer();
		spawnNewDoor();
		
		for (Obstacle a: obstacles) {
			initial.add(a);
		}
		spawnNewSpike(spike1,-DRAWING_WIDTH/2+DRAWING_WIDTH/2,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike2,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+30,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike3,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+60,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike4,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+90,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike5,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+120,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewCoin(coin,-DRAWING_WIDTH/2+100,-DRAWING_HEIGHT/2+700,30,30);
	//	spawnNewSpeedBoost(sCoin,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+200, -DRAWING_HEIGHT/2+DRAWING_HEIGHT-100, 30,30);
		//spawnNewPowerCoin(pCoin,-DRAWING_WIDTH/2+DRAWING_WIDTH/2, -DRAWING_HEIGHT/2+DRAWING_HEIGHT/2-50, 30,30);
	spawnNewPowerCoin(pCoin,iX + 75, iY, 30,30);
	spawnNewSpeedBoost(sCoin,iX + 100, iY, 30,30);
	}
	/**
	 * draws the window, checks intersections
	 */
	public void draw()
	{
		
		surface.background(211,211,211);
		
		user.draw(surface);
		
		
	//	System.out.println(angle);
		
		if (status == 0) {
			// clear and add new here?
			//obstacles = initial
			obstacles.clear();
			for (Obstacle a: initial) {
				obstacles.add(a);
			}
		} else if (status == 1) {
			
			obstacles = rotateAll(initial, Math.PI/2);
		} else if (status == 2) {
			obstacles = rotateAll(initial, Math.PI);
		} else if (status == 3) {
			obstacles = rotateAll(initial, 3*Math.PI/2);
		}

		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle c = obstacles.get(i);
			// System.out.println(c.x + " " + c.y);
			c.draw(surface);

		}
		
	
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			user.move(-1);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			user.move(1);
		if (surface.isPressed(KeyEvent.VK_UP))
			user.jump();
		
		user.act(obstacles);


		//anothe rarraylist wit things that need t be deleted
		for (int i = obstacles.size()-1; i >= 0; i--)
		{
			if (user.intersects(obstacles.get(i)) && ((obstacles.get(i) instanceof Spike) || obstacles.get(i) instanceof Saw))
			{
			 Main.changeSong(4);
				
			 	spawnNewPlayer();
				//setup();
			//	obstacles.remove(i);
				ShopMenu.coinsCollected--;
			}
			
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof Door) {
				Main.changeSong(3);
				spawnNewPlayer();
				surface.switchScreen(3);
				Player.speedMultiplier = 1;
			}
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof Coin)
			{
				if (Coin.doubleValue) {
					ShopMenu.coinsCollected += 2;
				} else {
				ShopMenu.coinsCollected++;
				}
			
				obstacles.remove(obstacles.get(i));
				i--;
				
			}
			if ( !user.intersects(screenRect))
			{
				
				spawnNewPlayer();
			}
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof PowerCoin) 
			{
				
				ShopMenu.coinsCollected += 10;
				obstacles.remove(obstacles.get(i));
				i--;
			}
			
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof SpeedBoost)
			{
				Player.speedMultiplier += 0.5;
				obstacles.remove(obstacles.get(i));
				i--;
			}
			
			
		
		}
		
//		if (!screenRect.intersects(user))
//		
//			spawnNewPlayer();
			
	
	}
	/**
	 * spawns new player
	 */
	public void spawnNewPlayer()
	
	{
		
		user = new Player(surface.loadImage("img/PLAYER.png"), iX,iY, 25, 50);
		obstacles.add(user);
		
	}
	/**
	 * spawns new door
	 */
	public void spawnNewDoor()
	{
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), -DRAWING_WIDTH/2+ DRAWING_WIDTH/2 + 60, -DRAWING_HEIGHT/2+ DRAWING_HEIGHT/2-270, 50, 120);
		obstacles.add(door);
	}
	/**
	 * spawns new spike
	 * @param spike object to spawn in
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width width of spike
	 * @param height height of spike
	 */
	public void spawnNewSpike(Spike spike, int x ,int y, int width, int height) {
		
		spike = new Spike(surface.loadImage("img/SPIKE.png"),x,y,width,height);
		obstacles.add(spike);
	}
	/**
	 * spawns new down spike
	 * @param spike object to spawn in
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width width of spike
	 * @param height height of spike
	 */
public void spawnNewSpikeDown(Spike spike, int x ,int y, int width, int height) {
		
		spike = new Spike(surface.loadImage("img/SPIKEDOWN.png"),x,y,width,height);
		obstacles.add(spike);
	}
/**
 * spawns new coin
 * @param coin object to spawn in
 * @param x x-coordinate
 * @param y y-coordinate
 * @param width width of coin
 * @param height height of coin
 */
	public void spawnNewCoin(Coin coin, int x,int y, int width, int height)
	{
		coin = new Coin(surface.loadImage("img/COIN.png"),x,y,width,height);
		obstacles.add(coin);
	}
	/**
	 * spawns new speedboost
	 * @param sCoin object to spawn in
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width width of speedBoost
	 * @param height height of speedBoost
	 */
	public void spawnNewSpeedBoost(SpeedBoost sCoin, int x,int y, int width, int height)
	{
		sCoin = new SpeedBoost(surface.loadImage("img/SPEEDBOOST.png"),x,y,width,height);
		obstacles.add(sCoin);
	}
	/**
	 * spawns new powerCoin
	 * @param pCoin object to spawn in
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width width of powerCoin
	 * @param height height of powerCoin
	 */
	public void spawnNewPowerCoin(PowerCoin pCoin, int x,int y, int width, int height)
	{
		pCoin = new PowerCoin(surface.loadImage("img/POWERCOIN.png"),x,y,width,height);
		obstacles.add(pCoin);
	}
	


	/**
	 * 
	 * @param saw object to spawn in
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width width of Saw
	 * @param height height of Saw
	 */
	
	/**
	 * rotates spawn
	 */

	public void rotateSpawn()
	{
		int temp = iX;
	//	iX = iX*Math.cos(angle) - iY*Math.sin(angle);
	}
//	/**
//	 * rotates all obstacles to get correct points
//	 * @param list list of obstacles
//	 * @param angle3  angle that is rotated by
//	 */
//	public void rotateObstacles(ArrayList<Obstacle> list, double angle3)
//	{
//		
//		double temp = 0;
//		for (Obstacle c : list)
//		{
//			temp = c.x;
//			c.x = c.x * Math.cos(angle3) - c.y*Math.sin(angle3);
//			c.y = c.y*Math.cos(angle3)  + temp*Math.sin(angle3);
//			
//		if ( angle % Math.PI/2 == 0 && angle % Math.PI != 0)
//		{
//			double temp1 = c.height;
//			c.height = c.width;
//			c.width = temp1;
//			
//			
//		}
//			
//				
//			
//			
//		}
//	}
	/**
	 * changes the angle to rotate the screen
	 */
	public void rotate(double angle1) {
		angle += angle1;
		if (equals(angle, 2 * Math.PI) || equals(angle, -2 * Math.PI)) {
			angle = 0;

		}
		if (equals(angle, 0)) {
			status = 0;
		}
		if (equals(angle, 3 * Math.PI / 2) || equals(angle, -Math.PI / 2)) {
			status = 3;
		}
		if (equals(angle, Math.PI / 2) || equals(angle, -3 * Math.PI / 2)) {
			status = 1;
		}
		if (equals(angle, Math.PI) || equals(angle, -Math.PI)) {
			status = 2;
		}

	}
	
}
