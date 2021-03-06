package core;

import java.util.List;

import obstacles.Coin;
import obstacles.Door;
import obstacles.Obstacle;
import obstacles.PowerCoin;
import obstacles.Saw;
import obstacles.SpeedBoost;
import obstacles.Spike;
import obstacles.Wall;
import processing.core.PImage;


/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 *Class representing the Player and all the functions that it haves.
 */
public class Player extends Obstacle{
	
	
	public static double jumpHeight;
	public static double speedMultiplier;
	private double xVel, yVel, gravity;

	/**
	 * variables to determine if player canJump or is onsurface
	 */
	public boolean onSurface, canJump;
	/**
	 * 
	 * @param img Image of Player
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param w width
	 * @param h height
	 */
	public Player(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		 yVel = 0;
		 xVel = 0;
		 onSurface = false;
		 canJump = false;
		 jumpHeight = 3;
		 gravity = 0.03;
		 speedMultiplier = 1;
	
	
	}
	/**
	 * makes the player jump
	 */
	public void jump() {
		if (onSurface && canJump) {
			Main.playSoundEffect(Main.jumpNumber);
			yVel =-jumpHeight;
		}
		//super.moveByAmount(0, -50);
	}
	
	/**
	 * moves the player by Direction amount
	 * @param dir amount to move by
	 */
public void move(int dir) {
	//	if (onSurface)
		super.moveByAmount(dir*2*speedMultiplier, 0);
		
	}
/**
 * makes the player act
 * @param obstacles obstacles in the Level that user plays on
 */

public void act(List<Obstacle> obstacles) {
	onSurface = false;
	yVel +=0.1;
	
	x += xVel;
	y +=yVel;
	
	for (Obstacle s : obstacles)
	{
		if (super.intersects(s) && ! ( (s instanceof Spike) || s instanceof Saw || s instanceof Coin || s instanceof Player || s instanceof Door || s instanceof PowerCoin || s instanceof SpeedBoost))
		{
			
			yVel = 0;
			if ( y < s.y)
			{
				canJump = true;
				y = s.y-super.height;
			}
			
			if ( x < s.x && y > s.y)
			{
				
				canJump = false;
				x = s.x-super.width;
			
			}
			
			if (x > s.x && y >s.y)
			{
				canJump = false;
				x = s.x+super.width+25;
			}
			if (x < s.x && y > s.y)
			{
				canJump = false;
				x = s.x-super.width;
			}
			
			if (x > s.x && y > s.y)
			{
				canJump = false;
				x = s.x+s.width ;
			}
			if ( (x > s.x && x < s.x +s.width) && (y > s.y && y < s.y+s.height))
			{
				
			y=100000;
				
			}
//			else if (x < s.x)
//			{
//				canJump = false;
//				x = s.x-super.width+50;
//				System.out.println(true);
//			}
			
			
//			{
//				canJump = false;
//				x = s.x+super.width+25;
//			}
			onSurface = true;
		}
	}
}
/**
 * Method meant to stop collisions into walls
 */
public void bounce() {
	yVel = -yVel;
	xVel = -xVel;
}
}
