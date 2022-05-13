package core;

import java.util.List;

import obstacles.Obstacle;
import obstacles.Spike;
import processing.core.PImage;


/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *Class representing the Player and all the functions that it haves.
 */
public class Player extends Obstacle{

	private double xVel, yVel;
	public boolean onSurface, canJump;
	public Player(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		 yVel = 0;
		 xVel = 0;
		 onSurface = false;
		 canJump = false;
	
	}
	
	public void jump() {
		if (onSurface && canJump) {
			yVel =-3;
		}
		//super.moveByAmount(0, -50);
	}
	
public void move(int dir) {
		
		super.moveByAmount(dir*2, 0);
		
	}

public void act(List<Obstacle> obstacles) {
	onSurface = false;
	yVel +=0.1;
	
	x += xVel;
	y +=yVel;
	
	for (Obstacle s : obstacles)
	{
		if (super.intersects(s) &&! (s instanceof Spike))
		{
			
			yVel = 0;
			if ( y < s.y)
			{
				canJump = true;
				y = s.y-super.height;
			} else 
			{
				canJump = false;
				x = s.x+super.width+25;
			}
			
			
			
			onSurface = true;
		}
	}
}

public void bounce() {
	xVel = -xVel;
	yVel = -yVel;
}
}
