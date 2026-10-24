package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Registration page elements
    By firstNameField = By.id("firstname");
    By lastNameField = By.id("lastname");
    By usernameField = By.id("userName");
    By passwordField = By.id("password");
    By registerButton = By.id("register");

    // Open Registration page and wait until the form is ready
    public void openRegisterPage() {
        driver.get("https://demoqa.com/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    // Enter first name
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField))
            .sendKeys(firstName);
    }

    // Enter last name
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField))
            .sendKeys(lastName);
    }

    // Enter username
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
            .sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
            .sendKeys(password);
    }

    // Click Register button (JavaScript click works even if an ad covers the button)
    public void clickRegister() {
        WebElement button = wait.until(
            ExpectedConditions.presenceOfElementLocated(registerButton)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();",
            button
        );
    }

    // Get registration error message
    public String getErrorMessage() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.id("output")
                )
            ).getText().trim();
        } catch (org.openqa.selenium.TimeoutException e) {
            return "";
        }
    }

}