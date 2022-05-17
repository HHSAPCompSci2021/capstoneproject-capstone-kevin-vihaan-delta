package levels;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import core.Coin;
import core.Door;
import core.DrawingSurface;
import core.Main;
import core.Player;
import menus.Screen;
import menus.ScreenSwitcher;
import menus.ShopMenu;
import obstacles.Obstacle;
import obstacles.Saw;
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

	private Door door;
	private Player user;
	private List<Obstacle> obstacles;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Coin coin;
	private double angle;

	private Saw saw;

	/**
	 * Constructs walls
	 * 
	 * @param surface DrawingSurface object
	 */
	public LevelTwo(DrawingSurface surface) {
		super(800, 800);

		this.surface = surface;

		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Wall(0, 0, 50, DRAWING_HEIGHT));
		obstacles.add(new Wall(52, 0, DRAWING_WIDTH / 3, DRAWING_HEIGHT - DRAWING_HEIGHT / 3));
		obstacles.add(new Wall(0, DRAWING_HEIGHT - 50, DRAWING_WIDTH / 2, 50));
		obstacles.add(new Wall(DRAWING_WIDTH / 2 + 100, DRAWING_HEIGHT - 50, DRAWING_WIDTH / 2 + 90, 50));
// above is one which door is on
		obstacles.add(new Wall(DRAWING_WIDTH - 50, DRAWING_HEIGHT, 50, -DRAWING_HEIGHT));
		obstacles.add(new Wall(DRAWING_WIDTH / 2 + 50, DRAWING_HEIGHT / 4 + 50, 200, 200));
		obstacles.add(new Wall(DRAWING_WIDTH / 2 - 80, 0, DRAWING_WIDTH / 2 + 28, 50));

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
		door.draw(surface);
		// spike1.draw(surface);
		
		// saw.draw(surface);

		for (Obstacle c : obstacles) {
			c.draw(surface);
			surface.rotate((float) (angle));
		}
		
		
		surface.fill(255);
		surface.text("Coins: " + ShopMenu.coinsCollected,20 , 30);
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
			Obstacle c  = obstacles.get(i);
			if (user.intersects(c) && (c instanceof Spike || c instanceof Saw)) {
			Main.changeSong(4);
				spawnNewPlayer();
			}

			if (user.intersects(door)) {
				Main.changeSong(3);
				spawnNewPlayer();
				surface.switchScreen(3);
			}

			if (user.intersects(c) && (c instanceof Wall)) {
				
				user.bounce();
				
			}
			
			if (user.intersects(c) && (c instanceof Coin)) {
				ShopMenu.coinsCollected++;
				System.out.println(ShopMenu.coinsCollected);
				obstacles.remove(obstacles.get(i));
			}
		}
	}

	/**
	 * spawns new player
	 */
	public void spawnNewPlayer() {
		
		user = new Player(surface.loadImage("img/PLAYER.png"), 60, 700, 25, 50);
	}

	/**
	 * spawns new door
	 */
	public void spawnNewDoor() {
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), DRAWING_WIDTH / 2 + 200, DRAWING_HEIGHT - 150, 50, 100);
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
	}
	
	public void spawnNewCoin(Coin coin, int x,int y, int width, int height)
	{
		coin = new Coin(surface.loadImage("img/COIN.png"),x,y,width,height);
		obstacles.add(coin);
	}
	public void rotate(double angle1) {
		angle = angle1;
	}
}
