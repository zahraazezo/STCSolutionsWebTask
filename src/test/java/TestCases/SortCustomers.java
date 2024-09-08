package TestCases;

import Base.TestBase;
import Pages.CustomersList;
import Pages.Manager;
import Pages.OpenAccount;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SortCustomers extends TestBase {
    OpenAccount openAccount;
    Manager manager;
    CustomersList customersList;

    @Test
    public void verifySortCustomersDescending() throws CsvException, IOException {
        customersList = login.loginAsBankManager().viewCustomerList();
        boolean isSortedDescending = customersList.sortCustomers(false);
        Assert.assertTrue(isSortedDescending, "Customers are not sorted in descending order");
    }

    @Test
    public void verifySortCustomersAscending() throws CsvException, IOException {
        customersList = login.loginAsBankManager().viewCustomerList();
        boolean isSortedAscending = customersList.sortCustomers(true);
        Assert.assertTrue(isSortedAscending, "Customers are not sorted in ascending order");
    }

}
