package levels;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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


/**
 * Class represents leveltwo
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *
 */
public class LevelTwo extends Screen {
	private DrawingSurface surface;
	private Rectangle screenRect;
	private Door door;
	private Player user;
	private List<Obstacle> obstacles;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Coin coin;
	private double angle;
	private double angleDifference;

	private Saw saw;

	/**
	 * Constructs walls
	 * 
	 * @param surface DrawingSurface object
	 */
	public LevelTwo(DrawingSurface surface) {
		super(800, 800);

		this.surface = surface;
		screenRect = new Rectangle(-DRAWING_WIDTH/2,-DRAWING_HEIGHT/2,DRAWING_WIDTH,DRAWING_HEIGHT);
// -DRAWING_WIDTH/2     -DRAWING_HEIGHT/2    
		obstacles = new ArrayList<Obstacle>();
		//obstacles.add(new Wall(-DRAWING_WIDTH/2, -DRAWING_HEIGHT/2, 50, DRAWING_HEIGHT));
		//obstacles.add(new Wall( -DRAWING_WIDTH/2 + 52, -DRAWING_HEIGHT/2, DRAWING_WIDTH / 3, DRAWING_HEIGHT - DRAWING_HEIGHT / 3));
		obstacles.add(new Wall(-DRAWING_WIDTH/2, -DRAWING_HEIGHT/2+DRAWING_HEIGHT - 50, DRAWING_WIDTH / 2, 50));
		obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH / 2 + 100,-DRAWING_HEIGHT/2+ DRAWING_HEIGHT - 50, DRAWING_WIDTH / 2 + 90, 50));
// above is one which door is on
	//obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH - 50, -DRAWING_HEIGHT/2+DRAWING_HEIGHT, 50, -DRAWING_HEIGHT));
	//	obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH / 2 + 50,-DRAWING_HEIGHT/2+ DRAWING_HEIGHT / 4 + 50, 200, 200));
	//	obstacles.add(new Wall(-DRAWING_WIDTH/2+DRAWING_WIDTH / 2 - 80, 0, -DRAWING_HEIGHT/2+DRAWING_WIDTH / 2 + 28, 50));

		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets up player, spike, door
	 */
	public void setup() {
		spawnNewPlayer();
		spawnNewDoor();
		spawnNewSpike(spike1, DRAWING_WIDTH / 2, DRAWING_HEIGHT - 50, 30, 50);
		spawnNewSpike(spike2, DRAWING_WIDTH / 2 + 30, DRAWING_HEIGHT - 50, 30, 50);
		spawnNewSpike(spike3, DRAWING_WIDTH / 2 + 60, DRAWING_HEIGHT - 50, 30, 50);
		spawnNewCoin(coin,100,700,30,30);

	}

	/**
	 * draws the level
	 */
	public void draw() {
		surface.background(211, 211, 211);
		
		user.draw(surface);
		//door.draw(surface);
		// spike1.draw(surface);
		//System.out.println(angle);
		// saw.draw(surface);
		if (angle!=0) {
		surface.rotate((float) (angle));
		rotate(0);
		}
		
		for (int i = 0; i< obstacles.size();i++) {
			Obstacle c = obstacles.get(i);
			double temp = c.x;
			//System.out.println(angleDifference+"ok");
			c.x = super.rotateXPoint(c.x,c.y,angleDifference);
			c.y = super.rotateYPoint(temp, c.y, angleDifference);
			
		//	obstacles.set(i, new Obstacle(c.image,c.x,c.y,c.width,c.height));
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

		
		
		for (int i = obstacles.size()-1; i >= 0; i--) {
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
	}

	/**
	 * spawns new player
	 */
	public void spawnNewPlayer() {
		
		user = new Player(surface.loadImage("img/PLAYER.png"), -340, 300, 25, 50);
		//obstacles.add(user); x coord - -340
	}

	/**
	 * spawns new door
	 */
	public void spawnNewDoor() {
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), -DRAWING_WIDTH/2+300, -DRAWING_HEIGHT/2+650, 50, 100);
		obstacles.add(door);
	}

	/**
	 * spawns new spike
	 * 
	 * @param spike  spike to be spawned
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
	 * spawns in new saw
	 */
	public void spawnNewSaw() {
		saw = new Saw(surface.loadImage("img/SAW.png"), 100, 100, 50, 50);
		obstacles.add(saw);
	}
	/**
	 * spawns in new coin
	 */
	public void spawnNewCoin(Coin coin, int x,int y, int width, int height)
	{
		coin = new Coin(surface.loadImage("img/COIN.png"),x,y,width,height);
		obstacles.add(coin);
	}
	/**
	 * changes the angle to rotate the screen
	 */
	public void rotate(double angle1) {
		angle += angle1;
		angleDifference = angle1;
	}
	
	
}
