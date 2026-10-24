
package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import base.BaseTest;

public class BookDetailsTest extends BaseTest {

    @Test
    public void verifyBookDetailsPageTitle() {

    	 driver.get("https://demoqa.com/books");
    	 
    	WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

        By bookLink = By.xpath(
            "//a[contains(@href, '/books?search=') and normalize-space()='Git Pocket Guide']"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(bookLink)
        ).click();


		String actualTitle = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[@id='title-wrapper']//div[contains(@class,'col-md-9')]//label")
		    )
		).getText();

        Assert.assertEquals(
            actualTitle,
            "Git Pocket Guide",
            "Book title does not match"
        );

        System.out.println(
            "Book Details Title: " + actualTitle
        );
    }

    @Test
    public void verifyBookDetailsPageAuthor() {
    	driver.get("https://demoqa.com/books");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By bookLink = By.linkText("Git Pocket Guide");
        wait.until(ExpectedConditions.elementToBeClickable(bookLink)).click();

        String actualAuthor = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[normalize-space()='Richard E. Silverman']")
            )
        ).getText();

        Assert.assertEquals(
            actualAuthor,
            "Richard E. Silverman",
            "Book author does not match"
        );

        System.out.println("Book Details Author: " + actualAuthor);
    }
}