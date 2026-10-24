package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookstorePage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By searchBox = By.id("searchBox");
    By imageHeading = By.xpath("//table/thead/tr/th[1]/span");
    By titleLinks = By.cssSelector("table tbody tr td:nth-child(2) a");
    By authorCells = By.cssSelector("table tbody tr td:nth-child(3)");

    public BookstorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openBookStore() {
        driver.get("https://demoqa.com/books");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public boolean isSearchBoxDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).isDisplayed();
    }

    public String getImageHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(imageHeading)).getText().trim();
    }

    public void searchBook(String bookName) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(bookName);
    }

    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (WebElement link : driver.findElements(titleLinks)) {
            if (!link.getText().trim().isEmpty()) {
                titles.add(link.getText().trim());
            }
        }
        return titles;
    }

    public List<String> getBookAuthors() {
        List<String> authors = new ArrayList<>();
        for (WebElement cell : driver.findElements(authorCells)) {
            if (!cell.getText().trim().isEmpty()) {
                authors.add(cell.getText().trim());
            }
        }
        return authors;
    }

    public int getBookCount() {
        return getBookTitles().size();
    }

    // Wait until the table finishes filtering (replaces Thread.sleep)
    public void waitForResults(boolean resultsExpected) {
        wait.until(d -> resultsExpected ? getBookCount() > 0 : getBookCount() == 0);
    }

    public void selectBook(String bookName) {
        By bookLink = By.xpath("//a[normalize-space()='" + bookName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(bookLink)).click();
    }
}