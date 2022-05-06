package menus;

/**
 * 
 * @author vihaanchinthakindi & kevinvalencia
 * The interface in which is used to change screens
 */
public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int GAME_SCREEN = 1;
	public static final int SHOP_SCREEN = 2;
	public static final int LEVELCHOOSER_SCREEN = 3;
	
	public void switchScreen(int i);
}
