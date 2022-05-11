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

	private double xVel, yVel;
	public Player(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		 yVel = 0;
		 xVel = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void jump() {
		super.moveByAmount(0, -50);
	}
	
public void move(int dir) {
		
		super.moveByAmount(dir*2, 0);
		
	}

public void act(List<Obstacle> obstacles) {
	yVel +=0.1;
	
	x += xVel;
	y +=yVel;
	
	for (Obstacle s : obstacles)
	{
		if (super.intersects(s))
		{
			yVel = 0;
			y = s.y-super.height;
			
		}
	}
}
}
