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

/**
 * Class represents level five
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *
 */
public class LevelFive extends Screen {
	
	//private ArrayList<Obstacle> obstacles;
	//private ArrayList<Obstacle> initial;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Spike spike4;
	private Spike spike5;
	
	private SpeedBoost b1;
	
	
	private Coin coin;
	//private double angle;
	
	//public int status; // 0 is initial, 1 is 90 degree to left,2, 3

	private Saw saw;

	/**
	 * Constructs walls
	 * 
	 * @param surface DrawingSurface object
	 */
	public LevelFive (DrawingSurface surface) {
		super(800, 800);

		this.surface = surface;
		screenRect = new Rectangle(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, DRAWING_WIDTH, DRAWING_HEIGHT);
// -DRAWING_WIDTH/2     -DRAWING_HEIGHT/2    
		obstacles = new ArrayList<Obstacle>();
		initial = new ArrayList<Obstacle>();
		
		
		//side left
		obstacles.add(new Wall(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, 50, DRAWING_HEIGHT));
		

		obstacles.add(new Wall(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2 + DRAWING_HEIGHT - 50, DRAWING_WIDTH / 2 - 100, 50));

		status = 0;

		obstacles.add(new Wall(-DRAWING_WIDTH / 2 + DRAWING_WIDTH / 2+60, -DRAWING_HEIGHT / 2 + DRAWING_HEIGHT - 50,DRAWING_WIDTH / 3+70, 50));

		
		
		obstacles.add(new Wall(-DRAWING_WIDTH / 2+DRAWING_WIDTH-50, -70, 50,420));
		
		obstacles.add(new Wall(-DRAWING_WIDTH / 2+DRAWING_WIDTH-50, -350, 50,190));
		
		//top
		obstacles.add(new Wall(-DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, DRAWING_WIDTH, 50));
		obstacles.add(new Wall(-DRAWING_WIDTH / 2+DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2 + 50, DRAWING_WIDTH/5, 50));
		
	

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
		spawnNewDoor(surface.loadImage("img/GRAYDOOR2.jpg"), -DRAWING_WIDTH/2+DRAWING_WIDTH / 2 +100,DRAWING_HEIGHT/2-120, 50, 70);
		spawnNewPlayer( -340, 300, 25, 50);
		
		 spawnNewSpike(spike1, -DRAWING_WIDTH/2+ DRAWING_WIDTH / 2-100, -DRAWING_HEIGHT/2+DRAWING_HEIGHT - 50, 30, 50);
		 spawnNewSpike(spike2, -DRAWING_WIDTH/2+DRAWING_WIDTH / 2 -70, -DRAWING_HEIGHT/2+DRAWING_HEIGHT - 50, 30, 50);
		 spawnNewSpike(spike3, -DRAWING_WIDTH/2+DRAWING_WIDTH / 2 -40,-DRAWING_HEIGHT/2+ DRAWING_HEIGHT - 50, 30, 50);
		 spawnNewSpike(spike4, -DRAWING_WIDTH/2+DRAWING_WIDTH / 2 -10,-DRAWING_HEIGHT/2+ DRAWING_HEIGHT - 50, 30, 50);
		 spawnNewSpike(spike5, -DRAWING_WIDTH/2+DRAWING_WIDTH / 2 +20,-DRAWING_HEIGHT/2+ DRAWING_HEIGHT - 50, 30, 50);
		 spawnNewSaw(saw, -370, DRAWING_HEIGHT/2-300,30,30);
		 spawnNewSaw(saw, -370, DRAWING_HEIGHT/2-340,30,30);
		 
		 spawnNewCoin(coin, 100, 700, 30, 30);
		 spawnNewSpeedBoost(b1,200,310,40,40);
	
		 for (Obstacle a: obstacles) {
				initial.add(a);
			}
	}

	/**
	 * draws the level
	 */
	public void draw() {
		//System.out.println(initial.size()+" " + obstacles.size());
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
				
				spawnNewPlayer( -340, 300, 25, 50);
				//setup();
			//	obstacles.remove(i);
				ShopMenu.coinsCollected--;
			}
			
			if (user.intersects(obstacles.get(i)) && obstacles.get(i) instanceof Door) {
				Main.playSoundEffect(Main.doorNumber);
				spawnNewPlayer( -340, 300, 25, 50);
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
				
				spawnNewPlayer( -340, 300, 25, 50);
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

	

	

	
	

}

