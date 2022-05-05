package menus;
import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;



public class MainMenu extends Screen {
	
	private DrawingSurface surface;
	private Rectangle chooseLevelButton;
	private Rectangle shopButton;
	private Rectangle quitButton;

	public MainMenu(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface;
		shopButton = new Rectangle(800/2-100,800/2-50,200,100);
		quitButton = new Rectangle(800/2-100,800/2+100,200,100);
		chooseLevelButton = new Rectangle(800/2-100,800/2-200,200,100);
		// TODO Auto-generated constructor stub
	}
	public void draw() {

		surface.background(255,255,255);
		
		surface.rect(shopButton.x, shopButton.y, shopButton.width, shopButton.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Shop";
		float w = surface.textWidth(str);	
		surface.textSize(30);
		surface.text(str, shopButton.x+shopButton.width/2-w/2, shopButton.y+shopButton.height/2);
		
		
		surface.fill(255);
		surface.rect(quitButton.x, quitButton.y, quitButton.width, quitButton.height,10,10,10,10);
		surface.fill(0);
		String str2 = "Quit";
		float x = surface.textWidth(str2);
		surface.text(str2, quitButton.x+quitButton.width/2-w/2, quitButton.y+quitButton.height/2);
		
		surface.fill(255);
		surface.rect(chooseLevelButton.x, chooseLevelButton.y, chooseLevelButton.width, chooseLevelButton.height,10,10,10,10);
		surface.fill(0);
		String str3 = "Choose \nLevel";
		float y = surface.textWidth(str3);
		surface.text(str3, chooseLevelButton.x+chooseLevelButton.width/2-2*w/3, chooseLevelButton.y+chooseLevelButton.height/2);
		
		
	}
}
