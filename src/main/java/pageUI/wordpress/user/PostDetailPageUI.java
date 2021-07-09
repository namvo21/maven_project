package pageUI.wordpress.user;

public class PostDetailPageUI {
	public static final String POST_CATEGORY = "//p[@class='post-categories']//a[text()='%s']";
	public static final String POST_TITLE = "//h1[@class='post-title' and text()='%s']";
	public static final String POST_IMAGE = "//figure[@class='post-image clear-fix']//img[contains(@src,'%s')]";
	public static final String POST_CONTENT = "//div[@class='post-content']//p[contains(text(),'%s')]";
	public static final String POST_DATE_CREATED = "//span[@class='post-meta-date']//a[text()='%s']";
	public static final String POST_AUTHOR = "//span[@class='post-meta-author']//a[text()='%s']";
}
