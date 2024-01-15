package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static constants.Urls.RECOVERY_PASSWORD_URL;

public class PasswordRecoverPage {
    private static final By enterButtonOnRecoverPage = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;

    public PasswordRecoverPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open recovery page")
    public PasswordRecoverPage openRecoveryPage() {
        driver.get(RECOVERY_PASSWORD_URL);
        return this;
    }

    @Step("Click on \"Entrance\" button from recovery page")
    public LoginPage clickEnterButtonOnRecoveryPage() {
        driver.findElement(enterButtonOnRecoverPage).click();
        return new LoginPage(driver);
    }
}