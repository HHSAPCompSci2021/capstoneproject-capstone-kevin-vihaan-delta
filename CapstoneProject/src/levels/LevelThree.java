package levels;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import core.DrawingSurface;
import core.Main;
import core.Player;
import menus.Screen;
import menus.ScreenSwitcher;
import menus.ShopMenu;
import obstacles.Coin;
import obstacles.Door;
import obstacles.Obstacle;
import obstacles.PowerCoin;
import obstacles.Saw;
import obstacles.SpeedBoost;
import obstacles.Spike;
import obstacles.Wall;
/**
 * class represents level three
 * @author vihaanchinthakindi and kevinvalencia
 *
 */

public class LevelThree extends Screen {
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Obstacle> initial;
	private Coin coin;
	private double angle;
	
	//set1
	private Rectangle screenRect;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Spike spike4;
	private Spike spike5;
	
	
	private int iX;
	private int iY;
	
	private Spike spike6;
	private Spike spike7;
	private Spike spike8;

	private PowerCoin pCoin;
	private SpeedBoost sCoin;
	
	private Door door;
	private Player user;
	
	public int status;
	
	public LevelThree(DrawingSurface surface) {
		super(800, 800);
		
		this.surface = surface;
		
		obstacles = new ArrayList<Obstacle>();
		initial = new ArrayList<Obstacle>();
		iX =  -DRAWING_WIDTH/2+DRAWING_WIDTH-100;
		iY = -DRAWING_HEIGHT/2+700;
		
		status = 0;
		
		screenRect = new Rectangle(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH/2+150,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50 ));
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH-50,-DRAWING_HEIGHT/2,50,DRAWING_HEIGHT ));
		obstacles.add(new Wall(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2,20,DRAWING_HEIGHT ));
//		obstacles.add(new Wall(-DRAWING_WIDTH/2 +DRAWING_WIDTH/2+90,-DRAWING_HEIGHT/2-DRAWING_HEIGHT/2,DRAWING_HEIGHT/4+60,DRAWING_HEIGHT+100)); 
//		obstacles.add(new Wall(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2,DRAWING_HEIGHT/4,DRAWING_HEIGHT+100)); 
		obstacles.add(new Wall(-DRAWING_WIDTH/2 ,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,DRAWING_HEIGHT/4+DRAWING_HEIGHT/4,DRAWING_HEIGHT/4)); 
		obstacles.add(new Wall(-DRAWING_WIDTH/2 +DRAWING_WIDTH/2-10,-DRAWING_HEIGHT/2,DRAWING_HEIGHT/8,DRAWING_HEIGHT/2-50)); 
		obstacles.add(new Wall(-DRAWING_WIDTH/2 ,-DRAWING_HEIGHT/2,DRAWING_HEIGHT,20)); 
		
//		obstacles.add(new Wall(-DRAWING_WIDTH/2 +DRAWING_HEIGHT/4,-DRAWING_HEIGHT/2,DRAWING_HEIGHT/4-10,DRAWING_HEIGHT/2)); 
	///	obstacles.add(new Wall(52,20,DRAWING_WIDTH/3,DRAWING_HEIGHT-DRAWING_HEIGHT/3));
