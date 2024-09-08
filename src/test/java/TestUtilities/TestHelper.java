package TestUtilities;

import Classes.Customers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {
    private static final Logger log = LogManager.getLogger(TestHelper.class.getName());

    public static void captureScreenShot(WebDriver driver, String screenshotname) {
        Path dest = Paths.get("./Screenshots", screenshotname + ".png");
        log.info(dest);
        try {
            Files.createDirectories(dest.getParent());
            FileOutputStream out = new FileOutputStream(dest.toString());
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.close();

        } catch (IOException e) {
            log.error("Exception while taking screenshot" + e.getMessage());
        }
    }

    public static List<Customers> getCustomersByCellsInfo(List<String> actualCustomersInfoList) {
        List<Customers> customers = new ArrayList<>();
        int listSize = actualCustomersInfoList.size();

        for (int i = 0; i < listSize; i++) {

            List<String> splittedList = new ArrayList<>(Arrays.asList(actualCustomersInfoList.get(i).split(" ")));

            if (!splittedList.isEmpty()) {
                splittedList.remove(splittedList.size() - 1);
            }

            // In some cases the account number format contains 4 parts
            if (splittedList.size() == 7) {
                customers.add(new Customers(
                        splittedList.get(0),
                        splittedList.get(1),
                        splittedList.get(2),
                        splittedList.get(3) + " " + splittedList.get(4) + " " + splittedList.get(5) + " " + splittedList.get(6)
                ));
            } else if (splittedList.size() == 6) {
                customers.add(new Customers(
                        splittedList.get(0),
                        splittedList.get(1),
                        splittedList.get(2),
                        splittedList.get(3) + " " + splittedList.get(4) + " " + splittedList.get(5)
                ));
            }
        }
        return customers;
    }

    public static List<Customers> getExpectedCustomersByFirstName(List<Customers> allCustomers, String firstName) {
        return allCustomers.stream()
                .filter(customer -> customer.firstName.equals(firstName))
                .collect(Collectors.toList());
    }

}
