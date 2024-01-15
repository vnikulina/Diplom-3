import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import java.time.Duration;
import static constants.Urls.LOGIN_PAGE_URL;

public class LogoutTest extends BaseTest {
    @Test
    @DisplayName("Checking the logout by Logout on the profile page")
    @Description("Check that the main page is displayed")
    public void logoutIsSuccessful() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickLogOutButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        Assert.assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}