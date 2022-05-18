
package menus;

import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import core.Player;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Represents the menu where the
 *         user will purchase powerups in the game.
 */
public class ShopMenu extends Screen {
	private Rectangle powerup1;
	private DrawingSurface surface;
	/**
	 * coins collected by the player, always same throughout program
	 */
	public static int coinsCollected;
	private boolean bought;
	

	/**
	 * Initializes the menu to be drawn
	 * 
	 * @param surface DrawingSurface object to perform drawing on
	 */
	public ShopMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
		powerup1 = new Rectangle(50, 350, 200, 350);
		coinsCollected = 25;
		bought = false;
	}

	/**
	 * Draws the mainMenu
	 */
	public void draw() {
		surface.background(255, 255, 255);
		surface.fill(0);
		surface.text("Coins: " + ShopMenu.coinsCollected,-380 , -370);
		surface.fill(0);
		surface.text("Item will display green if purchase successful.",-380 , -310);
		surface.text("Can only Buy Once.",-380 , -340);
		if (bought) {
			surface.fill(0, 128, 0);
		}
		else {
			surface.fill(128);
		}
		surface.rect(-350, -50, powerup1.width, powerup1.height, 10, 10, 10, 10);
		surface.fill(0);
		String str2 = "Jump Boost";
		float x = surface.textWidth(str2);
		surface.text(str2, -350 + powerup1.width / 2 - x / 2, -50 + powerup1.height / 2);
	}

	/**
	 * buys JumpBoost item for player
	 */
	public void buyJumpBoost() {
		if (coinsCollected >= 3 && bought == false) {
			Player.jumpHeight += 1;
			coinsCollected-=3;
			bought = true;
			
		}

	}

	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
		if (powerup1.contains(p))
			
			buyJumpBoost();

	}

}
