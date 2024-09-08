package Pages;

import Base.PageBase;
import Classes.Customers;
import Utils.SeleniumUtilities;
import com.google.common.collect.Ordering;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersList extends PageBase {
    private static final Logger log = LogManager.getLogger(SeleniumUtilities.class.getName());

    private By viewCustomerTableBtn = By.cssSelector("button[ng-click='showCust()']");
    private By customersTable = By.cssSelector("table");
    private By tableRows = By.cssSelector("table tbody tr");
    private By postCodeLink = By.xpath("//a[contains(@ng-click, 'postCd')]");
    private By searchCustomerInput = By.cssSelector("input[ng-model='searchCustomer']");

    public int getCustomersCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(customersTable));
        return driver.findElements(tableRows).size();
    }

    public String getLastAddedAccountNumber(List<Customers> customers) {
        log.info("Getting all account numbers from system customers");
        List<String> accountNumbers = customers.stream()
                .map(r -> r.accountNumber)
                .collect(Collectors.toList());

        // Extract the maximum value correctly
        String maxAccountNumber = SeleniumUtilities.getMaxValueOfList(accountNumbers);
        return maxAccountNumber;
    }

    public boolean sortCustomers(boolean ascending) {
        wait.until(ExpectedConditions.elementToBeClickable(postCodeLink));

        String action = ascending ? "ascending" : "descending";
        log.info("Sorting customers by Post Code in " + action + " order");
        SeleniumUtilities.pressElement(driver, postCodeLink, "Post Code Link");

        Iterable<String> columnValues = SeleniumUtilities.getColumnCells(driver, "Post Code", customersTable);

        boolean isSortedCorrectly = ascending ? Ordering.natural().isOrdered(columnValues) : Ordering.natural().reverse().isOrdered(columnValues);

        if (!isSortedCorrectly) {
            log.info("Toggling sorting order to achieve " + action + " order");
            SeleniumUtilities.pressElement(driver, postCodeLink, "Post Code Link");
        }

        columnValues = SeleniumUtilities.getColumnCells(driver, "Post Code", customersTable);
        return ascending ? Ordering.natural().isOrdered(columnValues) : Ordering.natural().reverse().isOrdered(columnValues);
    }

    public void searchForCustomerByFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchCustomerInput));
        log.info("Searching for customer with first name: " + firstName);
        SeleniumUtilities.type(driver, searchCustomerInput, firstName, "First Name Textbox");
    }

    public List<String> getDisplayedCustomers() {
        return SeleniumUtilities.getDisplayedTableAllRows(driver, customersTable);
    }

}
