package TestCases;

import Base.PageBase;
import Base.TestBase;
import Classes.Customers;
import Pages.CustomersList;
import Pages.Manager;
import Pages.OpenAccount;
import TestUtilities.TestHelper;
import com.opencsv.exceptions.CsvException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Base.PageBase.customers;

public class TestOpenAccount extends TestBase {
    private static final Logger log = LogManager.getLogger(PageBase.class.getName());
    OpenAccount openAccount;
    Manager manager;
    CustomersList customersList;

    @Test
    public void CheckOpenCustomerAccount() throws CsvException, IOException, InterruptedException {
        String customerName = "Hermoine";
        String currency = "Dollar";

        List<String> actualCustomersInfoList = new ArrayList<>();
        log.info("Step1 - Login as bank manager -------");
        manager = login.loginAsBankManager();

        customersList = manager.viewCustomerList();

        // Ensure we get the last account number correctly
        String lastAccountNumberStr = customersList.getLastAddedAccountNumber(customers);
        int lastAccountNumberAddedBefore = Integer.parseInt(lastAccountNumberStr.trim().split(" ")[2]); // Assuming the most recent number is first

        openAccount = manager.viewOpenAccountForm();
        String actualSuccessMsg = openAccount.SelectCustomer().selectCurrency(currency).process().getSucessAlertMsgThenAccept();
        Assert.assertEquals(actualSuccessMsg, "Account created successfully with account Number :" + (lastAccountNumberAddedBefore + 1));

        customersList = manager.viewCustomerList();
        actualCustomersInfoList = customersList.getDisplayedCustomers();

        Customers customer = TestHelper.getCustomersByCellsInfo(actualCustomersInfoList).stream()
                .filter(c -> c.firstName.equals(customerName))
                .collect(Collectors.toList())
                .get(0);

        var splittedAccountNum = customer.accountNumber.trim().split(" ");
        int latestAccountNumber = Integer.parseInt(splittedAccountNum[splittedAccountNum.length - 1]);
        Assert.assertEquals((lastAccountNumberAddedBefore + 1), latestAccountNumber);
    }
}
