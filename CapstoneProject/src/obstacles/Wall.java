package obstacles;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 * Class Represents the Walls in each level that player cannot pass
 */
public class Wall extends Obstacle{
public Wall(double x, double y, double w, double h) {
	super(x,y,w,h);
}
}
