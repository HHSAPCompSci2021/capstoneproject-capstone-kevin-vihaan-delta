package menus;

/**
 * 
 * @author vihaanchinthakindi and kevinvalencia
 * The interface in which is used to change screens
 * 
 */
public interface ScreenSwitcher {
	/**
	 * MENU_SCREEN has associated integer value of 0 for access.
	 */
	public static final int MENU_SCREEN = 0;
	/**
	 * GAME_SCREEN has associated integer value of 1 for access.
	 */
	public static final int LEVEL_ONE = 1;
	/**
	 * SHOP_SCREEN has associated integer value of 2 for access.
	 */
	public static final int SHOP_SCREEN = 2;
	/**
	 * LEVELCHOOSER_SCREEN has associated integer value of 3 for access.
	 */
	public static final int LEVELCHOOSER_SCREEN = 3;
	/**
	 * LEVEL_TWO has screen int of 4
	 */
	
	public static final int LEVEL_TWO = 4;

	
	public static final int LEVEL_THREE = 5;
	
	public static final int LEVEL_FOUR = 6;
	
	public static final int LEVEL_FIVE = 7;
	/**
	 * 
	 * @param i index of which screen to switch
	 * Abstract method that serves as role of switching the screen
	 */
	public void switchScreen(int i);
	
	
}
