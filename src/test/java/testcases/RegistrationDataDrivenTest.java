package testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.RegisterPage;

public class RegistrationDataDrivenTest extends BaseTest {

    @Test(
        dataProvider = "registrationData",
        dataProviderClass = RegisterDataProvider.class
    )
    public void verifyRegistrationWithExcelData(
            String firstName,
            String lastName,
            String username,
            String password,
            String expectedResult) {

        // Remove unwanted spaces from Excel result
        expectedResult = expectedResult.trim();

        RegisterPage registerPage = new RegisterPage(driver);

        // Step 1: Open Registration page
        registerPage.openRegisterPage();

        // Step 2: Enter registration details
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterUsername(username);
        registerPage.enterPassword(password);

        // Step 3: Click Register
        registerPage.clickRegister();

        // Step 4: Read the application message
        String actualMessage = registerPage.getErrorMessage();

        System.out.println(
            "Username: " + username
            + " | Expected: " + expectedResult
            + " | Actual: " + actualMessage
        );

        // Step 5: Check whether CAPTCHA blocks registration
        if (actualMessage.toLowerCase().contains("recaptcha")) {
            throw new SkipException(
                "Registration could not be verified because CAPTCHA "
                + "requires manual verification."
            );
        }

        // Step 6: Verify the expected result
        if (expectedResult.equalsIgnoreCase("Already Registered User")) {

            // The site says "User exists!" (Firefox) - accept both wordings
            org.testng.Assert.assertTrue(
                actualMessage.toLowerCase().contains("already")
                    || actualMessage.toLowerCase().contains("exists"),
                "Expected an already-registered-user message, but got: "
                + actualMessage
            );

        } else if (
            expectedResult.equalsIgnoreCase("Enter Valid User Name")) {

            org.testng.Assert.assertTrue(
                actualMessage.toLowerCase().contains("username")
                    || actualMessage.toLowerCase().contains("user name"),
                "Expected a username validation message, but got: "
                + actualMessage
            );

        } else if (
            expectedResult.equalsIgnoreCase("Registered Successfully")) {

            org.testng.Assert.assertTrue(
                actualMessage.toLowerCase().contains("success"),
                "Expected a registration success message, but got: "
                + actualMessage
            );

        } else {
            org.testng.Assert.fail(
                "Unexpected Expected Result in Excel: " + expectedResult
            );
        }
    }
}