package menus;

import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.PImage;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Represents a menu of Levels from
 *         which the user can choose to play a level
 *
 */
public class LevelMenu extends Screen {

	// private DrawingSurface surface;

	// private Rectangle screenRect;
	private Rectangle level1;
	private Rectangle level2;
	private Rectangle level3;
	private Rectangle level4;
	private PImage image;

	/**
	 * 
	 * @param surface takes in a DrawingSurface to perform drawing with Initializes
	 *                all buttons
	 */
	public LevelMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
		// screenRect = new Rectangle(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		level2 = new Rectangle(300, 100, 100, 100);
		level1 = new Rectangle(100, 100, 100, 100);
		level3 = new Rectangle(500, 100, 100, 100);
		level4 = new Rectangle(100,300,100,100);
		// TODO Auto-generated constructor stub
	}
	public void setup()
	{
		image = surface.loadImage("img/levelmenu.png");
	}
	/**
	 * Draws the LevelMenu with buttons indicating specific levels.
	 */
	public void draw() {
		
		surface.image(image, -400, -400, 800, 800);
		
		super.draw();
		
	//	surface.background(0, 255, 255);
		surface.rect(-100, -300, level2.width, level2.height, 10, 10, 10, 10);
		super.draw();
		surface.fill(0);
		String str = "Level 2";
		float w = surface.textWidth(str);
		surface.textSize(30);
		surface.text(str, -100 + level2.width / 2 - w / 2, -300 + level2.height / 2);
		
		surface.fill(255);
		surface.rect(-300, -300, level1.width, level1.height, 10, 10, 10, 10);
		surface.fill(0);
		String str1 = "Level 1";
		float w1 = surface.textWidth(str);
		surface.textSize(30);
		surface.text(str1, -300 + level1.width / 2 - w1 / 2, -300 + level1.height / 2);

		
		
		surface.fill(255);
		surface.rect(100, -300, level3.width, level3.height, 10, 10, 10, 10);
	surface.fill(0);
		String str3 = "Level 3";
		float w2 = surface.textWidth(str3);
		surface.textSize(30);
		surface.text(str3, 100 + level3.width / 2 - w2 / 2, -300 + level3.height / 2);
		
		
		surface.fill(255);
		surface.rect(-300, -100, level4.width, level4.height, 10, 10, 10, 10);
		surface.fill(0);
		String str4 = "Level 4";
		float w3 = surface.textWidth(str4);
	
		surface.textSize(30);
		surface.text(str4, -300 + level4.width / 2 - w3 / 2, -100 + level4.height / 2);
		
		
		
		
	}

	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
		if (level1.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_ONE);
		if (level2.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_TWO);
		if (level3.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_THREE);
		if (level4.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_FOUR);

	}
}
