package com.wordpress.posts;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Admin_01_Create_View_Edit_Delete extends AbstractTest {
	WebDriver driver;
	int fakeNumber = randomNumber();
	String newPostTitle = "NEW POST TITLE" + fakeNumber;
	String newPostContent = "NEW POST CONTENT" + fakeNumber;
	String newPostTag = "new_post" + fakeNumber;
	String newPostCategory = "NEW LIVE CODING";
	String featureImageName = "BullSkull.png";
	String authorName = "Automation FC";

	String editPostTitle = "EDIT POST TITLE" + fakeNumber;
	String editPostTag = "edit_post" + fakeNumber;
	String editPostCategory = "EDIT LIVE CODING";

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.ADMIN_WORDPRESS_URL);

		log.info("Pre-condition - STEP 01: Open Login Page");
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);

		log.info("Pre-condition - STEP 02: Input to 'Email' textbox");
		loginAdminPage.inputToEmailTextbox(GlobalConstants.USER_NAME);

		log.info("Pre-condition - STEP 03: Click to 'Continue' button");
		loginAdminPage.clickToContinueOrLoginButton();

		log.info("Pre-condition - STEP 04: Input to 'Password' textbox");
		loginAdminPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		log.info("Pre-condition - STEP 05: Click to 'Login' button");
		dashboardAdminPage = loginAdminPage.clickToContinueOrLoginButton();

		log.info("Pre-condition - STEP 06: Verify 'Header' text is displayed");
		verifyTrue(dashboardAdminPage.isHeaderTextDisplayed());
	}

	@Test
	public void Post_01_Create_New_Post_At_Admin_Page() {
		dashboardAdminPage.clickToPostsMenu(driver);
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		newEditPostAdminPage = postsAdminPage.clickToAddNewButton();

		newEditPostAdminPage.inputToPostTitleTextbox(newPostTitle);

		newEditPostAdminPage.clickToPostContentTextbox();

		newEditPostAdminPage.inputToPostContentTextbox(newPostContent);

		newEditPostAdminPage.clickToMenuTabs("Post");
		
		newEditPostAdminPage.clickToMenuTabs("Categories");

		newEditPostAdminPage.selectCategoryCheckbox(newPostCategory);
		
		newEditPostAdminPage.clickToMenuTabs("Tags");

		newEditPostAdminPage.inputToTagTextbox(newPostTag);

		newEditPostAdminPage.clickToSetFeatureImageLink("Featured image");

		newEditPostAdminPage.clickToFeaturedButton("Set featured image");

		newEditPostAdminPage.clickToMediaLibrary();

		newEditPostAdminPage.UploadMultipleFiles(driver, featureImageName);

		verifyTrue(newEditPostAdminPage.areFileUploadedDisplayed(driver, featureImageName));

		newEditPostAdminPage.clickToInserImageButton();

		verifyTrue(newEditPostAdminPage.isFeaturedImageDisplayed(featureImageName));

		newEditPostAdminPage.clickToPublishButton("Publish");

		newEditPostAdminPage.clickToPublishButton();

		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue("Post published."));

		// Search_Post_At_Admin_Page
		newEditPostAdminPage.openUrl(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
		newEditPostAdminPage.clickToPostsMenu(driver);
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(newPostTitle);

		verifyTrue(postsAdminPage.isPostDisplayedOnSearchPost(driver, authorName, newPostTitle));

		// Navigate to User Site
		homeUserPage = postsAdminPage.openHomeUserPage(driver);

		verifyTrue(
				homeUserPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle, getWordPressToday()));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImageName));

		// Go_Post_Detail_At_User_Page
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver, newPostTitle);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(driver, newPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(driver, newPostTitle));
		verifyTrue(postDetailUserPage.isImageNameDisplayed(driver, featureImageName));
		verifyTrue(postDetailUserPage.isContentNameDisplayed(driver, newPostContent));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(driver, getWordPressToday()));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(driver, authorName));

		// Search_Post_At_User_Page
		searchResultUserPage = postDetailUserPage.inputToSearchTextboxUserPage(driver, newPostTitle);

		verifyTrue(searchResultUserPage.isPostTitleDisplayedOnHeader(driver, newPostTitle));

		verifyTrue(searchResultUserPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle,
				getWordPressToday()));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImageName));
	}

	@Test
	public void Post_02_Edit_Post_At_Admin_Page() {
		dashboardAdminPage = searchResultUserPage.openAdminPage(driver);

		dashboardAdminPage.clickToPostsMenu(driver);
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		// Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox(newPostTitle);

		verifyTrue(postsAdminPage.isPostDisplayedOnSearchPost(driver, authorName, newPostTitle));

		// click to Post Detail
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitleName(newPostTitle);

		// Edit Post
		newEditPostAdminPage.clearPostTitleTextbox();
		
		newEditPostAdminPage.inputToPostTitleTextbox(editPostTitle);

		newEditPostAdminPage.deselectCategoryCheckbox(newPostCategory);

		newEditPostAdminPage.selectCategoryCheckbox(editPostCategory);

		newEditPostAdminPage.clickToDeleteTagIconWithTagName();
		
		newEditPostAdminPage.inputToTagTextbox(editPostTag);

		newEditPostAdminPage.clickToUpdateButton("Update");

		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue("Post updated."));

		// Search_Post_At_Admin_Page
		newEditPostAdminPage.openUrl(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
		newEditPostAdminPage.clickToPostsMenu(driver);
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(editPostTitle);

		verifyTrue(postsAdminPage.isPostDisplayedOnSearchPost(driver, authorName, editPostTitle));

		// Navigate to User Site
		homeUserPage = postsAdminPage.openHomeUserPage(driver);

		verifyTrue(
				homeUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, getWordPressToday()));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, featureImageName));

		// Go_Post_Detail_At_User_Page
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver, editPostTitle);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(driver, editPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(driver, editPostTitle));
		verifyTrue(postDetailUserPage.isImageNameDisplayed(driver, featureImageName));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(driver, getWordPressToday()));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(driver, authorName));

		// Search_Post_At_User_Page
		searchResultUserPage = postDetailUserPage.inputToSearchTextboxUserPage(driver, editPostTitle);

		verifyTrue(searchResultUserPage.isPostTitleDisplayedOnHeader(driver, editPostTitle));
		verifyTrue(searchResultUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, getWordPressToday()));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, featureImageName));	
	}

	@Test
	public void Post_03_Delete_Post_At_Admin_Page() {
		// Search_Post_At_Admin_Page
		newEditPostAdminPage.openUrl(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
		newEditPostAdminPage.clickToPostsMenu(driver);
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(editPostTitle);
		verifyTrue(postsAdminPage.isPostDisplayedOnSearchPost(driver, authorName, editPostTitle));

		// click to Post Detail
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitleName(editPostTitle);
		newEditPostAdminPage = PageGeneratorManager.getNewOrEditPostAdminPage(driver);

		newEditPostAdminPage.clickToMoveToTrashButton("Move to trash");

		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		verifyTrue(postsAdminPage.isSuccessMessageDisplayed("Post successfully moved to trash."));

		// Search_Post_At_Admin_Page
		postsAdminPage.clickToTrashTab();
		postsAdminPage.inputToSearchTextbox(editPostTitle);
		
		postsAdminPage.clickToToggleMenu();
		postsAdminPage.clickToDeleteOption();
		
		verifyTrue(postsAdminPage.isNoPostFoundMessageDisplayed("No posts found."));

		// Navigate to User Site
		homeUserPage = postsAdminPage.openHomeUserPage(driver);

		// Search_Post_At_User_Page
		searchResultUserPage = postDetailUserPage.inputToSearchTextboxUserPage(driver, editPostTitle);
		verifyTrue(searchResultUserPage.isNoPostFoundMessageDisplayed("It seems we can’t find what you’re looking for. Perhaps searching can help."));
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition - STEP 01: Close browser");
		closeBrowserAndDriver(driver);
	}

	LoginPageObject loginAdminPage;
	DashboardPageObject dashboardAdminPage;
	PostsPageObject postsAdminPage;
	NewEditPostsPageObject newEditPostAdminPage;
	HomePageObject homeUserPage;
	PostDetailPageObject postDetailUserPage;
	SearchResultPageObject searchResultUserPage;

}
