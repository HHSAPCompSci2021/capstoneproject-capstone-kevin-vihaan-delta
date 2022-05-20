
package menus;

import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import core.Player;
import obstacles.Coin;
import processing.core.PImage;

/**
 * 
 * @author kevinvalencia and vihaanchinthakindi Represents the menu where the
 *         user will purchase powerups in the game.
 */
public class ShopMenu extends Screen {
	private Rectangle powerup1;
	private DrawingSurface surface;
	private PImage image;
	private Rectangle powerup2;
	/**
	 * coins collected by the player, always same throughout program
	 */
	public static int coinsCollected;
	private boolean bought;
	private boolean bought2;
	

	/**
	 * Initializes the menu to be drawn
	 * 
	 * @param surface DrawingSurface object to perform drawing on
	 */
	public ShopMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
		powerup1 = new Rectangle(50, 350, 200, 350);
		powerup2 = new Rectangle(300,350,200,350);
		coinsCollected = 25;
		bought = false;
		bought2 = false;
		
	}
	public void setup()
	{
		image = surface.loadImage("img/shopmenu.jpg");
	}
	/**
	 * Draws the mainMenu
	 */
	public void draw() {
		
		
		surface.image(image, -400, -400, 800, 800);
		
		//super.draw();
		
		
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
		
		if (bought2)
		{
			surface.fill(0,128,0);
		}
		else {
			surface.fill(128);
		}
		
		
		surface.rect(-50, -50, powerup2.width, powerup2.height,10,10,10,10);
		surface.fill(0);
		String str3 ="x2 Coins";
		float x2 = surface.textWidth(str3);
		surface.text(str3, -50+powerup2.width/2 - x/2, -50 + powerup2.height/2);
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
	public void buyCoinMultiplier()
	{
		if (coinsCollected >= 5 && bought2 == false)
		{
			Coin.doubleValue = true;
			coinsCollected-=5;
			bought2=true;
		
			
		}
	}

	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
		if (powerup1.contains(p))
		{
			buyJumpBoost();
		}
		
		if (powerup2.contains(p))
		{
			buyCoinMultiplier();
		}

	}

}
