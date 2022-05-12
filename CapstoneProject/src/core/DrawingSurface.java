package core;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import levels.LevelOne;
import processing.core.PApplet;
import core.Main;
import menus.MainMenu;
import menus.LevelMenu;
import menus.Screen;
import menus.ScreenSwitcher;

//add shop menu
/**
 * 
 * @author vihaanchinthakindi and kevinvalencia DrawingSurface classes manages
 *         what goes onto the window.
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	private ArrayList<Integer> keys;
	private Screen activeScreen;
	/**
	 * ratioX and ratioY determine the ratios of the screen to be maintained.
	 */
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;

	/**
	 * constructor initializes fields
	 * 
	 */
	public DrawingSurface() {
		screens = new ArrayList<Screen>();

		keys = new ArrayList<Integer>();

		MainMenu screen1 = new MainMenu(this);
		screens.add(screen1);

		LevelOne screen2 = new LevelOne(this);
		screens.add(screen2);

		LevelMenu screen3 = new LevelMenu(this);
		screens.add(screen3);

		LevelMenu screen4 = new LevelMenu(this);
		screens.add(screen4);
		// add shop menu
		activeScreen = screens.get(0);
	}

	/**
	 * Sets up each screen
	 */
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}

	/**
	 * Draws the window with the screen that is currently active
	 */
	public void draw() {
		ratioX = (float) width / activeScreen.DRAWING_WIDTH;
		ratioY = (float) height / activeScreen.DRAWING_HEIGHT;

		push();

		scale(ratioX, ratioY);

		activeScreen.draw();

		pop();

	}

	/**
	 * Performs the action if the mouse is pressed
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
		return new Point((int) (assumed.getX() * ratioX), (int) (assumed.getY() * ratioY));
	}

	/**
	 * 
	 * @param actual Actual Point with coordinates
	 * @return returns assumed point
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int) (actual.getX() / ratioX), (int) (actual.getY() / ratioY));
	}

	public void keyPressed() {
		keys.add(keyCode);
		if (key == KeyEvent.VK_Q) // This prevents a processing program from closing on escape key
			switchScreen(0);
	}

	public void keyReleased() {
		while (keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	@Override
	/**
	 * 
	 * @param i index of which screen to switch Switches active screen to screen at
	 *          index i
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
		if (i == 1) {
			try {
				Main.playMusic(false, "Trash");
				Main.playMusic(true, "/Music/synthwave2.wav");
			} catch (NullPointerException e) {

			}

		}

		if (i == 0) {
			try {

				Main.playMusic(false, "Trash");
				Main.playMusic(true, "/Music/synthwave.wav");
			} catch (NullPointerException e) {

			}

		}
	}
}
