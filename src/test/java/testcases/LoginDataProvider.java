
package testcases;

import org.testng.annotations.DataProvider;

import utils.ExcelUtils;

public class LoginDataProvider {

	@DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        String filePath =
            System.getProperty("user.dir")
            + "/src/test/resources/testdata/DataTable.xlsx";

        return ExcelUtils.getData(filePath, "LoginData");
    }
}
