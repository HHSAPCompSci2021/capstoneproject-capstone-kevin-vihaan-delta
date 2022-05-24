package obstacles;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author kevinvalencia and vihaanchinthakindi
 * Represents a trap which is an obstacle that can kill the player and reset the level.
 *
 */
public class Obstacle extends Rectangle2D.Double { 
	public PImage image;
	
	
	public Obstacle(double x, double y, double w, double h) {
		
		this(null, x, y, w, h);
		
	}
	
	public Obstacle(PImage img, double x, double y, double w, double h) {
		super(x,y,w,h);
		image = img;
		
	}

	// METHODS	
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	
	public void draw(PApplet g) {
		//g.rectMode(3);
	//	x = x+width/2;
		//y = y + height/2;
		
		if (image != null)
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		else {
			g.fill(100);
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
	}
	
	
	
	
}
