package levels;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import core.Door;
import core.DrawingSurface;
import core.Player;
import menus.Screen;
import obstacles.Obstacle;
import obstacles.Saw;
import obstacles.Spike;
import obstacles.Wall;
import menus.ScreenSwitcher;


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
	private List<Obstacle> obstacles;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Spike spike4;
	private Spike spike5;
	
	private Saw saw;
	
	/**
	 * Initializes level
	 * @param surface Drawingsurface object
	 */
	public LevelOne(DrawingSurface surface) {
		super(800, 600);
		
		this.surface = surface;
		
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Wall(0,0,50,DRAWING_HEIGHT));
		obstacles.add(new Wall(52,0,DRAWING_WIDTH/3,DRAWING_HEIGHT-DRAWING_HEIGHT/3));
		obstacles.add(new Wall(0,DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50));
		obstacles.add(new Wall(DRAWING_WIDTH/2+150,DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50 ));
		
		obstacles.add(new Wall(DRAWING_WIDTH-50,DRAWING_HEIGHT,50,-DRAWING_HEIGHT ));
		obstacles.add(new Wall(DRAWING_WIDTH/2+50,DRAWING_HEIGHT/4+50,200,200 ));
		obstacles.add(new Wall(DRAWING_WIDTH/2-80,0,DRAWING_WIDTH/2+28,50 ));
		
	}
	/**
	 * Sets up all components with images
	 */
	public void setup()
	{
		spawnNewPlayer();
		spawnNewDoor();
		spawnNewSpike(spike1,DRAWING_WIDTH/2,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike2,DRAWING_WIDTH/2+30,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike3,DRAWING_WIDTH/2+60,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike4,DRAWING_WIDTH/2+90,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike5,DRAWING_WIDTH/2+120,DRAWING_HEIGHT-50,30,50);
		//spawnNewSaw();
	}
	/**
	 * draws the window, checks intersections
	 */
	public void draw()
	{
		
		surface.background(211,211,211);
		user.draw(surface);
		door.draw(surface);
	//	spike1.draw(surface);
		
	//	saw.draw(surface);
		
		for (Obstacle c : obstacles)
		{
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


		
		for (Obstacle c : obstacles)
		{
			if (user.intersects(c) && (c instanceof Spike) )
			{
			
				spawnNewPlayer();
			}
			
			if (user.intersects(door)) {
				spawnNewPlayer();
				surface.switchScreen(0);
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
		user = new Player(surface.loadImage("img/PLAYER.png"), 60,500, 25, 50);
	}
	/**
	 * spawns new door
	 */
	public void spawnNewDoor()
	{
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), DRAWING_WIDTH/2+100, DRAWING_WIDTH/4-100, 50, 100);
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
 * spawns in new saw
 */
	public void spawnNewSaw() 
	{
		saw = new Saw(surface.loadImage("img/SAW.png"),100,100,50,50);
	}
	/**
	 * rotates the level
	 */
	public void rotate() {
		
		surface.rotate(90);
		
		
	}
	
	
}
