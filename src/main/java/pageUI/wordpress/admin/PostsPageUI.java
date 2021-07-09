package pageUI.wordpress.admin;

public class PostsPageUI {
	public static final String ADD_NEW_BUTTON = "//a[text()='Add new post']";
	public static final String SEARCH__POST_ICON = "//div[@role='search']";
	public static final String SEARCH_POST_TEXTBOX = "//input[@type='search']";
	public static final String POST_TITLE = "//h1[@class='post-item__title']//a[text()='%s']";
	public static final String MOVE_TO_TRASH_MESSAGE = "//span[@class='notice__text' and text()='%s']";
	public static final String NOT_FOUND_MESSAGE = "//h2[@class='empty-content__title' and text()='%s']";
	public static final String TRASH_LINK = "//span[@class='section-nav-tab__text' and text()='Trashed']";
	public static final String TOOGLE_MENU = "//button[@title='Toggle menu']";
	public static final String DELETE_OPTION = "//button[@role='menuitem' and text()='Delete Permanently']";
}
