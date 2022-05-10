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
import obstacles.Wall;




public class LevelOne extends Screen {

	private DrawingSurface surface;
	
	private Rectangle screenRect;
	private Door door;
	private Player user;
	private List<Obstacle> obstacles;
	
	
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
		spawnNewDoor();
	}
	
	public void draw()
	{
		
		surface.background(211,211,211);
		
		door.draw(surface);
		for (Obstacle c : obstacles)
		{
			c.draw(surface);
		}
		
	}
	public void spawnNewPlayer()
	{
		
	}
	public void spawnNewDoor()
	{
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), 200, 200, 50, 100);
	}
	public void spawnNewSpike()
	{
		
	}
	public void spawnNewSaw() 
	{
		
	}
	
	
	
	
}
