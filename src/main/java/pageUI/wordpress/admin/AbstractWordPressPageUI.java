package pageUI.wordpress.admin;

public class AbstractWordPressPageUI {
	public static final String PAGES_LINK = "//span[@class='sidebar__expandable-title' and text()='Pages']";
	public static final String MEDIA_LINK = "//span[@class='sidebar__menu-icon dashicons-before dashicons-admin-media']";
	public static final String MEDIA_LINK1 = "//div[@class='wp-menu-name' and text()='Media']";
	public static final String POSTS_LINK = "//div[@class='wp-menu-name' and text()='Posts']";
	public static final String POST_LINK = "//span[@class='sidebar__expandable-title' and text()='Posts']";
	
	public static final String DYNAMC_PAGE_LINK = "//span[@class='sidebar__expandable-title' and text()='%s']";
	public static final String UPLOAD_FILE_TYPE = "//span//input[@type='file']";
	public static final String MEDIA_LOADING_ICON = "//div[@class='media-library__list-item-spinner']//div[@class='spinner']//div[@class='spinner__outer']//div[@class='spinner__inner']";
	public static final String ALL_UPLOADED_IMAGE = "//figure[@class='media-library__list-item-figure']//img";
	public static final String DYNAMIC_MESSAGE_ON_POST = "//div[@class='components-snackbar__content' and contains(text(),'%s')]";
	public static final String DYNAMIC_POST_WITH_AUTHOR_TITLE = "//div[@class='post-type-post-author__name' and text()='%s']//ancestor::div[@class='post-item__info']//following-sibling::h1[@class='post-item__title']//a[text()='%s']";
	public static final String DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE = "//p[@class='post-categories']//a[text()='%s']//parent::p//following-sibling::h2[@class='post-title']//a[text()='%s']//parent::h2//following-sibling::p[@class='post-meta']//a[text()='%s']";
	public static final String DYNAMIC_POST_IMAGE_BY_TITLE = "//a[@title='%s']//img[contains(@src,'%s')]";
	public static final String DYNAMIC_POST_TITLE = "//h2[@class='post-title']//a[text()='%s']";
	public static final String SEARCH_ICON = "//a[@class='search-toggle']";
	public static final String SEARCH_TEXTBOX = "//input[@class='search-field']";
	public static final String SEARCH_BUTTON = "//span[@class='fa fw fa-search']";
}
