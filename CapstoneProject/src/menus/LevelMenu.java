package menus;

import java.awt.Rectangle;

import core.DrawingSurface;

public class LevelMenu extends Screen {
	
	
	private DrawingSurface surface;
	

	private Rectangle screenRect;
	
	
	
	public LevelMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		
		// TODO Auto-generated constructor stub
	}

	
	
	public void draw()
	{
		surface.background(0,255,255);   
		surface.rect(100,100,100,100);
	}
}
