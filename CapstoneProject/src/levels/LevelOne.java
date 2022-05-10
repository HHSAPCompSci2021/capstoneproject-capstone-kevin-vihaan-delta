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
		obstacles.add(new Wall(100,100,100,100));
		
		
		
	}
	public void setup()
	{
		
	}
	
	public void draw(DrawingSurface surface)
	{
		surface.background(0,15,255);
		
		surface.fill(0);
		for (Obstacle c : obstacles)
		{
			c.draw(surface);
		}
		
	}
	public void spawnNewPlayer()
	{
		
	}
	
	
	
	
	
	
}
