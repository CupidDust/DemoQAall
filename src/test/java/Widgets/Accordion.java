package Widgets;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

public class Accordion {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/accordian");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll to the Accordion section
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", 
            driver.findElement(By.cssSelector(".text-center")));

        // Locate the accordion section and its content
        WebElement section1Heading = driver.findElement(By.id("section1Heading"));
        WebElement section1Content = driver.findElement(By.id("section1Content"));
        WebElement contentText = section1Content.findElement(By.tagName("p"));

        // Print the content of the accordion section before collapsing
        System.out.println("Content inside the accordion (before collapse): ");
        System.out.println(contentText.getText());

        // Click the section heading to collapse it
        section1Heading.click();

        // Wait until the section is collapsed (content is invisible)
        wait.until(ExpectedConditions.invisibilityOf(section1Content));

        System.out.println("Accordion section is collapsed now!");

        // Re-expand the section by clicking the heading again
        section1Heading.click();

        // Wait until the section becomes visible again (content should be accessible now)
        wait.until(ExpectedConditions.visibilityOf(section1Content));

        // Access the content again after expanding
        contentText = section1Content.findElement(By.tagName("p"));
        System.out.println("Content inside the accordion (after expanding): ");
        System.out.println(contentText.getText());

        driver.quit();
    }
}
