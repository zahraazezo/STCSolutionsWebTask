package TestCases;

import Base.TestBase;
import Classes.Customers;
import Pages.CustomersList;
import Pages.Manager;
import Pages.OpenAccount;
import TestUtilities.TestHelper;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Base.PageBase.customers;

public class SearchCustomer extends TestBase {
    OpenAccount openAccount;
    Manager manager;
    CustomersList customersList;
    List<Customers> actualCustomersInfoList = new ArrayList<>();
    List<Customers> expectedCustomersList = new ArrayList<>();
    SoftAssert soft = new SoftAssert();

    @Test
    public void searchCustomerByFirstName() throws CsvException, IOException {
        String searchKeyword = "Hermoine";

        customersList = login.loginAsBankManager().viewCustomerList();
        customersList.searchForCustomerByFirstName(searchKeyword);

        expectedCustomersList = TestHelper.getExpectedCustomersByFirstName(customers, searchKeyword);
        actualCustomersInfoList = TestHelper.getCustomersByCellsInfo(customersList.getDisplayedCustomers());
        compareCustomerDetails(expectedCustomersList, actualCustomersInfoList);
    }

    private void compareCustomerDetails(List<Customers> expectedList, List<Customers> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Customers expected = expectedList.get(i);
            Customers actual = actualList.get(i);
            soft.assertEquals(expected.firstName, actual.firstName, "First name mismatch.");
            soft.assertEquals(expected.lastName, actual.lastName, "Last name mismatch.");
            soft.assertEquals(expected.accountNumber, actual.accountNumber, "Account number mismatch.");
            soft.assertEquals(expected.postCode, actual.postCode, "Postal code mismatch.");
        }
        soft.assertAll();
    }

    @Test
    public void searchCustomerByPostalCode() throws CsvException, IOException {
        customersList = login.loginAsBankManager().viewCustomerList();
        Assert.assertTrue(customersList.sortCustomers(true), "Postal code sorting failed.");
    }
}
