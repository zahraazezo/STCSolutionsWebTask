package Pages;

import Base.PageBase;
import Utils.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends PageBase {
    private By bankManagerLoginButton = By.cssSelector("button[ng-click='manager()']");
    private By customerLoginButton = By.cssSelector("button[ng-click='customer()']");

    public Manager loginAsBankManager() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bankManagerLoginButton));
        SeleniumUtilities.pressElement(driver, bankManagerLoginButton, "Bank Manager Login Button");
        return new Manager();
    }

    public Login loginAsCustomer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerLoginButton));
        SeleniumUtilities.pressElement(driver, customerLoginButton, "Customer Login Button");
        return this;
    }
}
