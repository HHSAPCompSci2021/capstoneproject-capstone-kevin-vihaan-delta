package menus;

import java.util.ArrayList;

import core.DrawingSurface;
import obstacles.Obstacle;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi
 * Abstract class that represents any screen a user will see.
 *
 */
public abstract class Screen {
	
	/**
	 * DRAWING_WIDTH and DRAWING_HEIGHT descrie the dimensions of the Screen
	 */
	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	public DrawingSurface surface;
	public ArrayList<Obstacle> obstacles;
	
	/**
	 * 
	 * @param width width of Screen
	 * @param height width of Screen
	 * Initializes the dimensions of the Screen
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	/**
	 * Setup the screen
	 */
	public void setup() {
		
	}
	/**
	 *  Draw the Screen
	 */
	public void draw() {
		surface.fill(255);
		surface.text("Coins: " + ShopMenu.coinsCollected,-380 , -370);
		
	}
	
	/**
	 * Act when the mouse is pressed
	 */
	public void mousePressed() {
		
	}
	public void rotate(double d) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @param x initial x-point
	 * @param y initial y-point
	 * @param angle angle to rotated about
	 * @return new x-value of point
	 */
	public double rotateXPoint(double x, double y, double angle) {
		return x*Math.cos(angle)-y*Math.sin(angle);
	}
	/**
	 * 
	 * @param x initial x-point
	 * @param y initial y-point
	 * @param angle angle to rotated about
	 * @return new y-value of point
	 */
	public double rotateYPoint(double x,double y, double angle) {
		return y*Math.cos(angle)+x*Math.sin(angle);
	}
	

	
	
	
}
