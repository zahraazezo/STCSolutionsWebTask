package Utils;

import Base.PageBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeleniumUtilities {
    private static final Logger log = LogManager.getLogger(SeleniumUtilities.class.getName());

    // Method to wait for specific condition
    public static void Wait(WebDriver driver, By itemToWaitFor, String ControlName) {

        int timeToWait = 30;
        log.info("Waiting for max:: " + timeToWait + " seconds for " + ControlName + " to be available");
        try {

            long start = System.currentTimeMillis();
            PageBase.wait.until(ExpectedConditions.visibilityOfElementLocated(itemToWaitFor));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            log.info("Total Time for page load - " + totalTime);

        } catch (Exception e) {
            log.error("The element" + ControlName + "not appeared due to" + e.getMessage());
        }
    }

    // Method to clear and enter data in text box
    public static void type(WebDriver driver, By txt_ID, String txt_Value, String ControlName) {
        WebElement txt = driver.findElement(txt_ID);
        try {
            txt.clear();
            txt.sendKeys(txt_Value);
            log.info("The" + txt_Value + "value is typed in the " + "'" + ControlName + "'" + " text box");
        } catch (Exception e) {
            log.error("The " + txt_Value + " value isn't typed in the " + "'" + ControlName + "'" + " text box due to:"
                    + e.getMessage());
        }
    }

    // press element using javascript
    public static void pressElement(WebDriver driver, By btn_ID, String ctrlName) {

        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(btn_ID));
            log.info("The " + ctrlName + "was pressed successfully");
        } catch (Exception e) {

            log.error("The " + ctrlName + " wasn't pressed as " + e.getMessage());
        }
    }

    public static boolean isElementPresent(WebDriver driver, By by, String ControlName) {
        try {
            Wait(driver, by, ControlName);
            driver.findElement(by);
            log.info("'" + ControlName + "'" + " Element is Present");
            return true;
        } catch (NoSuchElementException e) {
            log.error("'" + ControlName + "'" + " Element is not Present due to " + e.getStackTrace());
            return false;
        }
    }

    public static String getAlertWindowText(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static void pressEnter(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
    }

    public static void selectValue(WebDriver driver, By element, String value) {
        Select drp = new Select(driver.findElement(element));
        drp.selectByVisibleText(value);
    }

    public static String getAllertMsgThenAccept(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();
        return msg;
    }

    // Method to double click on any button
    public static void ClickElement(WebDriver driver, By btn_ID, int numberOfClicks, String ctrlName) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(btn_ID);
        // Double click
        for (int i = 0; i < numberOfClicks; i++) {
            action.click(element).perform();
        }
    }

    public static List<WebElement> getColumnCellsElements(WebDriver driver, String columnName, By table) {
        Wait(driver, table, "table");
        List<String> headers = new ArrayList<>();
        List<String> columnValues = new ArrayList<>();

        List<WebElement> thList = driver.findElement(table).findElements(By.xpath("//thead//td"));

        thList.stream().forEach(th -> headers.add(th.getText()));
        int index = headers.indexOf(columnName);

        List<WebElement> trList = driver.findElement(table).findElements(By.xpath("//tr"));
        List<WebElement> tdList = new ArrayList<>();
        List<WebElement> columnCells = new ArrayList<>();

        WebElement cell = null;
        for (int i = 1; i < trList.size(); i++) {
            tdList = trList.get(i).findElements(By.tagName("td"));
            cell = tdList.get(index);
            columnCells.add(cell);
        }
        return columnCells;
    }

    public static List<String> getColumnCells(WebDriver driver, String columnName, By table) {
        Wait(driver, table, "table");
        List<String> headers = new ArrayList<>();
        List<String> columnValues = new ArrayList<>();

        List<WebElement> thList = driver.findElement(table).findElements(By.xpath("//thead//td"));

        thList.stream().forEach(th -> headers.add(th.getText()));
        int index = headers.indexOf(columnName);

        List<WebElement> trList = driver.findElement(table).findElements(By.xpath("//tr"));
        List<WebElement> tdList = new ArrayList<>();
        List<WebElement> columnCells = new ArrayList<>();

        WebElement cell = null;
        for (int i = 1; i < trList.size(); i++) {
            tdList = trList.get(i).findElements(By.tagName("td"));
            cell = tdList.get(index);
            columnCells.add(cell);
        }
        columnCells.stream().forEach(th -> columnValues.add(th.getText()));
        return columnValues;
    }

    public static List<String> getDisplayedTableAllRows(WebDriver driver, By table) {
        var tableBody = driver.findElement(table).findElement(By.tagName("tbody"));
        var rows = tableBody.findElements(By.tagName("tr"));
        List<String> cells = new ArrayList<>();
        rows.stream().forEach(cell -> cells.add(cell.getText()));
        return cells;
    }

    public static String getMaxValueOfList(List<String> list) {
        return Collections.max(list);
    }
}
