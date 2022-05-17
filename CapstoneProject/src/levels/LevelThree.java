package levels;

import java.util.ArrayList;

import core.Coin;
import core.DrawingSurface;
import menus.Screen;
import obstacles.Obstacle;
import obstacles.Wall;

public class LevelThree extends Screen {
	private ArrayList<Obstacle> obstacles;
	private Coin coin;
	public LevelThree(DrawingSurface surface) {
		super(800, 800);

		this.surface = surface;
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Wall(0,0,50,DRAWING_HEIGHT));
		obstacles.add(new Wall(52,0,DRAWING_WIDTH/3,DRAWING_HEIGHT-DRAWING_HEIGHT/3));
		obstacles.add(new Wall(0,DRAWING_HEIGHT-50,DRAWING_WIDTH/2,50));
		
	}
	
	public void setup() {
		spawnNewCoin(coin,100,700,30,30);
	}
	public void draw() {
		surface.background(211,211,211);
		
		for (Obstacle c : obstacles)
		{
			c.draw(surface);
			
		}
	}

	
	
	public void spawnNewCoin(Coin coin, int x,int y, int width, int height)
	{
		coin = new Coin(surface.loadImage("img/COIN.png"),x,y,width,height);
		obstacles.add(coin);
	}
}
