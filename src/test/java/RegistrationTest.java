import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import data.Authorization;
import data.User;
import data.GeneratorForUsers;
import pom.LoginPage;
import pom.MainPage;
import pom.RegistrationPage;
import data.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;

public class RegistrationTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String accessToken;
    private Authorization authorization;

    @Before
    @Step("Precondition step for test")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = GeneratorForUsers.getNewRandomUser();
    }

    @Test
    @DisplayName("Registration via the registration page")
    @Description("Check that the main page is displayed")
    public void successfullyRegistrationOnRegisterPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isManePageOpen());
    }

    @Test
    @DisplayName("Registration with the login page")
    @Description("Check that the main page is displayed with Checkout")
    public void successfullyRegistrationOnLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .clickRegisterButtonLoginPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isManePageOpen());
    }

    @Test
    @DisplayName("Registration with the main page")
    @Description("Check that the main page is displayed with Checkout")
    public void successfullyRegistrationOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .clickRegisterButtonLoginPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue(mainPage.isManePageOpen());
    }

    @After
    @Step("Delete test user and close browser")
    public void tearDown() {
        userClient = new UserClient();
        authorization = new Authorization(user.getEmail(), user.getPassword());
        ValidatableResponse responseLoginUser = userClient.loginUser(authorization);
        accessToken = responseLoginUser.extract().path("accessToken").toString().substring(7);
        userClient.deleteUser(accessToken);
        driver.quit();
    }
}