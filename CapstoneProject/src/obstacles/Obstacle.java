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
	private PImage image;
	public Obstacle(int x, int y, int w, int h) {
		this(null, x, y, w, h);
	}
	
	public Obstacle(PImage img, int x, int y, int w, int h) {
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
		if (image != null)
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		else {
			g.fill(100);
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
	}
	
	
	
	
}
