package menus;

import java.awt.Rectangle;

import core.DrawingSurface;

/**
 * 
 * @author kevinvalenica and vihaanchinthakindi
 * Represents a menu of Levels from which the user can choose to play a level
 *
 */
public class LevelMenu extends Screen {

	private DrawingSurface surface;

	//private Rectangle screenRect;
	private Rectangle level1;
	private Rectangle level2;
/**
 * 
 * @param surface takes in a DrawingSurface to perform drawing with
 * Initializes all buttons
 */
	public LevelMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
	//	screenRect = new Rectangle(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		level2 = new Rectangle(300, 100, 100, 100);
		level1 = new Rectangle(100, 100, 100, 100);
		// TODO Auto-generated constructor stub
	}
/**
 * Draws the LevelMenu with  buttons indicating specific levels.
 */
	public void draw() {
		surface.background(0, 255, 255);
		surface.rect(level1.x, level1.y, level1.width, level1.height, 10, 10, 10, 10);

		surface.fill(0);
		String str = "Level 1";
		float w = surface.textWidth(str);
		surface.textSize(30);
		surface.text(str, level1.x + level1.width / 2 - w / 2, level1.y + level1.height / 2);
		surface.fill(255);
		surface.rect(level2.x, level2.y, level2.width, level2.height, 10, 10, 10, 10);
		surface.fill(0);
		String str1 = "Level 2";
		float w1 = surface.textWidth(str);
		surface.textSize(30);
		surface.text(str1, level2.x + level2.width / 2 - w1 / 2, level2.y + level2.height / 2);

	}
}
