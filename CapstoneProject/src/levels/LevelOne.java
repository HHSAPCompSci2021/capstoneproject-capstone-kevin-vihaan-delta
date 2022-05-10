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
import screens.ScreenSwitcher;




public class LevelOne extends Screen {

	private DrawingSurface surface;
	
	private Rectangle screenRect;
	private Door door;
	private Player user;
	private List<Obstacle> obstacles;
	private Spike spike;
	private Saw saw;
	
	
	public LevelOne(DrawingSurface surface) {
		super(800, 600);
		
		this.surface = surface;
		
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Wall(0,0,50,DRAWING_HEIGHT));
		obstacles.add(new Wall(52,0,DRAWING_WIDTH/2,50));
		
		
		
	}
	public void setup()
	{
		spawnNewPlayer();
		spawnNewDoor();
		spawnNewSpike();
		spawnNewSaw();
	}
	
	public void draw()
	{
		
		surface.background(211,211,211);
		user.draw(surface);
		door.draw(surface);
		spike.draw(surface);
		saw.draw(surface);
		
		for (Obstacle c : obstacles)
		{
			c.draw(surface);
		}
		
	
	}
	public void spawnNewPlayer()
	{
		user = new Player(surface.loadImage("img/PLAYER.png"), 500, 200, 50, 100);
	}
	public void spawnNewDoor()
	{
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), 200, 200, 50, 100);
	}
	public void spawnNewSpike()
	{
		spike = new Spike(surface.loadImage("img/SPIKE.png"),300,100,50,50);
	}
	public void spawnNewSaw() 
	{
		saw = new Saw(surface.loadImage("img/SAW.png"),100,100,50,50);
	}
	
	
	
	
}
