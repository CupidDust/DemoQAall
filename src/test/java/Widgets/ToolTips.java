package Widgets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolTips {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/tool-tips");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));

        WebElement hoverButton = driver.findElement(By.id("toolTipButton"));

        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton).perform();

        // Wait for tooltip to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonToolTip")));

        // Validate the aria-describedby attribute
        String ariaValue = hoverButton.getDomAttribute("aria-describedby");
        if ("buttonToolTip".equalsIgnoreCase(ariaValue)) {
            System.out.println("aria-describedby attribute is set correctly");
        } else {
            System.out.println("aria-describedby attribute is missing or incorrect");
        }

        // Validate the tooltip text
        System.out.println("Tooltip text is: " + tooltip.getText());

        driver.quit();
    }
}
