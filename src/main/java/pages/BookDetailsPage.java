package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    By title = By.xpath("//*[@id='title-wrapper']//div[contains(@class,'col-md-9')]//label");
    By author = By.xpath("//*[@id='author-wrapper']//div[contains(@class,'col-md-9')]//label");
    By addToCollectionButton = By.xpath("//button[normalize-space()='Add To Your Collection']");
    By backToBookStoreButton = By.xpath("//button[normalize-space()='Back To Book Store']");

    public BookDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public String getAuthor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(author)).getText();
    }

    // Returns the alert message so the test can assert it
    public String addToCollection() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(addToCollectionButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", button);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public void clickBackToBookStore() {
        wait.until(ExpectedConditions.elementToBeClickable(backToBookStoreButton)).click();
        wait.until(ExpectedConditions.urlContains("/books"));
    }
}