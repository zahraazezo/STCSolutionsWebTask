package Base;

import Classes.Customers;
import Pages.Login;
import TestUtilities.TestHelper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileReader;
import java.io.IOException;

public class TestBase {
    private static final Logger log = LogManager.getLogger(PageBase.class.getName());
    public static Login login;
    CSVReader reader;

    @BeforeSuite
    public void setLog() throws IOException, CsvValidationException {

    }

    @BeforeMethod
    public void setDriver() throws IOException, CsvValidationException {
        String CSV_file = System.getProperty("user.dir") + "/src/test/java/TestData/Customers.csv";
        reader = new CSVReader(new FileReader(CSV_file));
        String[] csvCell;
        // while loop will be executed till the lastvalue in CSV file .
        while ((csvCell = reader.readNext()) != null) {
            PageBase.customers.add(new Customers(csvCell[0], csvCell[1], csvCell[2], csvCell[3]));
        }
        PageBase.setDriver();
        login = new Login();
    }

    @AfterMethod
    public void screenShotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test failed");
            log.info("Taking Screenshot....");
            TestHelper.captureScreenShot(PageBase.driver, result.getName());
        }
        PageBase.closeAllOpenedBrowserWindows();
    }
}
