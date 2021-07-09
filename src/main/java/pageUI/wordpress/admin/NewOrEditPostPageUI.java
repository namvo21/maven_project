package pageUI.wordpress.admin;

public class NewOrEditPostPageUI {
	public static final String TINY_MCE_IFRAME = "//iframe[@class='is-loaded']";
	public static final String TITLE_TEXTBOX = "//textarea[@id='post-title-0']";
	public static final String TINY_MCE_EDITOR = "//textarea[@class='block-editor-default-block-appender__content']";
	public static final String TINY_MCE_TEXTBOX = "//p[@data-title='Paragraph']";
	public static final String BUTTON_LINK = "//button[@type='button' and text()='%s']";
	public static final String PUBLISH_BUTTON = "//div[@class='editor-post-publish-panel__header-publish-button']//button[contains(text(),'Publish')]";
	public static final String CATEGORY_CHECKBOX = "//label[contains(text(),'%s')]//preceding-sibling::span//input";
	public static final String TAG_TEXTBOX = "//input[@id='components-form-token-input-0']";
	public static final String REMOVE_TAG_BUTTON = "//button[@type='button' and @aria-label='Remove Tag']";
	public static final String INSERT_BUTTON = "//span[text()='Insert']";
	public static final String FEATURED_IMAGE_THUMBNAIL = "//img[contains(@src,'%s')]";
	public static final String LOADING_ICON = "//span[@class='components-spinner css-pz2hpy-StyledSpinner e1s472tg0']";
	public static final String UPLOAD_FILE_TYPE = "//span//input[@type='file']";
	public static final String MEDIA_BUTTON = "//span[text()='Media Library']//parent::button";
}
