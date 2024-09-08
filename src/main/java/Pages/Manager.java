package Pages;

import Base.PageBase;
import Utils.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Manager extends PageBase {
    private By viewAddCustomerFormBtn = By.cssSelector("button[ng-click='addCust()']");
    private By viewOpenAccountFormBtn = By.cssSelector("button[ng-click='openAccount()']");
    private By viewAddCustomerListBtn = By.cssSelector("button[ng-click='showCust()']");

    public AddCustomer viewAddCustomerForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewAddCustomerFormBtn));
        SeleniumUtilities.pressElement(driver, viewAddCustomerFormBtn, "View Add Customer Form Button");
        return new AddCustomer();
    }

    public CustomersList viewCustomerList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewAddCustomerListBtn));
        SeleniumUtilities.pressElement(driver, viewAddCustomerListBtn, "View Customer List");
        return new CustomersList();
    }

    public OpenAccount viewOpenAccountForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewOpenAccountFormBtn));
        SeleniumUtilities.pressElement(driver, viewOpenAccountFormBtn, "View Open Account Form");
        return new OpenAccount();
    }
}
