package levels;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
import processing.core.PImage;

/**
 * Class represents levelseven
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *
 */
public class LevelSeven extends Screen {
	private DrawingSurface surface;
	private Rectangle screenRect;
	private Door door;
	private Player user;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Obstacle> initial;
	private Saw spike1;
	private Saw spike2;
	private Saw spike3;
	
	private Spike spike4;
	private Spike spike5;
	private Spike spike6;
	
	
	private SpeedBoost sCoin;
	private double angle;

	public int status; // 0 is initial, 1 is 90 degree to left,2, 3

	private Saw saw;

	/**
	 * Constructs walls
	 * 
	 * @param surface DrawingSurface object
	 */
	public LevelSeven(DrawingSurface surface) {
		super(800, 800);

		this.surface = surface;
		screenRect = new Rectangle(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, DRAWING_WIDTH, DRAWING_HEIGHT);
// -DRAWING_WIDTH/2     -DRAWING_HEIGHT/2    
		obstacles = new ArrayList<Obstacle>();
		initial = new ArrayList<Obstacle>();
		
		obstacles.add(new Wall(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, 50, DRAWING_HEIGHT));


		obstacles.add(new Wall(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2 + DRAWING_HEIGHT - 50, DRAWING_WIDTH / 2 , 50));

		status = 0;

		obstacles.add(new Wall(-DRAWING_WIDTH / 2 + DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2 + DRAWING_HEIGHT - 50,DRAWING_WIDTH / 5, 50));
		
		obstacles.add(new Wall(-DRAWING_WIDTH/2 + DRAWING_WIDTH / 2 + DRAWING_WIDTH / 5 + 100, -DRAWING_HEIGHT / 2 + DRAWING_HEIGHT - 50,DRAWING_WIDTH/6+10,50 ));
		
		obstacles.add(new Wall(-DRAWING_WIDTH / 2+DRAWING_WIDTH-50, -DRAWING_HEIGHT / 2, 50, DRAWING_HEIGHT/2+50));
		
		obstacles.add(new Wall(-DRAWING_WIDTH / 2+DRAWING_WIDTH-50, -DRAWING_HEIGHT / 2 +DRAWING_HEIGHT/2+150, 50,200));
		
		obstacles.add(new Wall(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, DRAWING_WIDTH, 50));

		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets up player, spike, door
	 */
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

		
		spawnNewSaw(spike1, -DRAWING_WIDTH / 2+ DRAWING_WIDTH-50-20, -DRAWING_HEIGHT /3 + 40, 30, 30);
		spawnNewSaw(spike2, -DRAWING_WIDTH / 2 + DRAWING_WIDTH-50-20, -DRAWING_HEIGHT/3 + 110, 30, 30);
		spawnNewSaw(spike3, -DRAWING_WIDTH / 2 + DRAWING_WIDTH-50-20, -DRAWING_HEIGHT/3 + 180, 30, 30);
		
		spawnNewSpeedBoost(sCoin,-DRAWING_WIDTH / 2+DRAWING_WIDTH-85,320,30,30);
		
		spawnNewDownSpike(spike4,-DRAWING_WIDTH / 2 + DRAWING_WIDTH / 2+30,-DRAWING_HEIGHT/2+50,30,30);
		spawnNewDownSpike(spike5,-DRAWING_WIDTH / 2+ DRAWING_WIDTH / 2+60,-DRAWING_HEIGHT/2+50,30,30);
		spawnNewDownSpike(spike6,-DRAWING_WIDTH / 2+ DRAWING_WIDTH / 2 +90,-DRAWING_HEIGHT/2+50,30,30);
		
		
		
		
//		spawnNewCoin(coin, 100, 700, 30, 30);
		for (Obstacle a : obstacles) {
			initial.add(a);
		}

	}

	/**
	 * draws the level
	 */
	public void draw() {
		// System.out.println(initial.size()+" " + obstacles.size());
		surface.background(211, 211, 211);

		user.draw(surface);
		// door.draw(surface);
		// spike1.draw(surface);
		// System.out.println(angle);
		// saw.draw(surface);
		// if (angle!=0) {
		// surface.rotate((float) (angle));
		// rotate(0);
		// }

		if (status == 0) {
			// clear and add new here?
			// obstacles = initial
			obstacles.clear();
			for (int i = 0; i < initial.size(); i++) {
				Obstacle a = initial.get(i);

				if (a instanceof Door) {
					a.image = doorRight;
				}

				
				obstacles.add(a);
			}
		} else if (status == 1) {
			door.image = doorDown;
			
			obstacles = rotateAll(initial, Math.PI / 2);
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle a = obstacles.get(i);

				if (a instanceof Spike) {
					a.image = spikeLeft;
				}

			}
		} else if (status == 2) {
			door.image = doorLeft;
			
			obstacles = rotateAll(initial, Math.PI);
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle a = obstacles.get(i);

				if (a instanceof Spike) {
					a.image = spikeUp;
				}

			}
		} else if (status == 3) {
			door.image = doorUp;
			
			obstacles = rotateAll(initial, 3 * Math.PI / 2);
			for (int i = 0; i < obstacles.size(); i++) {
				Obstacle a = obstacles.get(i);

				if (a instanceof Spike) {
					a.image = spikeRight;
				}

			}
		}

		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle c = obstacles.get(i);
			// System.out.println(c.x + " " + c.y);
			c.draw(surface);

		}

