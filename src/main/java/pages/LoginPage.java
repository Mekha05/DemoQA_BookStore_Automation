
package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.StaleElementReferenceException;

public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Login page elements
    By usernameField = By.id("userName");
    By passwordField = By.id("password");
    By loginButton = By.id("login");

    // Open Login page
    public void openLoginPage() {
        driver.get("https://demoqa.com/login");
    }

    // Enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click Login button
    public void clickLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }
    // Get login error message
    public String getLoginErrorMessage() {
        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.id("name")
                )
            ).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    // Get username displayed after successful login
    public String getLoggedInUsername() {
        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("userName-value")
            )
        ).getText();
    }
}
