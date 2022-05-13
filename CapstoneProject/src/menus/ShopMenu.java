
package menus;

import core.DrawingSurface;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Represents the menu where the
 *         user will purchase powerups in the game.
 */
public class ShopMenu extends Screen {

	private DrawingSurface surface;

	public ShopMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
	}

	public void draw() {
		surface.background(255,255,255);
	}
}
