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




public class LevelOne extends Screen {

	private DrawingSurface surface;
	
	private Rectangle screenRect;
	private Door door;
	private Player user;
	private List<Obstacle> obstacles;
	private Spike spike1;
	private Spike spike2;
	private Spike spike3;
	private Spike spike4;
	
	private Saw saw;
	
	
	public LevelOne(DrawingSurface surface) {
		super(800, 600);
		
		this.surface = surface;
		
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Wall(0,0,50,DRAWING_HEIGHT));
		obstacles.add(new Wall(52,0,DRAWING_WIDTH/3,DRAWING_HEIGHT-DRAWING_HEIGHT/3));
		obstacles.add(new Wall(0,DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50));
		obstacles.add(new Wall(DRAWING_WIDTH/2+120,DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50 ));
		
	
		
		
	}
	public void setup()
	{
		spawnNewPlayer();
		spawnNewDoor();
		spawnNewSpike(spike1,DRAWING_WIDTH/2,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike2,DRAWING_WIDTH/2+30,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike3,DRAWING_WIDTH/2+60,DRAWING_HEIGHT-50,30,50);
		spawnNewSpike(spike4,DRAWING_WIDTH/2+90,DRAWING_HEIGHT-50,30,50);
		spawnNewSaw();
	}
	
	public void draw()
	{
		
		surface.background(211,211,211);
		user.draw(surface);
		door.draw(surface);
	//	spike1.draw(surface);
		
		saw.draw(surface);
		
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

		if (spike1 != null )
		{
			if (spike1.intersects(user))
			spawnNewPlayer();
		}
		
		if (!screenRect.intersects(user))
			spawnNewPlayer();
	
	}
	public void spawnNewPlayer()
	{
		user = new Player(surface.loadImage("img/PLAYER.png"), 60,500, 25, 50);
	}
	public void spawnNewDoor()
	{
		door = new Door(surface.loadImage("img/GRAYDOOR2.jpg"), 200, 200, 50, 100);
	}
	
	public void spawnNewSpike(Spike spike, int x ,int y, int width, int height) {
		
		spike = new Spike(surface.loadImage("img/SPIKE.png"),x,y,width,height);
		obstacles.add(spike);
	}
	

	public void spawnNewSaw() 
	{
		saw = new Saw(surface.loadImage("img/SAW.png"),100,100,50,50);
	}
	
	
	
	
}
