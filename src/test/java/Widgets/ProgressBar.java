package Widgets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressBar {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/progress-bar");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));

        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']//div[@role='progressbar']"));
        WebElement startStopButton = driver.findElement(By.id("startStopButton"));

        int barValue;

        // Start the progress bar
        startStopButton.click();

        // Monitor until it reaches 60%, then stop
        while (true) {
            barValue = getProgressValue(progressBar);

            if (barValue >= 60) {
                startStopButton.click(); // stop
                break;
            }
        }

        System.out.println("Progress stopped at: " + barValue + "%");

        // Start again to complete 100%
        startStopButton.click();

        while (true) {
            barValue = getProgressValue(progressBar);

            if (barValue >= 100) {
                // Wait for reset button to become visible
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetButton")));

                // Click reset
                WebElement resetButton = driver.findElement(By.id("resetButton"));
                resetButton.click();

                // Small wait to let the reset take effect
                Thread.sleep(300);

                // Re-fetch updated value
                barValue = getProgressValue(progressBar);

                if (barValue == 0) {
                    System.out.println("Reset Complete ✅");
                } else {
                    System.out.println("Reset clicked but progress not reset ❌");
                }
                break;
            }
        }
    }

    public static int getProgressValue(WebElement progressBar)
    {
    		String style = progressBar.getDomAttribute("style");
    		return Integer.parseInt(style.replaceAll("[^0-9]", ""));
    }
    
    
}
