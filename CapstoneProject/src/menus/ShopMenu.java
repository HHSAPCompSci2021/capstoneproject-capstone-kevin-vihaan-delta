
package menus;

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
	public static int coinsCollected;
	
	

	public ShopMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
		powerup1 = new Rectangle(800/2-350,800/2-50,200,350);
		coinsCollected = 0;
	}

	public void draw() {
		surface.background(255,255,255);
		
		
		surface.fill(255);
		surface.rect(powerup1.x, powerup1.y, powerup1.width, powerup1.height,10,10,10,10);
		surface.fill(0);
		String str2 = "Jump Boost";
		float x = surface.textWidth(str2);
		surface.text(str2, powerup1.x+powerup1.width/2-x/2, powerup1.y+powerup1.height/2);
	}
	
	public void buyJumpBoost()
	{
		if (coinsCollected == 3)
		{
			Player.jumpHeight += 3;
		}
		
		
	}
	
	
}
