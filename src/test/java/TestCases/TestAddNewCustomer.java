package TestCases;

import Base.PageBase;
import Base.TestBase;
import Pages.AddCustomer;
import Pages.Manager;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class TestAddNewCustomer extends TestBase {
    private static final Logger log = LogManager.getLogger(PageBase.class.getName());
    CSVReader reader;
    AddCustomer addCustomer;
    Manager manager;

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Invalid Login Scenario with wrong username and password.")
    public void CheckAddNewCustomer() throws IOException, CsvValidationException, InterruptedException {

        log.info("Step1- Login as bank manager.....");
        manager = login.loginAsBankManager();

        // get path of CSV file
        String CSV_file = System.getProperty("user.dir") + "/src/test/java/TestData/Registeration.csv";
        reader = new CSVReader(new FileReader(CSV_file));
        String[] csvCell;
        // while loop will be executed till the last value in CSV file .
        while ((csvCell = reader.readNext()) != null) {
            var customersCount = manager.viewCustomerList().getCustomersCount();
            addCustomer = manager.viewAddCustomerForm();
            String firstname = csvCell[0]; // first name
            String lastname = csvCell[1]; // last name
            String postcode = csvCell[2]; // post code

            log.info("Step2- Fill the form .....");
            addCustomer.fillAddCustomerForm(firstname, lastname, postcode).addCustomer();

            String actualSuccessMsg = addCustomer.getAddCustomerSuccessMessageThenAccept();
            String expectedSuccessMsg = "Customer added successfully with customer id :" + (customersCount + 1);

            log.info("Step2- Check that the expected message " + expectedSuccessMsg + "equals to " + actualSuccessMsg);
            Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg);
        }
    }
}
