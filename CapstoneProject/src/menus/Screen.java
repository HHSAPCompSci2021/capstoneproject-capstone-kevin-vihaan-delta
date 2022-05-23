package menus;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import core.DrawingSurface;
import obstacles.Obstacle;
import obstacles.Wall;
import processing.core.PImage;
import obstacles.Spike;
import obstacles.*;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Abstract class that represents
 *         any screen a user will see.
 *
 */
public abstract class Screen {

	/**
	 * DRAWING_WIDTH and DRAWING_HEIGHT descrie the dimensions of the Screen
	 */
	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	public DrawingSurface surface;
	public ArrayList<Obstacle> obstacles;
	public PImage doorDown, doorLeft ,doorRight, doorUp;

	/**
	 * 
	 * @param width  width of Screen
	 * @param height width of Screen Initializes the dimensions of the Screen
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
	 * Draw the Screen
	 */
	public void draw() {
		surface.fill(255);
		surface.text("Coins: " + ShopMenu.coinsCollected, -380, -370);

	}

	/**
	 * Act when the mouse is pressed
	 */
	public void mousePressed() {

	}

	/**
	 * method to rotate obstacles
	 * 
	 * @param d angle to rotate by
	 */
	public void rotate(double d) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param x     initial x-point
	 * @param y     initial y-point
	 * @param angle angle to rotated about
	 * @return new x-value of point
	 */
	public double rotateXPoint(double x, double y, double angle) {
		return x * Math.cos(angle) - y * Math.sin(angle);
	}

	/**
	 * 
	 * @param x     initial x-point
	 * @param y     initial y-point
	 * @param angle angle to rotated about
	 * @return new y-value of point
	 */
	public double rotateYPoint(double x, double y, double angle) {
		return y * Math.cos(angle) + x * Math.sin(angle);
	}

	/**
	 * Method finds if doubles are equal factoring in runoff error
	 * 
	 * @param a first double
	 * @param b second double to be compared
	 * @return if the two doubles are equal
	 */
	public boolean equals(double a, double b) {
		double change = 0.0001d;
		if (Math.abs(a - b) < change)
			return true;
		return false;
	}

	/**
	 * Finds topLeft corner
	 * 
	 * @param hey   ArrayList of Points representing corners
	 * @param angle angle to be rotated by
	 * @return Point2D object representing top left corner
	 */
	public Point2D findTopLeftCorner(ArrayList<Point2D> hey, double angle) {

		ArrayList<Point2D> corners1 = new ArrayList<Point2D>();
		for (Point2D a : hey) {
			corners1.add(a);
		}
		for (int i = 0; i < corners1.size(); i++) {
			Point2D a = corners1.get(i);
			double temp = a.getX();
			double newX = rotateXPoint(temp, a.getY(), angle);
			double newY = rotateYPoint(temp, a.getY(), angle);
			a.setLocation(newX, newY);

		}

		double min = corners1.get(0).getX();
		for (int i = 1; i < corners1.size(); i++) {

			if (equals(corners1.get(i).getX(), min)) {
				corners1.set(i, new Point2D.Double(min, corners1.get(i).getY()));
			}

			if (corners1.get(i).getX() > min) {

				corners1.remove(i);
				i--;
			} else if (corners1.get(i).getX() < min) {

				min = corners1.get(i).getX();
				i = -1;
			}

		}

		double y1 = corners1.get(0).getY();
		double y2 = corners1.get(1).getY();
		if (y1 > y2) {
			return corners1.get(1);
		} else if (y2 > y1) {
			return corners1.get(0);
		}

		return null;

	}

	/**
	 * gets all corners of an obstacle
	 * 
	 * @param x      Top Left corner x-coordinate
	 * @param y      Top-Left corner y-coordinate
	 * @param width  width of Obstacle
	 * @param height Height of Obstacle
	 * @return ArrayList of Points representing all 4 corners.
	 */
	public ArrayList<Point2D> getAllCorners(double x, double y, double width, double height) {
		ArrayList<Point2D> corners = new ArrayList<Point2D>();
		corners.add(new Point2D.Double(x, y));
		corners.add(new Point2D.Double(x + width, y));
		corners.add(new Point2D.Double(x + width, y + height));
		corners.add(new Point2D.Double(x, y + height));
		return corners;
	}

	/**
	 * Rotates all obstacles in given arraylist
	 * 
	 * @param hey   arraylist of obstacles to be rotated
	 * @param angle angle to rotate by
	 * @return arraylist of rotated obstacles with correct coordinates
	 */
	public ArrayList<Obstacle> rotateAll(ArrayList<Obstacle> hey, double angle) {
		ArrayList<Obstacle> obstacle1 = new ArrayList<Obstacle>();
		for (Obstacle a : hey) {
			obstacle1.add(a);
		}
		for (int i = 0; i < obstacle1.size(); i++) {
			Obstacle a = obstacle1.get(i);
			ArrayList<Point2D> corners = getAllCorners(a.getX(), a.getY(), a.getWidth(), a.getHeight());

			Point2D topLeft = findTopLeftCorner(corners, angle);

			if (equals(angle, Math.PI / 2) || equals(angle, 3 * Math.PI / 2)) {

				if (a instanceof Wall) {
					obstacle1.set(i, new Wall(topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
				if (a instanceof Spike) {
					obstacle1.set(i, new Spike(a.image, topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
				if (a instanceof Door) {
					obstacle1.set(i, new Door(a.image, topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
				if (a instanceof PowerCoin) {
					obstacle1.set(i,
							new PowerCoin(a.image, topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
				if (a instanceof Saw) {
					obstacle1.set(i, new Saw(a.image, topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
				if (a instanceof SpeedBoost) {
					obstacle1.set(i,
							new SpeedBoost(a.image, topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
				if (a instanceof Coin) {
					obstacle1.set(i, new Coin(a.image, topLeft.getX(), topLeft.getY(), a.getHeight(), a.getWidth()));
				}
			}

			if (equals(angle, Math.PI) || equals(angle, 0)) {
				if (a instanceof Wall) {
					obstacle1.set(i, new Wall(topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
				if (a instanceof Spike) {
					obstacle1.set(i, new Spike(a.image, topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
				if (a instanceof Door) {
					
					obstacle1.set(i, new Door(a.image, topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
				if (a instanceof PowerCoin) {
					obstacle1.set(i,
							new PowerCoin(a.image, topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
				if (a instanceof Saw) {
					obstacle1.set(i, new Saw(a.image, topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
				if (a instanceof SpeedBoost) {
					obstacle1.set(i,
							new SpeedBoost(a.image, topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
				if (a instanceof Coin) {
					obstacle1.set(i, new Coin(a.image, topLeft.getX(), topLeft.getY(), a.getWidth(), a.getHeight()));
				}
			}
		}
		return obstacle1;
	}

}
