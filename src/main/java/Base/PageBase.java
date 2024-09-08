package Base;

import Classes.Customers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PageBase {
    private static final Logger log = LogManager.getLogger(PageBase.class.getName());
    public static List<Customers> customers = new ArrayList<Customers>();

    public static Properties config = new Properties();
    public static FileInputStream fis;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public JavascriptExecutor jse;

    public PageBase() {
    }

    public static void setDriver() {
        log.info("Setting up driver ...");

        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/properties/prop.properties")) {
            config.load(fis);
        } catch (FileNotFoundException e) {
            log.error("Properties file not found: " + e.getMessage());
        } catch (IOException e) {
            log.error("Failed to load properties: " + e.getMessage());
        }

        String browser = config.getProperty("browser");

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");
            log.info("Launching Chrome browser ...");
            driver = new ChromeDriver(options);
        } else {
            log.error("Browser not supported: " + browser);
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get(config.getProperty("testSitUrl"));

        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));

        driver.manage().window().maximize();
    }
    public static void closeAllOpenedBrowserWindows() {
        if (driver != null) {
            driver.quit();
            log.info("Closed all browser windows.");
        }
    }
}