//		obstacles.add(new Wall(0,DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50));
		
	}
	
	public void setup() {
		
		doorDown = surface.loadImage("img/DOORDOWN.png");
		doorRight = surface.loadImage("img/DOORRIGHT.png");
		doorLeft = surface.loadImage("img/DOORLEFT.png");
		doorUp = surface.loadImage("img/GRAYDOOR2.jpg");
		spikeDown = surface.loadImage("img/DOWNSPIKE.png");
		spikeRight = surface.loadImage("img/RIGHTSPIKE.png");
		spikeLeft = surface.loadImage("img/LEFTSPIKE.png");
		spikeUp = surface.loadImage("img/SPIKE.png");
		spawnNewDoor();
		spawnNewPlayer();
		spawnNewCoin(coin,100,700,30,30);
		spawnNewSpike(spike1,-DRAWING_WIDTH/2+DRAWING_WIDTH/2,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike2,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+30,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike3,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+60,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike4,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+90,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike5,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+120,-DRAWING_HEIGHT/2+DRAWING_HEIGHT-50,30,50);
		
//		spawnNewSpike(spike6,-DRAWING_WIDTH/2+DRAWING_WIDTH/2-10,-DRAWING_HEIGHT/2+DRAWING_HEIGHT/2-50,30,50);
//		spawnNewSpike(spike7,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+30,-DRAWING_HEIGHT/2+DRAWING_HEIGHT/2-50,30,50);
//		spawnNewSpike(spike8,-DRAWING_WIDTH/2+DRAWING_WIDTH/2+60,-DRAWING_HEIGHT/2+DRAWING_HEIGHT/2-50,30,50);

		spawnNewPowerCoin(pCoin,iX - 75, iY, 30,30);
		spawnNewSpeedBoost(sCoin,iX - 100, iY, 30,30);
		
		
		
		for (Obstacle a: obstacles) {
			initial.add(a);
		}
	}
	public void draw() {
		
		surface.background(211,211,211);
		user.draw(surface);
		
		
		if (status == 0) {
			// clear and add new here?
			// obstacles = initial
			obstacles.clear();
			for (int i = 0; i < initial.size(); i++) {
				Obstacle a = initial.get(i);

				if (a instanceof Door) {
					a.image = doorUp;
				}

				if (a instanceof Spike) {
					a.image = spikeUp;
				}
				obstacles.add(a);
			}
		} else if (status == 1) {
			door.image = doorRight;
			
			obstacles = rotateAll(initial, Math.PI / 2);
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle a = obstacles.get(i);

				if (a instanceof Spike) {
					a.image = spikeRight;
				}

			}
		} else if (status == 2) {
			door.image = doorDown;
			
			obstacles = rotateAll(initial, Math.PI);
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle a = obstacles.get(i);

				if (a instanceof Spike) {
					a.image = spikeDown;
				}

			}
		} else if (status == 3) {
			door.image = doorLeft;
			
			obstacles = rotateAll(initial, 3 * Math.PI / 2);
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle a = obstacles.get(i);

				if (a instanceof Spike) {
					a.image = spikeLeft;
				}

			}
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
		
		for (int i = obstacles.size()-1; i >= 0; i--)
		{
			if (user.intersects(obstacles.get(i)) && ((obstacles.get(i) instanceof Spike) || obstacles.get(i) instanceof Saw))
			{
				Main.playSoundEffect(Main.effectNumber);
				
			 	spawnNewPlayer();
				//setup();
			//	obstacles.remove(i);
				ShopMenu.coinsCollected--;
			}
			
			if (user.intersects(door)) {
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
				initial.remove(obstacles.get(i));
				obstacles.remove(obstacles.get(i));
				setup();
				i--;
				
			}
			if ( !user.intersects(screenRect))
			{
				
				spawnNewPlayer();
				//setup();
			}
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof PowerCoin) 
			{
				
				ShopMenu.coinsCollected += 10;
				initial.remove(obstacles.get(i));
				obstacles.remove(obstacles.get(i));
				i--;
			}
			
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof SpeedBoost)
			{
				Player.speedMultiplier += 0.5;
				initial.remove(obstacles.get(i));
				obstacles.remove(obstacles.get(i));
				i--;
			}
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof PowerCoin) 
			{
				
				ShopMenu.coinsCollected += 10;
				initial.remove(obstacles.get(i));
				obstacles.remove(obstacles.get(i));
				i--;
			}
			
			
			
			
			
		
		}
	}

	public void rotate(double angle1)
	{
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
	 * spawns new player
	 */
	public void spawnNewPlayer()
	{
		status = 0;
		angle = 0;
		user = new Player(surface.loadImage("img/PLAYERSIDE.png"), -DRAWING_WIDTH/2+DRAWING_WIDTH-100,-DRAWING_HEIGHT/2+700, 25, 50);
		//obstacles.add(user);
	}
	/**
	 * spawns new door
	 */
	public void spawnNewDoor()
	{
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), -DRAWING_WIDTH/2+DRAWING_WIDTH/3-200, -DRAWING_HEIGHT/2+DRAWING_WIDTH/4+450, 50, 100);
		obstacles.add(door);
	}
}
