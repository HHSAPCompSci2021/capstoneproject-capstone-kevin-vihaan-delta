package obstacles;

import processing.core.PImage;

/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 * Coin class represents a coin in the Revolve Game that can be used to buy buffs from the Shop
 */
public class Coin extends Obstacle { //extends shape

	
	public static boolean doubleValue;
	
	public Coin(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		doubleValue = false;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
