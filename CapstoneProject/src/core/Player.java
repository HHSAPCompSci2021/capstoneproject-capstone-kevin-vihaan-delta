package core;

import java.util.List;

import obstacles.Obstacle;
import processing.core.PImage;


/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *Class representing the Player and all the functions that it haves.
 */
public class Player extends Obstacle{

	public Player(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void jump() {
		
	}
	
public void move(int dir) {
		
		super.moveByAmount(dir, 0);
		
	}

public void act(List<Obstacle> obstacles) {
	
	
	for (Obstacle s : obstacles)
	{
		if (super.intersects(s))
		{
			
			y = s.y-super.height;
			
		}
	}
}
}
