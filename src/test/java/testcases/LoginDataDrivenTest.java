package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginDataDrivenTest extends BaseTest {

    @Test(
        dataProvider = "loginData",
        dataProviderClass = LoginDataProvider.class
    )
    public void verifyLoginWithExcelData(
            String username,
            String password,
            String expectedResult) {

    	LoginPage loginPage = new LoginPage(driver);
        // Step 1: Open Login page
        loginPage.openLoginPage();

        // Step 2: Enter username if provided
        if (username != null && !username.trim().isEmpty()) {
            loginPage.enterUsername(username);
        }

        // Step 3: Enter password if provided
        if (password != null && !password.trim().isEmpty()) {
            loginPage.enterPassword(password);
        }

        // Step 4: Click Login
        loginPage.clickLogin();

        // Step 5: Verify the expected result
        if (expectedResult.equalsIgnoreCase("Login Successful")) {

            String actualUsername =
                loginPage.getLoggedInUsername();

            Assert.assertEquals(
                actualUsername,
                username,
                "Logged-in username does not match"
            );

            System.out.println(
                "Login successful for: " + actualUsername
            );

        } else if (
            expectedResult.equalsIgnoreCase("Login Failed")
        ) {

            String actualError =
                loginPage.getLoginErrorMessage();

            // Handle blank credentials separately
            if (username.trim().isEmpty()
                    && password.trim().isEmpty()) {

                Assert.assertTrue(
                    actualError.isEmpty()
                        || actualError.equals(
                            "Invalid username or password!"
                        ),
                    "Unexpected error message for blank credentials"
                );

            } else {

                Assert.assertEquals(
                    actualError,
                    "Invalid username or password!",
                    "Expected login error message was not displayed"
                );
            }

            System.out.println(
                "Login failure verified for username: " + username
            );

        } else {

            Assert.fail(
                "Unexpected Expected Result value in Excel: "
                + expectedResult
            );
        }
    }
}
