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

public class DrawingSurface extends PApplet{
	private Screen activeScreen;
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;

	
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
		
		MainMenu screen1 = new MainMenu(this);
		screens.add(screen1);
		
		LevelMenu screen2 = new LevelMenu(this);
		screens.add(screen2);
		//add shop menu
		activeScreen = screens.get(0);
	}
	
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	public void draw() { 
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
		
	}
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
}
