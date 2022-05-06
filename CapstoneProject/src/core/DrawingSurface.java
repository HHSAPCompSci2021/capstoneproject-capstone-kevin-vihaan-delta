package core;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import processing.core.PApplet;

import menus.MainMenu;
import menus.LevelMenu;
import menus.Screen;
import menus.ScreenSwitcher;
//add shop menu
/**
 * 
 * @author vihaanchinthakindi & kevinvalencia
 * DrawingSurface classes manages what goes onto the window.
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher{
	private Screen activeScreen;
	/**
	 * ratioX & ratioY determine the ratios of the screen to be maintained.
	 */
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;

	/**
	 * constructor initializes fields
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
		
		MainMenu screen1 = new MainMenu(this);
		screens.add(screen1);
		
		LevelMenu screen2 = new LevelMenu(this);
		screens.add(screen2);
		//add shop menu
		activeScreen = screens.get(0);
	}
	/**
	 * sets up each  screen
	 */
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	/**
	 * draws the window with the screen that is currently active
	 */
	public void draw() { 
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
		
	}
	/**
	 * performs the action if the mouse is pressed
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	/**
	 * 
	 * @param assumed Point with assumed x,y coordinates
	 * @return the real Point with accordance to the width and height of the window
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}
/**
 * 
 * @param actual Actual Point with coordinates
 * @return returns assumed point
 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
}
