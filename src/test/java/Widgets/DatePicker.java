package Widgets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePicker {

    public static void main(String[] args) {

        System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/date-picker");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.tagName("h1")));

        String year = "1998";
        String month = "November";
        String day = "7";
        String dayOfWeek = "Saturday";  // Update this manually if needed or calculate dynamically if required
        String time = "02:30";
        
        driver.findElement(By.id("datePickerMonthYearInput")).click();
        
        DatePicker obj = new DatePicker();
        WebElement yearList = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(yearList));
//        new Select(yearList).selectByVisibleText(year);
        obj.selectClass(yearList, year);

        WebElement monthList = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
//        new Select(monthList).selectByVisibleText(month);
        obj.selectClass(monthList, month);

        // Determine correct day suffix
        String suffix = getDaySuffix(day);

        // Click on the desired date dynamically
        String dynamicXpath = "//div[@aria-label='Choose " + dayOfWeek + ", " + month + " " + day + suffix + ", " + year + "']";
        driver.findElement(By.xpath(dynamicXpath)).click();
        
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dateAndTimePickerInput")));
        driver.findElement(By.id("dateAndTimePickerInput")).click();
        driver.findElement(By.xpath("//li[@class='react-datepicker__time-list-item ' and text()='"+time+"']")).click();
        
        
    }

    // Method to determine day suffix
    public static String getDaySuffix(String day) {
        int d = Integer.parseInt(day);
        if (d >= 11 && d <= 13) return "th";
        switch (d % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }
        
        public void selectClass(WebElement dropDownElement, String value)
        {
        	Select select = new Select(dropDownElement);
        	select.selectByVisibleText(value);
        }
    
}
