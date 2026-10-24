
package testcases;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class RegisterDataProvider {

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/DataTable.xlsx";

        return ExcelUtils.getData(filePath, "Registration");
    }
}
