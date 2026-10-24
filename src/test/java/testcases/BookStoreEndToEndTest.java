package testcases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BookDetailsPage;
import pages.BookstorePage;
import pages.LoginPage;

public class BookStoreEndToEndTest extends BaseTest {

    @Test
    public void verifyBookStoreEndToEndFlow() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        LoginPage loginPage = new LoginPage(driver);
        BookstorePage bookstorePage = new BookstorePage(driver);

        String username = "Mekha_005";
        String password = "Mekha@123";
        String bookName = "Git Pocket Guide";

        // Locators used on the Profile page
        By bookRow = By.xpath("//tr[.//a[normalize-space()='" + bookName + "']]");
        By deleteIcon = By.xpath("//tr[.//a[normalize-space()='" + bookName + "']]"
                + "//*[starts-with(@id,'delete-record-')]");
        By deleteModal = By.className("modal-content");
        By confirmDeleteButton = By.id("closeSmallModal-ok");
        By logoutButton = By.xpath("//button[normalize-space()='Log out' or normalize-space()='Logout']");

        // Step 1: Login
        loginPage.openLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        wait.until(ExpectedConditions.urlContains("/profile"));
        Assert.assertEquals(loginPage.getLoggedInUsername(), username, "Login verification failed.");

        // Step 2: Open Book Store and search for the book
        bookstorePage.openBookStore();
        bookstorePage.searchBook(bookName);
        bookstorePage.waitForResults(true);

        // Step 3: Open the book and verify its details
        bookstorePage.selectBook(bookName);

        BookDetailsPage detailsPage = new BookDetailsPage(driver);
        Assert.assertEquals(detailsPage.getTitle(), bookName, "Book title verification failed.");

        // Step 4: Add the book to the collection
        // (if an earlier run left the book there, the site says it is already present)
        String addMessage = detailsPage.addToCollection().toLowerCase();
        Assert.assertTrue(addMessage.contains("added") || addMessage.contains("already"),
                "Unexpected message after adding the book: " + addMessage);

        // Step 5: Open Profile and check the book is in the collection
        driver.get("https://demoqa.com/profile");
        driver.navigate().refresh();

        wait.until(ExpectedConditions.visibilityOfElementLocated(bookRow));
        Assert.assertFalse(driver.findElements(bookRow).isEmpty(),
                bookName + " was not found in the Profile collection.");

        // Step 6: Delete the book
        WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(deleteIcon));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", icon);

        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteModal));
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

        Alert successAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(successAlert.getText(), "Book deleted.", "Unexpected delete message.");
        successAlert.accept();

        // Step 7: Verify the book is removed
        wait.until(ExpectedConditions.invisibilityOfElementLocated(bookRow));
        Assert.assertTrue(driver.findElements(bookRow).isEmpty(), "Book was not removed from the collection.");

        // Step 8: Logout
        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", logout);

        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Logout verification failed.");
    }
}