//		surface.fill(255);
//		surface.text("Coins: " + ShopMenu.coinsCollected,20 , 30);
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
			
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof Door) {
				Main.playSoundEffect(Main.doorNumber);
				spawnNewPlayer();
				//setup();
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
			
			
		
		}
	}

	/**
	 * spawns in new saw
	 */
	public void spawnNewSaw(Saw saw, int x, int y, int width, int height) {
		saw = new Saw(surface.loadImage("img/SAW.png"), x, y, width, height);
		obstacles.add(saw);
	}

	/**
	 * spawns new player
	 */
	public void spawnNewPlayer() {
		status = 0;
		angle = 0;
		// rotate(0);
		user = new Player(surface.loadImage("img/PLAYER.png"), -340, 300, 25, 50);
		door.image = doorUp;
		// obstacles.add(user); x coord - -340
	}

	/**
	 * spawns new door
	 */
	public void spawnNewDoor() {
		door = new Door(doorRight, -DRAWING_WIDTH / 2 + 50, -DRAWING_HEIGHT / 2 + 400, 100, 50);
		obstacles.add(door);
	}
	
	/**
	 * spawns new down spike
	 * 
	 * @param spike  object to spawn in
	 * @param x      x-coordinate
	 * @param y      y-coordinate
	 * @param width  width of spike
	 * @param height height of spike
	 */
	public void spawnNewDownSpike(Spike spike, int x, int y, int width, int height) {

		spike = new Spike(surface.loadImage("img/DOWNSPIKE.png"), x, y, width, height);
		obstacles.add(spike);
	}
	/**
	 * spawns new left spike
	 * 
	 * @param spike  object to spawn in
	 * @param x      x-coordinate
	 * @param y      y-coordinate
	 * @param width  width of spike
	 * @param height height of spike
	 */
	public void spawnNewLeftSpike(Spike spike, int x, int y, int width, int height) {

		spike = new Spike(surface.loadImage("img/LEFTSPIKE.png"), x, y, width, height);
		obstacles.add(spike);
	}

	/**
	 * spawns new spike
	 * 
	 * @param spike  object to spawn in
	 * @param x      x-coordinate
	 * @param y      y-coordinate
	 * @param width  width of spike
	 * @param height height of spike
	 */
	public void spawnNewSpike(Spike spike, int x, int y, int width, int height) {

		spike = new Spike(surface.loadImage("img/SPIKE.png"), x, y, width, height);
		obstacles.add(spike);
	}

	/**
	 * spawns new coin
	 * 
	 * @param coin   object to spawn in
	 * @param x      x-coordinate
	 * @param y      y-coordinate
	 * @param width  width of coin
	 * @param height height of coin
	 */
	public void spawnNewCoin(Coin coin, int x, int y, int width, int height) {
		coin = new Coin(surface.loadImage("img/COIN.png"), x, y, width, height);
		obstacles.add(coin);
	}

	/**
	 * spawns new speedboost
	 * 
	 * @param sCoin  object to spawn in
	 * @param x      x-coordinate
	 * @param y      y-coordinate
	 * @param width  width of speedBoost
	 * @param height height of speedBoost
	 */
	public void spawnNewSpeedBoost(SpeedBoost sCoin, int x, int y, int width, int height) {
		sCoin = new SpeedBoost(surface.loadImage("img/SPEEDBOOST.png"), x, y, width, height);
		obstacles.add(sCoin);
	}

	/**
	 * spawns new powerCoin
	 * 
	 * @param pCoin  object to spawn in
	 * @param x      x-coordinate
	 * @param y      y-coordinate
	 * @param width  width of powerCoin
	 * @param height height of powerCoin
	 */
	public void spawnNewPowerCoin(PowerCoin pCoin, int x, int y, int width, int height) {
		pCoin = new PowerCoin(surface.loadImage("img/POWERCOIN.png"), x, y, width, height);
		obstacles.add(pCoin);
	}

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
		// changeDoor();

	}

}
