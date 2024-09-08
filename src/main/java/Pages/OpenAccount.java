package Pages;

import Base.PageBase;
import Utils.SeleniumUtilities;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class OpenAccount extends PageBase {
    public static String selectedCustomerFirstName;
    CSVReader reader;
    private By customerSelect = By.id("userSelect");
    private By currencySelect = By.id("currency");
    private By processBtn = By.cssSelector("button[type='submit']");

    public OpenAccount SelectCustomer() throws CsvException, IOException {
        SeleniumUtilities.pressElement(driver, customerSelect, "View Customer List");

        String CSV_file = System.getProperty("user.dir") + "/src/test/java/TestData/Customers.csv";
        reader = new CSVReader(new FileReader(CSV_file));
        Optional<String[]> y = reader.readAll().stream().findFirst();
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerSelect));
        if (y.isPresent()) {
            SeleniumUtilities.selectValue(driver, customerSelect, y.get()[0] + " " + y.get()[1]);
            selectedCustomerFirstName = y.get()[0];
        }
        return this;
    }

    public OpenAccount selectCurrency(String currency) throws CsvValidationException, IOException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(currencySelect));
        SeleniumUtilities.selectValue(driver, currencySelect, currency);
        return this;
    }

    public OpenAccount process() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(processBtn));
        SeleniumUtilities.pressElement(driver, processBtn, "Process Button");
        return this;
    }

    public String getSucessAlertMsgThenAccept() {
        return SeleniumUtilities.getAllertMsgThenAccept(driver);
    }
}
