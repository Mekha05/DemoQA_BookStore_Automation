
package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseTest;
import pages.BookstorePage;

public class BookStoreTest extends BaseTest {

    // Common method: Open Book Store page and wait for the search box
    private void openBookStorePage() {

        driver.get("https://demoqa.com/books");

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(20)
        );

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("searchBox")
            )
        );
    }

    // Test 1: Verify Book Store page launch
    @Test
    public void verifyBookStorePageLaunch() {

        openBookStorePage();

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(
            actualURL.contains("/books"),
            "Book Store page did not open successfully"
        );

        System.out.println("Book Store page opened successfully.");
        System.out.println("Current URL: " + actualURL);
    }

    // Test 2: Verify Book Store Application heading
    @Test
    public void verifyBookStoreApplicationHeading() {

        driver.get("https://demoqa.com/");

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(20)
        );

        String actualHeading = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/books']//h5")
            )
        ).getText();

        Assert.assertEquals(
            actualHeading,
            "Book Store Application",
            "Book Store Application heading does not match"
        );

        System.out.println("Heading verified: " + actualHeading);
    }

    // Test 3: Verify the search box is displayed
    @Test
    public void verifySearchBoxDisplayed() {

        openBookStorePage();

        BookstorePage bookstorePage = new BookstorePage(driver);

        Assert.assertTrue(
            bookstorePage.isSearchBoxDisplayed(),
            "Search box is not displayed"
        );

        System.out.println("Search box is displayed.");
    }

    // Test 4: Verify the Image column heading
    @Test
    public void verifyImageColumnHeading() {

        openBookStorePage();

        BookstorePage bookstorePage = new BookstorePage(driver);

        String actualHeading = bookstorePage.getImageHeading();

        Assert.assertEquals(
            actualHeading,
            "Image",
            "Image column heading does not match"
        );

        System.out.println("Image column heading: " + actualHeading);
    }

    // Test 5: Verify available books are displayed
    @Test
    public void verifyAvailableBooksDisplayed() {

        openBookStorePage();

        BookstorePage bookstorePage = new BookstorePage(driver);

        int bookCount = bookstorePage.getBookCount();

        Assert.assertTrue(
            bookCount > 0,
            "No books are displayed on the Book Store page"
        );

        System.out.println("Number of displayed books: " + bookCount);
    }

    // Test 6: Verify book titles are displayed
    @Test
    public void verifyBookTitlesDisplayed() {

        openBookStorePage();

        BookstorePage bookstorePage = new BookstorePage(driver);

        List<String> bookTitles = bookstorePage.getBookTitles();

        Assert.assertFalse(
            bookTitles.isEmpty(),
            "No book titles are displayed"
        );

        System.out.println("Displayed book titles:");

        for (String title : bookTitles) {
            System.out.println(title);
        }
    }

    // Test 7: Verify book authors are displayed
    @Test
    public void verifyBookAuthorsDisplayed() {

        openBookStorePage();

        BookstorePage bookstorePage = new BookstorePage(driver);

        List<String> authors = bookstorePage.getBookAuthors();

        Assert.assertFalse(
            authors.isEmpty(),
            "No book authors are displayed"
        );

        System.out.println("Displayed book authors:");

        for (String author : authors) {
            System.out.println(author);
        }
    }

    // Test 8: Negative test - Search for a non-existing book
    @Test
    public void verifySearchWithNonExistingBook() {

        openBookStorePage();

        BookstorePage bookstorePage = new BookstorePage(driver);

        bookstorePage.searchBook("NonExistingBook123");

        Assert.assertEquals(
            bookstorePage.getBookCount(),
            0,
            "Search results should be empty for a non-existing book"
        );

        System.out.println(
            "Negative search test passed: No matching books found."
        );
    }
}
