package levels;

import core.DrawingSurface;
import menus.Screen;

public class LevelThree extends Screen {

	public LevelThree(DrawingSurface surface) {
		super(800, 800);

		this.surface = surface;

	}
	
	
	public void draw() {
		surface.background(211,211,211);
	}

}
