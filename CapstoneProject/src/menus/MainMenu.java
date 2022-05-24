package menus;

import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
import core.DrawingSurface;
import menus.ScreenSwitcher;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Represents the main screen from
 *         which the user will navigate to either play the game, go to the shop,
 *         or quit
 */
public class MainMenu extends Screen {

	// private DrawingSurface surface;
	private Rectangle chooseLevelButton;
	private Rectangle shopButton;
	private Rectangle quitButton;
	private PImage image;

	/**
	 * 
	 * @param surface takes in a DrawingSurface to perform drawing with Initializes
	 *                all buttons
	 */
	public MainMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;

		shopButton = new Rectangle(300, 350, 200, 100);
		quitButton = new Rectangle(300, 500, 200, 100);
		chooseLevelButton = new Rectangle(300, 200, 200, 100);

		// TODO Auto-generated constructor stub
	}

	public void setup() {

		image = surface.loadImage("img/placeholder.png");

	}

	/**
	 * Draws the MainMenu with buttons indicating the ShopMenu, LevelMenu, and quit
	 * button
	 */
	public void draw() {

		// surface.background(255,255,255);

		surface.image(image, -400, -400, 800, 800);

		// surface.background
		super.draw();
		surface.fill(255);

		surface.text("Press ESC to go back to Main Menu.", -380, -310);
		surface.text("Press 1 to Rotate 90 degrees Clockwise", -380, -340);
		surface.text("Press 2 to Rotate 90 degrees Counter-Clockwise ", -380, -280);
		surface.text("Sometimes it is not possible to rotate!", -380, -250);
		surface.rect(-100, -50, shopButton.width, shopButton.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Shop";
		float w = surface.textWidth(str);
		surface.textSize(30);
		surface.text(str, -100 + shopButton.width / 2 - w / 2, -50 + shopButton.height / 2);

		surface.fill(255);
		surface.rect(-100, 100, quitButton.width, quitButton.height, 10, 10, 10, 10);
		surface.fill(0);
		String str2 = "Quit";
		float x = surface.textWidth(str2);
		surface.text(str2, -100 + quitButton.width / 2 - x / 2, 100 + quitButton.height / 2);

		surface.fill(255);
		surface.rect(-100, -200, 200, 100, 10, 10, 10, 10);
		surface.fill(0);
		String str3 = "Choose \nLevel";
		float y = surface.textWidth(str3);
		surface.text(str3, -100 + 200 / 2 - y / 2, -200 + 100 / 2);

	}

	/**
	 * Determines if a button is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
		if (chooseLevelButton.contains(p)) {
			// System.out.println("HI");
			surface.switchScreen(ScreenSwitcher.LEVELCHOOSER_SCREEN);
		}
		if (shopButton.contains(p)) {
			surface.switchScreen(ScreenSwitcher.SHOP_SCREEN);

		}
		if (quitButton.contains(p)) {
			surface.exit();
		}
	}
	// add same if for other buttons
}
